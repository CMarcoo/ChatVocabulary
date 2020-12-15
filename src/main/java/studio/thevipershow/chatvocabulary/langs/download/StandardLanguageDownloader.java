package studio.thevipershow.chatvocabulary.langs.download;

import java.io.File;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.chatvocabulary.ChatVocabulary;
import studio.thevipershow.chatvocabulary.langs.StandardLanguage;

public final class StandardLanguageDownloader extends AbstractLanguageDownloader<StandardLanguage> {

    private static StandardLanguageDownloader instance = null;

    private StandardLanguageDownloader(ChatVocabulary chatVocabulary, AbstractLanguageStorageManager<StandardLanguage> standardLanguageStorageManager) {
        super(chatVocabulary, standardLanguageStorageManager, StandardCharsets.ISO_8859_1, HttpClient.Version.HTTP_2, 750L);
    }

    /**
     * Get the global instance of this language downloader.
     *
     * @return The instance.
     */
    @NotNull
    public static StandardLanguageDownloader getInstance(@NotNull ChatVocabulary chatVocabulary) {
        if (instance == null) {
            instance = new StandardLanguageDownloader(chatVocabulary, StandardLanguageStorageManager.getInstance(chatVocabulary));
        }
        return instance;
    }

    /**
     * Download a resource asynchronously and then export it into bukkit folder.
     *
     * @param language The language to download.
     * @param replace  Whether replace old ones or not.
     */
    @Override
    public final void downloadAsyncAndExport(@NotNull StandardLanguage language, boolean replace) {
        var englishFile = new File(super.chatVocabulary.getDataFolder(), language.getDefaultFilename());

        if (!englishFile.exists()) {
            var downloadFuture = this.downloadAsync(language);

            downloadFuture.exceptionally(throwable -> {
                throwable.printStackTrace();
                return "EXCEPTION";
            }).thenAccept(string -> {
                if (string.equals("EXCEPTION")) {
                    super.chatVocabulary.getLogger().warning("Something has gone wrong with the download of the default language.");
                } else {
                    this.languageStorageManager.exportToBukkitDirectory(language, replace);
                }
            });
        }
    }
}
