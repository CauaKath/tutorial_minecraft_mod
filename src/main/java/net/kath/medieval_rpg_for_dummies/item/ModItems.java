package net.kath.medieval_rpg_for_dummies.item;

import net.kath.medieval_rpg_for_dummies.MedievalRpgMod;
import net.kath.medieval_rpg_for_dummies.block.ModBlocks;
import net.kath.medieval_rpg_for_dummies.fluid.ModFluids;
import net.kath.medieval_rpg_for_dummies.item.custom.EightBallItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MedievalRpgMod.MOD_ID);

  public static final RegistryObject<Item> GOSLIM = ITEMS.register("goslim",
          () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MEDIEVAL_RPG_FOR_DUMMIES_TAB)));

  public static final RegistryObject<Item> RAW_GOSLIM = ITEMS.register("raw_goslim",
          () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MEDIEVAL_RPG_FOR_DUMMIES_TAB)));

  public static final RegistryObject<Item> EIGHT_BALL = ITEMS.register("eight_ball",
          () -> new EightBallItem(new Item.Properties().tab(ModCreativeModeTab.MEDIEVAL_RPG_FOR_DUMMIES_TAB).stacksTo(1)));

  public static final RegistryObject<Item> BLUEBERRY_SEEDS = ITEMS.register("blueberry_seeds",
          () -> new ItemNameBlockItem(ModBlocks.BLUEBERRY_CROP.get(),
                  new Item.Properties().tab(ModCreativeModeTab.MEDIEVAL_RPG_FOR_DUMMIES_TAB)));

  public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
          () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MEDIEVAL_RPG_FOR_DUMMIES_TAB)
                  .food(new FoodProperties.Builder().nutrition(2).saturationMod(2f).build())));

  public static final RegistryObject<Item> SOAP_WATER_BUCKET = ITEMS.register("soap_water_bucket",
          () -> new BucketItem(ModFluids.SOURCE_SOAP_WATER, new Item.Properties()
                  .tab(ModCreativeModeTab.MEDIEVAL_RPG_FOR_DUMMIES_TAB).craftRemainder(Items.BUCKET).stacksTo(1)));

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
