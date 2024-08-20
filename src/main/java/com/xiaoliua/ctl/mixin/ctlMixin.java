package com.xiaoliua.ctl.mixin;

import com.ibm.icu.text.CaseMap;
import com.xiaoliua.ctl.ctl;
import net.minecraft.client.gui.screens.TitleScreen;
import org.checkerframework.common.value.qual.MinLen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class ctlMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        ctl.LOGGER.info("This line is printed by an example mod mixin!");
    }
}
