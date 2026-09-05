package com.duskui.mod.mixin;

import com.duskui.mod.config.DuskPalette;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Draws a flat dark panel behind inventory-style screens (survival inventory,
 * chest, crafting table, furnace, anvil, etc.) instead of relying solely on
 * the beige vanilla texture, then lets vanilla draw item slots/icons on top.
 * This is a background wash, not a full texture replacement, so item icons
 * and slot highlight sprites remain fully legible.
 */
@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin {

    @Inject(method = "drawBackground", at = @At("HEAD"))
    private void duskui$panelWash(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        HandledScreen<?> self = (HandledScreen<?>) (Object) this;
        int x = self.getX(), y = self.getY();
        int w = ((net.minecraft.client.gui.screen.Screen) self).width;
        int h = ((net.minecraft.client.gui.screen.Screen) self).height;
        // Full-screen dark wash first (menu already handled by ScreenMixin,
        // this just ensures the container panel area reads as part of the theme).
        context.fill(0, 0, w, h, DuskPalette.BG_BASE);
    }
}
