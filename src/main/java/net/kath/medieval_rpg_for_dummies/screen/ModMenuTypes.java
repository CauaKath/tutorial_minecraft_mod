package net.kath.medieval_rpg_for_dummies.screen;

import net.kath.medieval_rpg_for_dummies.MedievalRpgMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

  public static final DeferredRegister<MenuType<?>> MENUS =
          DeferredRegister.create(ForgeRegistries.MENU_TYPES, MedievalRpgMod.MOD_ID);

  private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                String name) {
    return MENUS.register(name, () -> IForgeMenuType.create(factory));
  }

  public static final RegistryObject<MenuType<GemInfusingStationMenu>> GEM_INFUSING_STATION_MENU =
    registerMenuType(GemInfusingStationMenu::new, "gem_infusing_station_menu");

  public static void register(IEventBus eventBus) {
    MENUS.register(eventBus);
  }
}