package com.kesha.bettermovement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
        .getConfigDir()
        .resolve("bettermovement.json");
    
    private boolean enabled = true;
    
    public void load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH);
                ModConfig loaded = GSON.fromJson(json, ModConfig.class);
                this.enabled = loaded.enabled;
                BetterMovementMod.LOGGER.info("Config loaded: enabled={}", enabled);
            } catch (IOException e) {
                BetterMovementMod.LOGGER.error("Failed to load config", e);
            }
        } else {
            save();
        }
    }
    
    public void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            String json = GSON.toJson(this);
            Files.writeString(CONFIG_PATH, json);
            BetterMovementMod.LOGGER.info("Config saved: enabled={}", enabled);
        } catch (IOException e) {
            BetterMovementMod.LOGGER.error("Failed to save config", e);
        }
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public void toggleEnabled() {
        this.enabled = !this.enabled;
    }
}
