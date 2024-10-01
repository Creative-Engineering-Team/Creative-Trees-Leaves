package com.xiaoliua.ctl.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlowingFluid.class)
public abstract class FlowingFluidMixin {
    @Inject(method = "canSpreadTo",at = @At("HEAD"))
    public void canSpreadTo(BlockGetter p_75978_, BlockPos p_75979_, BlockState p_75980_, Direction p_75981_,
                            BlockPos p_75982_, BlockState p_75983_, FluidState p_75984_, Fluid p_75985_,
                            CallbackInfoReturnable<Boolean> cir) {
        //if (p_75983_.getBlock() instanceof hayrackBlock){
//            ctl.LOGGER.debug("{}\n{}\n{}", p_75984_.canBeReplacedWith(p_75978_, p_75982_, p_75985_, p_75981_),
//                    canPassThroughWall(p_75981_, p_75978_, p_75979_, p_75980_, p_75982_, p_75983_),
//                    canHoldFluid(p_75978_, p_75982_, p_75983_, p_75985_));
//            ctl.LOGGER.debug("{}\n{}\n{}\n{}\n{}\n{}", p_75978_, p_75979_, p_75980_.getBlock(), p_75981_, p_75982_, p_75983_.getBlock());
            //}
        //cir.setReturnValue(true);
    }
}
