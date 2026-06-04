package net.trollblox.mixin;

import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.trollblox.Discraft;
import net.trollblox.events.DiscordManager;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ChatListenerMixin {

	@Inject(at = @At("HEAD"), method = "logChatMessage")
	private void messageSent(final Component message, final ChatType.Bound chatType, final @Nullable String tag, CallbackInfo info) {
        Discraft.LOGGER.info("message detected");
        String text = chatType.decorate(message).getString();
        String author = text.substring(text.indexOf('<') + 1, text.indexOf('>'));
        String chat = text.substring(text.indexOf('>') + 2);
        DiscordManager.sendMessage(chat, author);
	}
}