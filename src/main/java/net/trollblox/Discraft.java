package net.trollblox;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.trollblox.config.Configs;
import net.trollblox.events.DiscordManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Discraft implements ModInitializer {
	public static final String MOD_ID = "discraft";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static MinecraftServer server;

	@Override
	public void onInitialize() {
        Configs.registerConfigs();

        ServerLifecycleEvents.SERVER_STARTED.register(s -> {
            server = s;
        });

        DiscordManager.init();

		LOGGER.info("Ready!");
	}
}