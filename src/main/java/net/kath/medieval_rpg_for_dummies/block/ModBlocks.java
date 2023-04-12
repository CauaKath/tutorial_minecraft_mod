package net.kath.medieval_rpg_for_dummies.block;

import net.kath.medieval_rpg_for_dummies.MedievalRpgMod;
import net.kath.medieval_rpg_for_dummies.block.custom.GoslimLampBlock;
import net.kath.medieval_rpg_for_dummies.block.custom.JumpyBlock;
import net.kath.medieval_rpg_for_dummies.item.ModCreativeModeTab;
import net.kath.medieval_rpg_for_dummies.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MedievalRpgMod.MOD_ID);

  public static final RegistryObject<Block> GOSLIM_BLOCK = registerBlock("goslim_block",
          () -> new Block(BlockBehaviour.Properties
                  .of(Material.STONE)
                  .strength(6.0f)
                  .requiresCorrectToolForDrops()
          ), ModCreativeModeTab.MEDIEVAL_RPG_FOR_DUMMIES_TAB);

  public static final RegistryObject<Block> GOSLIM_ORE = registerBlock("goslim_ore",
          () -> new DropExperienceBlock(BlockBehaviour.Properties
                  .of(Material.STONE)
                  .strength(6.0f)
                  .requiresCorrectToolForDrops(),
                  UniformInt.of(3, 7)
          ), ModCreativeModeTab.MEDIEVAL_RPG_FOR_DUMMIES_TAB);

  public static final RegistryObject<Block> DEEPSLATE_GOSLIM_ORE = registerBlock("deepslate_goslim_ore",
          () -> new DropExperienceBlock(BlockBehaviour.Properties
                  .of(Material.STONE)
                  .strength(6.0f)
                  .requiresCorrectToolForDrops(),
                  UniformInt.of(3, 7)
          ), ModCreativeModeTab.MEDIEVAL_RPG_FOR_DUMMIES_TAB);

  public static final RegistryObject<Block> JUMPY_BLOCK = registerBlock("jumpy_block",
          () -> new JumpyBlock(BlockBehaviour.Properties
                  .of(Material.STONE)
                  .strength(6.0f)
                  .requiresCorrectToolForDrops()
          ), ModCreativeModeTab.MEDIEVAL_RPG_FOR_DUMMIES_TAB);

  public static final RegistryObject<Block> GOSLIM_LAMP = registerBlock("goslim_lamp",
          () -> new GoslimLampBlock(BlockBehaviour.Properties
                  .of(Material.STONE)
                  .strength(6.0f)
                  .requiresCorrectToolForDrops()
                  .lightLevel(state -> state.getValue(GoslimLampBlock.LIT) ? 15 : 0)
          ), ModCreativeModeTab.MEDIEVAL_RPG_FOR_DUMMIES_TAB);

  public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab modeTab) {
    RegistryObject<T> toReturn = BLOCKS.register(name, block);

    registerBlockItem(name, toReturn, modeTab);

    return toReturn;
  }

  public static <T extends Block> RegistryObject<Item> registerBlockItem(
          String name, RegistryObject<T> block, CreativeModeTab modeTab
  ) {
    return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(modeTab)));
  }

  public static void register(IEventBus eventBus) {
    BLOCKS.register(eventBus);
  }

}
