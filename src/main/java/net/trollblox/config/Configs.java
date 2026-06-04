package net.trollblox.config;

import com.mojang.datafixers.util.Pair;
import net.trollblox.Discraft;

public class Configs {
    public static SimpleConfig CONFIG;
    private static ConfigProvider configs;

    public static String DISCORD_TOKEN;
    public static String CHAT_CHANNEL_ID;

    public static void registerConfigs() {
        configs = new ConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(Discraft.MOD_ID).provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("discord-token", ""), "Token for Discord bot");
        configs.addKeyValuePair(new Pair<>("chat-channel-id", ""), "Channel ID for Minecraft chat");
    }

    private static void assignConfigs() {
        DISCORD_TOKEN = CONFIG.getOrDefault("discord-token", "");
        CHAT_CHANNEL_ID = CONFIG.getOrDefault("chat-channel-id", "");
        Discraft.LOGGER.info("Assigned CHAT_CHANNEL_ID to {}", CHAT_CHANNEL_ID);
    }
}