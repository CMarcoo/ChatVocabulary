package studio.thevipershow.chatvocabulary.langs.download;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.chatvocabulary.langs.Language;

/**
 * An interface for downloading languages data.
 *
 * @param <T> The type of the language to download.
 */
public interface LanguageDownloader<T extends Language> {

    /**
     * Perform a download of a language asynchronously.
     *
     * @param language The language to download.
     * @return A future of the download data.
     */
    @NotNull
    CompletableFuture<String> downloadAsync(@NotNull T language);

    /**
     * Download a resource asynchronously and then export it into bukkit folder.
     *
     * @param language The language to download.
     * @param replace  Whether replace old ones or not.
     */
    void downloadAsyncAndExport(@NotNull T language, boolean replace);
}
