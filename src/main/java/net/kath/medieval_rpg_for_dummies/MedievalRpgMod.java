package net.kath.medieval_rpg_for_dummies;

import com.mojang.logging.LogUtils;
import net.kath.medieval_rpg_for_dummies.block.ModBlocks;
import net.kath.medieval_rpg_for_dummies.entity.ModBlockEntities;
import net.kath.medieval_rpg_for_dummies.fluid.ModFluidTypes;
import net.kath.medieval_rpg_for_dummies.fluid.ModFluids;
import net.kath.medieval_rpg_for_dummies.item.ModItems;
import net.kath.medieval_rpg_for_dummies.networking.ModMessages;
import net.kath.medieval_rpg_for_dummies.painting.ModPaintings;
import net.kath.medieval_rpg_for_dummies.screen.GemInfusingStationScreen;
import net.kath.medieval_rpg_for_dummies.screen.ModMenuTypes;
import net.kath.medieval_rpg_for_dummies.villager.ModVillagers;
import net.kath.medieval_rpg_for_dummies.world.feature.ModConfiguredFeatures;
import net.kath.medieval_rpg_for_dummies.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(MedievalRpgMod.MOD_ID)
public class MedievalRpgMod {

    public static final String MOD_ID = "medieval_rpg_for_dummies";

    private static final Logger LOGGER = LogUtils.getLogger();

    public MedievalRpgMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        ModVillagers.register(modEventBus);
        ModPaintings.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModMessages.register();
            ModVillagers.registerPOIs();
        });
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SOAP_WATER.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAP_WATER.get(), RenderType.translucent());

            MenuScreens.register(ModMenuTypes.GEM_INFUSING_STATION_MENU.get(), GemInfusingStationScreen::new);
        }
    }
}
