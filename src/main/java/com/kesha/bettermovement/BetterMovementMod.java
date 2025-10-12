package com.kesha.bettermovement;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterMovementMod implements ClientModInitializer {
    public static final String MOD_ID = "bettermovement";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static BetterMovementMod instance;
    private ModConfig config;
    private KeyInputHandler keyInputHandler;
    
    private static KeyBinding toggleKeyBinding;
    
    @Override
    public void onInitializeClient() {
        instance = this;
        LOGGER.info("Initializing Kesha's BetterMovement Mod");
        
        config = new ModConfig();
        config.load();
        
        keyInputHandler = new KeyInputHandler();
        
        toggleKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.bettermovement.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "category.bettermovement"
        ));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKeyBinding.wasPressed()) {
                config.toggleEnabled();
                config.save();
                
                if (client.player != null) {
                    String status = config.isEnabled() ? "enabled" : "disabled";
                    client.player.sendMessage(
                        Text.literal("§6[BetterMovement] §f" + status.toUpperCase()), 
                        true
                    );
                }
                
                LOGGER.info("BetterMovement toggled: {}", config.isEnabled());
            }
        });
        
        LOGGER.info("BetterMovement initialized successfully!");
    }
    
    public static BetterMovementMod getInstance() {
        return instance;
    }
    
    public ModConfig getConfig() {
        return config;
    }
    
    public KeyInputHandler getKeyInputHandler() {
        return keyInputHandler;
    }
}
