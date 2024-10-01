package com.xiaoliua.ctl.Features;

import com.xiaoliua.ctl.Blocks.BlockInit;
import com.xiaoliua.ctl.ctl;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class ctlOreFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> COPPER_ORE_GRAVEL_ORE = createKey("copper_ore_gravel_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> TIN_ORE_GRAVEL_ORE = createKey("tin_ore_gravel_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> pContext) {
        RuleTest gravelOreReplaceRuleTest = new BlockMatchTest(Blocks.GRAVEL);
        FeatureUtils.register(pContext, COPPER_ORE_GRAVEL_ORE, Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(gravelOreReplaceRuleTest, BlockInit.COPPER_ORE_GRAVEL.get().defaultBlockState())
        ), 7));
        FeatureUtils.register(pContext,TIN_ORE_GRAVEL_ORE,Feature.ORE,new OreConfiguration(List.of(
                OreConfiguration.target(gravelOreReplaceRuleTest, BlockInit.TIN_ORE_GRAVEL.get().defaultBlockState())
        ),4));

    }
    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String pName) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ctl.MODID,pName));
    }
}
