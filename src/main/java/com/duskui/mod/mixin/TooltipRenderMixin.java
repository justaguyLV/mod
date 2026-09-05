package com.duskui.mod.mixin;

import com.duskui.mod.config.DuskPalette;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Retints the tooltip box (item tooltips, button tooltips, advancement
 * hovers) from vanilla purple/black to the DuskUI violet->teal gradient
 * border on a near-black fill.
 */
@Mixin(DrawContext.class)
public abstract class TooltipRenderMixin {

    // Vanilla's drawTooltip internals call an internal gradient-border method
    // with 4 color args (bg top, bg bottom, border top, border bottom).
    // We intercept those literal color arguments and swap in our palette.
    @ModifyArg(
            method = "drawTooltip*",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawBorder(IIIIII)V", remap = false),
            require = 0
    )
    private int duskui$borderColor(int color) {
        return DuskPalette.TOOLTIP_BORDER_TOP;
    }
}
