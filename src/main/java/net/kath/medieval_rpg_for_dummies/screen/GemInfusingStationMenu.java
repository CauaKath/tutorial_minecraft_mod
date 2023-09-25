package net.kath.medieval_rpg_for_dummies.screen;

import net.kath.medieval_rpg_for_dummies.block.ModBlocks;
import net.kath.medieval_rpg_for_dummies.entity.GemInfusingStationBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class GemInfusingStationMenu extends AbstractContainerMenu {
  public final GemInfusingStationBlockEntity blockEntity;
  public final Level level;
  public final ContainerData data;

  public GemInfusingStationMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
    this(id, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
  }

  public GemInfusingStationMenu(int id, Inventory inventory, BlockEntity entity, ContainerData data) {
    super(ModMenuTypes.GEM_INFUSING_STATION_MENU.get(), id);

    checkContainerSize(inventory, 3);

    blockEntity = (GemInfusingStationBlockEntity) entity;
    this.level = inventory.player.level;
    this.data = data;

    addPlayerInventory(inventory);
    addPlayerHotbar(inventory);

    this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
      this.addSlot(new SlotItemHandler(h, 0, 12, 15));
      this.addSlot(new SlotItemHandler(h, 1, 86, 15));
      this.addSlot(new SlotItemHandler(h, 2, 86, 60));
    });

    addDataSlots(data);
  }

  public boolean isCrafting() {
    return data.get(0) > 0;
  }

  public int getScaledProgress() {
    int progress = this.data.get(0);
    int maxProgress = this.data.get(1);  // Max Progress
    int progressArrowSize = 26; // This is the height in pixels of your arrow

    return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
  }

  private static final int HOTBAR_SLOT_COUNT = 9;
  private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
  private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
  private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
  private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
  private static final int VANILLA_FIRST_SLOT_INDEX = 0;
  private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

  // THIS YOU HAVE TO DEFINE!
  private static final int TE_INVENTORY_SLOT_COUNT = 3;  // must be the number of slots you have!

  @Override
  public ItemStack quickMoveStack(Player playerIn, int index) {
    Slot sourceSlot = slots.get(index);
    if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
    ItemStack sourceStack = sourceSlot.getItem();
    ItemStack copyOfSourceStack = sourceStack.copy();

    // Check if the slot clicked is one of the vanilla container slots
    if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
      // This is a vanilla container slot so merge the stack into the tile inventory
      if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
              + TE_INVENTORY_SLOT_COUNT, false)) {
        return ItemStack.EMPTY;  // EMPTY_ITEM
      }
    } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
      // This is a TE slot so merge the stack into the players inventory
      if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
        return ItemStack.EMPTY;
      }
    } else {
      System.out.println("Invalid slotIndex:" + index);
      return ItemStack.EMPTY;
    }
    // If stack size == 0 (the entire stack was moved) set slot contents to null
    if (sourceStack.getCount() == 0) {
      sourceSlot.set(ItemStack.EMPTY);
    } else {
      sourceSlot.setChanged();
    }
    sourceSlot.onTake(playerIn, sourceStack);
    return copyOfSourceStack;
  }

  @Override
  public boolean stillValid(Player player) {
    return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
            ModBlocks.GEM_INFUSING_STATION.get());
  }

  private void addPlayerInventory(Inventory playerInventory) {
    for (int i = 0; i < 3; ++i) {
      for (int l = 0; l < 9; ++l) {
        this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
      }
    }
  }

  private void addPlayerHotbar(Inventory playerInventory) {
    for (int i = 0; i < 9; ++i) {
      this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
    }
  }
}
