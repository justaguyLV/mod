package com.duskui.mod.mixin;

import com.duskui.mod.config.DuskPalette;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public abstract class ScreenMixin {

    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    private void duskui$renderBackground(DrawContext context, CallbackInfo ci) {
        Screen self = (Screen) (Object) this;
        context.fillGradient(0, 0, self.width, self.height,
                DuskPalette.BG_BASE, DuskPalette.BG_PANEL);
        ci.cancel();
    }
}
