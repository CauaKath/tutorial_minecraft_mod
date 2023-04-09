package net.kath.medieval_rpg_for_dummies.item;

import net.kath.medieval_rpg_for_dummies.MedievalRpgMod;
import net.kath.medieval_rpg_for_dummies.item.custom.EightBallItem;
import net.minecraft.world.item.Item;
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

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
