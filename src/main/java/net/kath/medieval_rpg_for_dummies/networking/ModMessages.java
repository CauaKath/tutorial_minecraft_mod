package net.kath.medieval_rpg_for_dummies.networking;

import net.kath.medieval_rpg_for_dummies.MedievalRpgMod;
import net.kath.medieval_rpg_for_dummies.networking.packet.C2SPacket;
import net.kath.medieval_rpg_for_dummies.networking.packet.DrinkWaterC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

  private static SimpleChannel INSTANCE;

  private static int packetId = 0;
  private static int id() {
    return packetId++;
  }

  public static void register() {
    SimpleChannel net = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(MedievalRpgMod.MOD_ID, "messages"))
            .networkProtocolVersion(() -> "1.0")
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();

    INSTANCE = net;

    net.messageBuilder(C2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
            .encoder(C2SPacket::toBytes)
            .decoder(C2SPacket::new)
            .consumerMainThread(C2SPacket::handle)
            .add();

    net.messageBuilder(DrinkWaterC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
            .encoder(DrinkWaterC2SPacket::toBytes)
            .decoder(DrinkWaterC2SPacket::new)
            .consumerMainThread(DrinkWaterC2SPacket::handle)
            .add();
  }

  public static <MSG> void sendToServer(MSG message) {
    INSTANCE.sendToServer(message);
  }

  public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
    INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
  }

}
