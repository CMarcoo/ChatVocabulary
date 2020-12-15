package studio.thevipershow.chatvocabulary;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import studio.thevipershow.chatvocabulary.langs.StandardLanguage;
import studio.thevipershow.chatvocabulary.langs.download.AbstractLanguageDownloader;
import studio.thevipershow.chatvocabulary.langs.download.AbstractLanguageStorageManager;
import studio.thevipershow.chatvocabulary.langs.download.StandardLanguageDownloader;

@Getter
public final class ChatVocabulary extends JavaPlugin {

    private AbstractLanguageStorageManager<StandardLanguage> languageStorageManager;
    private AbstractLanguageDownloader<StandardLanguage> languageDownloader;

    @Override
    public final void onEnable() { // Plugin startup logic
        this.languageDownloader = StandardLanguageDownloader.getInstance(this);
        this.languageStorageManager = this.languageDownloader.getLanguageStorageManager();
    }

    @Override
    public final void onDisable() { // Plugin shutdown logic
    }
}
