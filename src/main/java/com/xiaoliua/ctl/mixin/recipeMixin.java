package com.xiaoliua.ctl.mixin;

import com.google.gson.JsonElement;
import com.xiaoliua.ctl.ctl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraftforge.client.RecipeBookManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
public abstract class recipeMixin {

    @Inject(method = "apply*", at = @At("HEAD"))
    private void customRecipesHead(Map<ResourceLocation, JsonElement> p_44037_,
                                   ResourceManager p_44038_, ProfilerFiller p_44039_, CallbackInfo ci) {
        p_44037_.remove(new ResourceLocation("minecraft","wooden_axe"));
        p_44037_.remove(new ResourceLocation("minecraft","wooden_hoe"));
        p_44037_.remove(new ResourceLocation("minecraft","wooden_pickaxe"));
        p_44037_.remove(new ResourceLocation("minecraft","wooden_shovel"));
        p_44037_.remove(new ResourceLocation("minecraft","wooden_sword"));

        p_44037_.remove(new ResourceLocation("minecraft","stone_axe"));
        p_44037_.remove(new ResourceLocation("minecraft","stone_hoe"));
        p_44037_.remove(new ResourceLocation("minecraft","stone_pickaxe"));
        p_44037_.remove(new ResourceLocation("minecraft","stone_shovel"));
        p_44037_.remove(new ResourceLocation("minecraft","stone_sword"));

        p_44037_.remove(new ResourceLocation("minecraft","campfire"));
        p_44037_.remove(new ResourceLocation("minecraft","soul_campfire"));
        //ctl.LOGGER.info(p_44037_.toString());
        //ctl.LOGGER.info("aaa");
    }
}
