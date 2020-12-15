package studio.thevipershow.chatvocabulary;

import java.util.logging.Logger;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import studio.thevipershow.chatvocabulary.langs.StandardLanguage;
import studio.thevipershow.chatvocabulary.langs.download.AbstractLanguageDownloader;
import studio.thevipershow.chatvocabulary.langs.download.StandardLanguageDownloader;

@Getter
public final class ChatVocabulary extends JavaPlugin {

    private AbstractLanguageDownloader<StandardLanguage> languageDownloader;
    private final Logger logger = getLogger();

    @Override
    public final void onEnable() { // Plugin startup logic
        this.languageDownloader = StandardLanguageDownloader.getInstance(this);
        this.languageDownloader.downloadAsyncAndExport(StandardLanguage.DEFAULT, false);

    }

    @Override
    public final void onDisable() { // Plugin shutdown logic
    }
}
