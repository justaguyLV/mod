package com.duskui.mod.mixin;

import com.duskui.mod.config.DuskPalette;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Draws a flat DuskUI panel + 1px accent border behind every vanilla button,
 * instead of the default stone-texture 9-slice sprite, then lets vanilla
 * draw the label text on top (HEAD-inject + cancel of just the sprite step
 * would break text, so instead we draw underneath by injecting at HEAD of
 * renderWidget and drawing our own box; vanilla's own background draw call
 * is a no-op sprite that renders on top of a transparent area only outside
 * our box in this simplified version).
 */
@Mixin(ButtonWidget.class)
public abstract class ButtonWidgetMixin extends PressableWidget {

    protected ButtonWidgetMixin(int x, int y, int width, int height, net.minecraft.text.Text message) {
        super(x, y, width, height, message);
    }

    @Inject(method = "renderWidget", at = @At("HEAD"))
    private void duskui$drawPanel(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        boolean hovered = this.isHovered();
        boolean enabled = this.active;

        int fill = !enabled ? DuskPalette.BUTTON_DISABLED
                : hovered ? DuskPalette.BUTTON_HOVER
                : DuskPalette.BUTTON_IDLE;
        int border = hovered && enabled ? DuskPalette.BUTTON_BORDER_HOVER : DuskPalette.BUTTON_BORDER_IDLE;

        int x0 = this.getX(), y0 = this.getY(), x1 = x0 + this.getWidth(), y1 = y0 + this.getHeight();

        context.fill(x0, y0, x1, y1, fill);
        // 1px border
        context.fill(x0, y0, x1, y0 + 1, border);
        context.fill(x0, y1 - 1, x1, y1, border);
        context.fill(x0, y0, x0 + 1, y1, border);
        context.fill(x1 - 1, y0, x1, y1, border);
    }
}
