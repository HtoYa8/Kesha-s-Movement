package com.kesha.bettermovement;

import net.minecraft.client.option.KeyBinding;

import java.util.HashMap;
import java.util.Map;

public class KeyInputHandler {
    private final Map<String, Long> keyPressTimes = new HashMap<>();
    
    public void recordKeyPress(String keyName) {
        keyPressTimes.put(keyName, System.currentTimeMillis());
    }
    
    public void clearKeyPress(String keyName) {
        keyPressTimes.remove(keyName);
    }
    
    public boolean shouldOverrideMovement(KeyBinding forward, KeyBinding back, KeyBinding left, KeyBinding right) {
        if (!BetterMovementMod.getInstance().getConfig().isEnabled()) {
            return false;
        }
        
        return (forward.isPressed() && back.isPressed()) || (left.isPressed() && right.isPressed());
    }
    
    public void handleOpposingKeys(KeyBinding key1, KeyBinding key2, String key1Name, String key2Name) {
        if (!BetterMovementMod.getInstance().getConfig().isEnabled()) {
            return;
        }
        
        boolean key1Pressed = key1.isPressed();
        boolean key2Pressed = key2.isPressed();
        
        if (key1Pressed && !keyPressTimes.containsKey(key1Name)) {
            recordKeyPress(key1Name);
        } else if (!key1Pressed && keyPressTimes.containsKey(key1Name)) {
            clearKeyPress(key1Name);
        }
        
        if (key2Pressed && !keyPressTimes.containsKey(key2Name)) {
            recordKeyPress(key2Name);
        } else if (!key2Pressed && keyPressTimes.containsKey(key2Name)) {
            clearKeyPress(key2Name);
        }
    }
    
    public boolean shouldSuppressKey(String keyName, String oppositeKeyName) {
        if (!BetterMovementMod.getInstance().getConfig().isEnabled()) {
            return false;
        }
        
        Long keyTime = keyPressTimes.get(keyName);
        Long oppositeTime = keyPressTimes.get(oppositeKeyName);
        
        if (keyTime != null && oppositeTime != null) {
            return oppositeTime > keyTime;
        }
        
        return false;
    }
}
