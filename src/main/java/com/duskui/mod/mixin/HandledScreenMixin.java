package com.duskui.mod.mixin;

import com.duskui.mod.config.DuskPalette;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin extends Screen {

    protected HandledScreenMixin(net.minecraft.text.Text title) {
        super(title);
    }

    @Inject(method = "drawBackground", at = @At("HEAD"))
    private void duskui$panelWash(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        context.fill(0, 0, this.width, this.height, DuskPalette.BG_BASE);
    }
}
