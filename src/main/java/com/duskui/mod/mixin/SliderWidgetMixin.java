package com.duskui.mod.mixin;

import com.duskui.mod.config.DuskPalette;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.SliderWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Redraws sliders (used in Options -> Video/Audio/Skin Customization etc.)
 * as a flat dark track with a violet fill and a gold handle instead of the
 * vanilla gray 9-slice sprite.
 */
@Mixin(SliderWidget.class)
public abstract class SliderWidgetMixin {

    @Inject(method = "renderWidget", at = @At("HEAD"), cancellable = true)
    private void duskui$renderSlider(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        SliderWidget self = (SliderWidget) (Object) this;
        int x0 = self.getX(), y0 = self.getY();
        int w = self.getWidth(), h = self.getHeight();

        // Track
        context.fill(x0, y0 + h / 2 - 1, x0 + w, y0 + h / 2 + 1, DuskPalette.SLIDER_TRACK);

        double value = ((SliderWidgetAccessor) self).duskui$getValue(); // 0.0 - 1.0
        int handleW = 8;
        int handleX = x0 + (int) (value * (w - handleW));

        // Filled portion of the track, up to the handle
        context.fill(x0, y0 + h / 2 - 1, handleX + handleW / 2, y0 + h / 2 + 1, DuskPalette.SLIDER_FILLED);
        context.fill(handleX, y0, handleX + handleW, y0 + h, DuskPalette.SLIDER_HANDLE);

        context.drawCenteredTextWithShadow(
                net.minecraft.client.MinecraftClient.getInstance().textRenderer,
                self.getMessage(), x0 + w / 2, y0 + (h - 8) / 2, DuskPalette.TEXT_PRIMARY);

        ci.cancel();
    }
}
