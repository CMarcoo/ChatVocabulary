package studio.thevipershow.chatvocabulary.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.chatvocabulary.ChatVocabulary;

public final class ChatCompleteListener extends PluginListener {

    public ChatCompleteListener(@NotNull ChatVocabulary chatVocabulary) {
        super(chatVocabulary);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public final void onPlayerChatTabComplete(PlayerChatTabCompleteEvent event) {

    }
}
