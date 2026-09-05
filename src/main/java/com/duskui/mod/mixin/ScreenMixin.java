package com.duskui.mod.mixin;

import com.duskui.mod.config.DuskPalette;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Replaces the default translucent-gray dirt-texture / gradient background
 * that every GUI screen (options, inventory frame, chat, world select...)
 * draws behind itself with the DuskUI near-black gradient.
 */
@Mixin(Screen.class)
public abstract class ScreenMixin {

    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    private void duskui$renderBackground(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        Screen self = (Screen) (Object) this;
        context.fillGradient(0, 0, self.width, self.height,
                DuskPalette.BG_BASE, DuskPalette.BG_PANEL);
        ci.cancel();
    }
}
