package com.xiaoliua.ctl.mixin;

import com.xiaoliua.ctl.Blocks.bonfireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CampfireBlock.class)
public class CampfireBlockMixin {
    @Inject(method = "canLight",at = @At("HEAD"), cancellable = true)
    private static void canLight(BlockState p_51322_, CallbackInfoReturnable<Boolean> cir) {
        if (p_51322_.getBlock() instanceof bonfireBlock){
            cir.setReturnValue(p_51322_.getValue(bonfireBlock.IGNITABLE) && !p_51322_.getValue(bonfireBlock.LIT)
            && !p_51322_.getValue(bonfireBlock.WATERLOGGED));
        }
    }
}
