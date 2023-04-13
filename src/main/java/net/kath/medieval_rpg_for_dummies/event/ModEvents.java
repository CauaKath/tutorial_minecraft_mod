package net.kath.medieval_rpg_for_dummies.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.kath.medieval_rpg_for_dummies.MedievalRpgMod;
import net.kath.medieval_rpg_for_dummies.item.ModItems;
import net.kath.medieval_rpg_for_dummies.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

}
