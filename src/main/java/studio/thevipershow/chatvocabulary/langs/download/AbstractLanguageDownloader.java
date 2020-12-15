package studio.thevipershow.chatvocabulary.langs.download;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.chatvocabulary.ChatVocabulary;
import studio.thevipershow.chatvocabulary.langs.Language;

public abstract class AbstractLanguageDownloader<T extends Enum<T> & Language> implements LanguageDownloader<T> {

    protected AbstractLanguageDownloader(@NotNull ChatVocabulary chatVocabulary,
                                         @NotNull AbstractLanguageStorageManager<T> languageStorageManager,
                                         @NotNull Charset responseCharset,
                                         @NotNull HttpClient.Version httpVersion,
                                         long responseTimeout) {
        this.chatVocabulary = chatVocabulary;
        this.languageStorageManager = languageStorageManager;
        this.defaultLanguageResponse = HttpResponse.BodyHandlers.ofString(responseCharset);
        this.httpClient = HttpClient.newBuilder().version(httpVersion).connectTimeout(Duration.ofMillis(responseTimeout)).build();
    }

    protected final ChatVocabulary chatVocabulary;
    @Getter
    protected final AbstractLanguageStorageManager<T> languageStorageManager;
    protected final HttpClient httpClient;
    protected final HttpResponse.BodyHandler<String> defaultLanguageResponse;

    /**
     * Perform a download of a language asynchronously.
     *
     * @param language The language to download.
     * @return A future of the download status.
     */
    @Override
    public @NotNull CompletableFuture<String> downloadAsync(@NotNull T language) {
        try {
            var uri = URI.create(language.getUrl());
            this.chatVocabulary.getLogger().info("Starting download for language " + language.getName());
            var currentTime = System.currentTimeMillis();
            return this.httpClient.sendAsync(
                    HttpRequest.newBuilder(uri).version(HttpClient.Version.HTTP_2).build()
                    , defaultLanguageResponse
            ).thenApply(HttpResponse::body).whenComplete((body, throwable) -> {
                if (throwable == null) {
                    this.chatVocabulary.getLogger().info("Download for language " + language.getName() + " has finished.");
                    this.chatVocabulary.getLogger().info(String.format("Time taken: %.4f milliseconds", ((currentTime - System.currentTimeMillis()) / 1e3)));
                    this.languageStorageManager.putLanguageData(language, body);
                } else {
                    chatVocabulary.getLogger().warning(String.format("Something has gone wrong with %s language obtainment.", language.getName()));
                }
            });

        } catch (IllegalArgumentException iax) {
            this.chatVocabulary.getLogger().warning("Could not download the vocabulary resource for language: " + language.getName());
            return CompletableFuture.failedFuture(iax);
        }
    }

}
