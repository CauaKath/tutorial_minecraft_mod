package net.kath.medieval_rpg_for_dummies.world.feature;

import net.kath.medieval_rpg_for_dummies.MedievalRpgMod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {

  public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
          DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MedievalRpgMod.MOD_ID);

  public static final RegistryObject<PlacedFeature> GOSLIM_ORE_PLACED = PLACED_FEATURES.register("goslim_ore_placed",
          () -> new PlacedFeature(ModConfiguredFeatures.GOSLIM_ORE.getHolder().get(),
                  commonOrePlacement(8, // VeinsPerChunk
                          HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

  public static final RegistryObject<PlacedFeature> END_GOSLIM_ORE_PLACED = PLACED_FEATURES.register("end_goslim_ore_placed",
          () -> new PlacedFeature(ModConfiguredFeatures.END_GOSLIM_ORE.getHolder().get(), commonOrePlacement(7, // VeinsPerChunk
                  HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

  public static final RegistryObject<PlacedFeature> NETHER_GOSLIM_ORE_PLACED = PLACED_FEATURES.register("nether_goslim_ore_placed",
          () -> new PlacedFeature(ModConfiguredFeatures.NETHER_GOSLIM_ORE.getHolder().get(), commonOrePlacement(7, // VeinsPerChunk
                  HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

  public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
    return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
  }

  public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
    return orePlacement(CountPlacement.of(p_195344_), p_195345_);
  }

  public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
    return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
  }

  public static void register(IEventBus eventBus) {
    PLACED_FEATURES.register(eventBus);
  }

}
