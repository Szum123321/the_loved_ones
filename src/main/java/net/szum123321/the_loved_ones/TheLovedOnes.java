package net.szum123321.the_loved_ones;

import io.github.cottonmc.cotton.config.ConfigManager;
import io.github.cottonmc.cotton.logging.ModLogger;
import net.fabricmc.api.ModInitializer;


public class TheLovedOnes implements ModInitializer {
    public static ModLogger logger = new ModLogger("the_loved_ones", "The Loved Ones");
    public static ConfigHandler config;

    @Override
    public void onInitialize() {
        config = ConfigManager.loadConfig(ConfigHandler.class);
        logger.debug("Loaded config?");
        System.out.println("Config Loaded");
    }
}