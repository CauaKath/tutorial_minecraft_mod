package net.kath.medieval_rpg_for_dummies.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.kath.medieval_rpg_for_dummies.MedievalRpgMod;
import net.kath.medieval_rpg_for_dummies.item.ModItems;
import net.kath.medieval_rpg_for_dummies.thirst.PlayerThirst;
import net.kath.medieval_rpg_for_dummies.thirst.PlayerThirstProvider;
import net.kath.medieval_rpg_for_dummies.villager.ModVillagers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = MedievalRpgMod.MOD_ID)
public class ModEvents {

  @SubscribeEvent
  public static void addCustomTrades(VillagerTradesEvent event) {
    if (event.getType() == VillagerProfession.TOOLSMITH) {
      Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
      ItemStack stack = new ItemStack(ModItems.EIGHT_BALL.get(), 1);
      int villagersLevel = 1;

      trades.get(villagersLevel).add((trader, rand) -> new MerchantOffer(
              new ItemStack(Items.EMERALD, 2),
              stack, 10, 8, 0.02F
      ));
    }

    if (event.getType() == ModVillagers.JUMP_MASTER.get()) {
      Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
      int villagersLevel = 1;

      trades.get(villagersLevel).add((trader, rand) -> new MerchantOffer(
              new ItemStack(Items.EMERALD, 5),
              new ItemStack(ModItems.BLUEBERRY.get(), 15),
              10, 8, 0.02F
      ));

      trades.get(villagersLevel).add((trader, rand) -> new MerchantOffer(
              new ItemStack(Items.EMERALD, 8),
              new ItemStack(ModItems.EIGHT_BALL.get(), 1),
              10, 8, 0.02F
      ));
    }
  }

  @SubscribeEvent
  public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
    if (event.getObject() instanceof Player) {
      if (!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()) {
        event.addCapability(new ResourceLocation(MedievalRpgMod.MOD_ID, "properties"), new PlayerThirstProvider());
      }
    }
  }

  @SubscribeEvent
  public static void onPlayerCloned(PlayerEvent.Clone event) {
    if (event.isWasDeath()) {
      event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldThirst -> {
        event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newThirst -> {
          newThirst.copyFrom(oldThirst);
        });
      });
    }
  }

  @SubscribeEvent
  public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
    event.register(PlayerThirst.class);
  }

  @SubscribeEvent
  public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.side == LogicalSide.SERVER) {
      event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
        if (thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.005f) {
          thirst.subThirst(1);
          event.player.sendSystemMessage(Component.literal("Subtracted Thirst"));
        }
      });
    }
  }

}
