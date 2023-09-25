package net.kath.medieval_rpg_for_dummies.block.custom;

import net.kath.medieval_rpg_for_dummies.entity.GemInfusingStationBlockEntity;
import net.kath.medieval_rpg_for_dummies.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class GemInfusingStationBlock extends BaseEntityBlock {

  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

  public GemInfusingStationBlock(Properties properties) {
    super(properties);
  }

  private static final VoxelShape SHAPE =
          Block.box(0, 0, 0, 16, 10, 16);

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
    return SHAPE;
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext pContext) {
    return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
  }

  @Override
  public BlockState rotate(BlockState pState, Rotation pRotation) {
    return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
  }

  @Override
  public BlockState mirror(BlockState pState, Mirror pMirror) {
    return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }

  /* BLOCK ENTITY */

  @Override
  public RenderShape getRenderShape(BlockState state) {
    return RenderShape.MODEL;
  }

  @Override
  public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
    if (state.getBlock() != newState.getBlock()) {
      BlockEntity blockEntity = level.getBlockEntity(pos);
      if (blockEntity instanceof GemInfusingStationBlockEntity) {
        ((GemInfusingStationBlockEntity) blockEntity).drops();
      }
    }

    super.onRemove(state, level, pos, newState, isMoving);
  }

  @Override
  public InteractionResult use(BlockState state, Level level, BlockPos pos,
                               Player player, InteractionHand hand, BlockHitResult hit) {
    if (!level.isClientSide()) {
      BlockEntity entity = level.getBlockEntity(pos);
      if(entity instanceof GemInfusingStationBlockEntity) {
        NetworkHooks.openScreen(((ServerPlayer) player), (GemInfusingStationBlockEntity)entity, pos);
      } else {
        throw new IllegalStateException("Our Container provider is missing!");
      }
    }

    return InteractionResult.sidedSuccess(level.isClientSide());
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return new GemInfusingStationBlockEntity(pos, state);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
    return createTickerHelper(type, ModBlockEntities.GEM_INFUSING_STATION.get(), GemInfusingStationBlockEntity::tick);
  }
}
