package studio.thevipershow.chatvocabulary;

import java.util.logging.Logger;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import studio.thevipershow.chatvocabulary.langs.StandardLanguage;
import studio.thevipershow.chatvocabulary.langs.download.AbstractLanguageDownloader;
import studio.thevipershow.chatvocabulary.langs.download.StandardLanguageDownloader;
import studio.thevipershow.chatvocabulary.listeners.ChatCompleteListener;
import studio.thevipershow.chatvocabulary.listeners.PluginListener;

@Getter
public final class ChatVocabulary extends JavaPlugin {

    private AbstractLanguageDownloader<StandardLanguage> languageDownloader;
    private PluginListener chatCompleteListener;
    private final Logger logger = getLogger();

    @Override
    public final void onEnable() { // Plugin startup logic
        this.languageDownloader = StandardLanguageDownloader.getInstance(this);
        this.languageDownloader.downloadAsyncAndExport(StandardLanguage.DEFAULT, false);

        this.chatCompleteListener = new ChatCompleteListener(this);
        getServer().getPluginManager().registerEvents(chatCompleteListener, this);
    }

    @Override
    public final void onDisable() { // Plugin shutdown logic
    }
}
