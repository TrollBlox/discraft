package net.trollblox.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import net.minecraft.network.chat.Component;
import net.trollblox.Discraft;
import net.trollblox.config.Configs;

public class DiscordManager extends ListenerAdapter {
    public static JDA bot;

    public static void init() {
        if (Configs.DISCORD_TOKEN.isEmpty()) throw new RuntimeException("Discraft token must not be blank!");
        if (Configs.CHAT_CHANNEL_ID.isEmpty()) throw new RuntimeException("Discraft channel ID must not be blank!");

        bot = JDABuilder.createDefault(Configs.DISCORD_TOKEN)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .build();

        bot.addEventListener(new DiscordManager());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getChannel().getId().equals(Configs.CHAT_CHANNEL_ID)) return;
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        Discraft.server.execute(() -> Discraft.server.getPlayerList().broadcastSystemMessage(Component.literal("[Discord] <" + message.getAuthor().getGlobalName() + "> " + message.getContentRaw()), false));
    }

    public static void sendMessage(String message, String author) {
        EmbedBuilder embed = new EmbedBuilder().setAuthor("<" + author + "> " + message);
        bot.getTextChannelById(Configs.CHAT_CHANNEL_ID).sendMessageEmbeds(embed.build()).queue();
    }
}
