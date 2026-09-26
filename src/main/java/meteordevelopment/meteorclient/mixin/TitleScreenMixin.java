/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import meteordevelopment.meteorclient.systems.config.Config;
import meteordevelopment.meteorclient.utils.player.TitleScreenCredits;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    public TitleScreenMixin(Component title) {
        super(title);
    }

    // MC 26.3 runs GameRenderer.extract() → TitleScreen.extractRenderState() during Minecraft.<init>,
    // which is before MeteorClient constructs its Config singleton. Config.get() can therefore be null
    // here at startup; do not crash the client on initial screen rendering.
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void onExtractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        Config config = Config.get();
        if (config != null && config.titleScreenCredits.get()) TitleScreenCredits.render(graphics);
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void onMouseClicked(MouseButtonEvent event, boolean doubleClick, CallbackInfoReturnable<Boolean> cir) {
        Config config = Config.get();
        if (config != null && config.titleScreenCredits.get() && event.button() == InputConstants.MOUSE_BUTTON_LEFT) {
            if (TitleScreenCredits.onClicked(event.x(), event.y())) cir.setReturnValue(true);
        }
    }
}