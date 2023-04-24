package net.kath.medieval_rpg_for_dummies;

import com.mojang.logging.LogUtils;
import net.kath.medieval_rpg_for_dummies.block.ModBlocks;
import net.kath.medieval_rpg_for_dummies.item.ModItems;
import net.kath.medieval_rpg_for_dummies.networking.ModMessages;
import net.kath.medieval_rpg_for_dummies.painting.ModPaintings;
import net.kath.medieval_rpg_for_dummies.villager.ModVillagers;
import net.kath.medieval_rpg_for_dummies.world.feature.ModConfiguredFeatures;
import net.kath.medieval_rpg_for_dummies.world.feature.ModPlacedFeatures;
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

        ModVillagers.register(modEventBus);
        ModPaintings.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ModVillagers::registerPOIs);

        ModMessages.register();
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
