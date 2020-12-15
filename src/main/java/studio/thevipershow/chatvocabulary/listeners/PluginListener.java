package studio.thevipershow.chatvocabulary.listeners;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Listener;
import studio.thevipershow.chatvocabulary.ChatVocabulary;

@RequiredArgsConstructor
public abstract class PluginListener implements Listener {

    @Getter private final ChatVocabulary chatVocabulary;
}
