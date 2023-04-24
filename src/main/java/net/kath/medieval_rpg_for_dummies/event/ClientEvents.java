package net.kath.medieval_rpg_for_dummies.event;

import net.kath.medieval_rpg_for_dummies.MedievalRpgMod;
import net.kath.medieval_rpg_for_dummies.networking.ModMessages;
import net.kath.medieval_rpg_for_dummies.networking.packet.C2SPacket;
import net.kath.medieval_rpg_for_dummies.networking.packet.DrinkWaterC2SPacket;
import net.kath.medieval_rpg_for_dummies.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {

  @Mod.EventBusSubscriber(modid = MedievalRpgMod.MOD_ID, value = Dist.CLIENT)
  public static class ClientForgeEvents {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
      if (KeyBinding.DRINKING_KEY.consumeClick()) {
        if (Minecraft.getInstance().player == null) return;

        ModMessages.sendToServer(new DrinkWaterC2SPacket());
      }
    }

  }

  @Mod.EventBusSubscriber(modid = MedievalRpgMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class ClientModBusEvents {
    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
      event.register(KeyBinding.DRINKING_KEY);
    }
  }

}
