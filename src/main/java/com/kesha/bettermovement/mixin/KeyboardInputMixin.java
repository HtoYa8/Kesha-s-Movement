package com.kesha.bettermovement.mixin;

import com.kesha.bettermovement.BetterMovementMod;
import com.kesha.bettermovement.KeyInputHandler;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {
    
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(boolean slowDown, float slowDownFactor, CallbackInfo ci) {
        KeyboardInput input = (KeyboardInput) (Object) this;
        GameOptions options = net.minecraft.client.MinecraftClient.getInstance().options;
        KeyInputHandler handler = BetterMovementMod.getInstance().getKeyInputHandler();
        
        if (!BetterMovementMod.getInstance().getConfig().isEnabled()) {
            return;
        }
        
        handler.handleOpposingKeys(
            options.forwardKey, 
            options.backKey, 
            "forward", 
            "back"
        );
        
        handler.handleOpposingKeys(
            options.leftKey, 
            options.rightKey, 
            "left", 
            "right"
        );
    }
    
    @Inject(method = "tick", at = @At("TAIL"))
    private void afterTick(boolean slowDown, float slowDownFactor, CallbackInfo ci) {
        KeyboardInput input = (KeyboardInput) (Object) this;
        GameOptions options = net.minecraft.client.MinecraftClient.getInstance().options;
        KeyInputHandler handler = BetterMovementMod.getInstance().getKeyInputHandler();
        
        if (!BetterMovementMod.getInstance().getConfig().isEnabled()) {
            return;
        }
        
        boolean forwardPressed = options.forwardKey.isPressed();
        boolean backPressed = options.backKey.isPressed();
        boolean leftPressed = options.leftKey.isPressed();
        boolean rightPressed = options.rightKey.isPressed();
        
        if (forwardPressed && backPressed) {
            if (handler.shouldSuppressKey("forward", "back")) {
                input.pressingForward = false;
                input.pressingBack = true;
            } else if (handler.shouldSuppressKey("back", "forward")) {
                input.pressingForward = true;
                input.pressingBack = false;
            }
        }
        
        if (leftPressed && rightPressed) {
            if (handler.shouldSuppressKey("left", "right")) {
                input.pressingLeft = false;
                input.pressingRight = true;
            } else if (handler.shouldSuppressKey("right", "left")) {
                input.pressingLeft = true;
                input.pressingRight = false;
            }
        }
        
        if (forwardPressed && backPressed) {
            if (handler.shouldSuppressKey("forward", "back")) {
                input.movementForward = -1.0F;
            } else if (handler.shouldSuppressKey("back", "forward")) {
                input.movementForward = 1.0F;
            }
        }
        
        if (leftPressed && rightPressed) {
            if (handler.shouldSuppressKey("left", "right")) {
                input.movementSideways = -1.0F;
            } else if (handler.shouldSuppressKey("right", "left")) {
                input.movementSideways = 1.0F;
            }
        }
    }
}
