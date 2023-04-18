package net.kath.medieval_rpg_for_dummies.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.kath.medieval_rpg_for_dummies.MedievalRpgMod;
import net.kath.medieval_rpg_for_dummies.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModConfiguredFeatures {

  public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
          DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MedievalRpgMod.MOD_ID);

  public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_GOSLIM_ORES = Suppliers.memoize(() -> List.of(
          OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.GOSLIM_ORE.get().defaultBlockState()),
          OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_GOSLIM_ORE.get().defaultBlockState())));
  public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_GOSLIM_ORES = Suppliers.memoize(() -> List.of(
          OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.NETHERRACK_GOSLIM_ORE.get().defaultBlockState())));
  public static final Supplier<List<OreConfiguration.TargetBlockState>> END_GOSLIM_ORES = Suppliers.memoize(() -> List.of(
          OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.ENDSTONE_GOSLIM_ORE.get().defaultBlockState())));

  public static final RegistryObject<ConfiguredFeature<?, ?>> GOSLIM_ORE = CONFIGURED_FEATURES.register("goslim_ore",
          () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_GOSLIM_ORES.get(), 8)));
  public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_GOSLIM_ORE = CONFIGURED_FEATURES.register("nether_goslim_ore",
          () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_GOSLIM_ORES.get(), 9)));
  public static final RegistryObject<ConfiguredFeature<?, ?>> END_GOSLIM_ORE = CONFIGURED_FEATURES.register("end_goslim_ore",
          () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_GOSLIM_ORES.get(), 4)));

  public static void register(IEventBus eventBus) {
    CONFIGURED_FEATURES.register(eventBus);
  }

}
