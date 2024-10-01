package com.xiaoliua.ctl.Features;

import com.xiaoliua.ctl.ctl;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ctlOrePlacements {
    public static final ResourceKey<PlacedFeature> COPPER_ORE_GRAVEL_ORE = createKey("copper_ore_gravel_ore");
    public static final ResourceKey<PlacedFeature> TIN_ORE_GRAVEL_ORE = createKey("tin_ore_gravel_ore");
    public static void bootstrap(BootstapContext<PlacedFeature> pContext) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = pContext.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> oreCopoerHolder = holdergetter.getOrThrow(ctlOreFeatures.COPPER_ORE_GRAVEL_ORE);
        Holder<ConfiguredFeature<?,?>> oreTinHolder = holdergetter.getOrThrow(ctlOreFeatures.TIN_ORE_GRAVEL_ORE);
        // 让后我们就可以使用PlacementUtils的register方法，注册我们的PlacedFeature
        // 第一个参数就是上下文，第二个参数是对应ConfiguredFeature的holder，第三个参数是list的PlacementModifier，说明你的PlacedFeature的设置内容，
        // 这里commonOrePlacement是指设置了一系列的PlacementModifier，对应的是常规的矿物的生成，和原版的一样。
        // 对于PlacementModifier有很多的子类，你可以去继承关系中看
        // 这里我们用的一个是HeightRangePlacement，是指设置生成的最低高度和最高的高度。
        // 对于commonOrePlacement方法中用的CountPlacement则是设置一个数值
        // HeightRangePlacement的uniform方法是指从低到高平均生成，还有一个三角的，是指中间生成多，两边生成少。
        PlacementUtils.register(
                pContext,
                COPPER_ORE_GRAVEL_ORE,
                oreCopoerHolder,
                commonOrePlacement(40, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(255)))
        );
        PlacementUtils.register(
                pContext,
                TIN_ORE_GRAVEL_ORE,
                oreTinHolder,
                commonOrePlacement(60,HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(255)))
        );
    }



    private static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }
    @SuppressWarnings("unused")
    private static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }

    public static ResourceKey<PlacedFeature> createKey(String pKey) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ctl.MODID,pKey));
    }
}
