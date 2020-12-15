package studio.thevipershow.chatvocabulary.langs;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public enum StandardLanguage implements Language {

    DANSK("dansk", buildLanguageUrl("dansk")),
    DEUTSCH("deutsch", buildLanguageUrl("deutsch")),
    ENGLISH("english", buildLanguageUrl("english")),
    ESPANOL("espanol", buildLanguageUrl("espanol")),
    FRANCAIS("francais", buildLanguageUrl("francais")),
    ITALIANO("italiano", buildLanguageUrl("italiano")),
    NEDERLANDS("nederlands", buildLanguageUrl("nederlands")),
    NORSK("norsk", buildLanguageUrl("norsk"));

    private final String languageName;
    private final String languageUrl;

    private final static String BASE_GITHUB_URL = "https://raw.githubusercontent.com/TheViperShow/ChatVocabulary/main/langs/";

    private static String buildLanguageUrl(@NotNull String languageName) {
        return BASE_GITHUB_URL + languageName + "/" + languageName + ".txt";
    }

    /**
     * Utility method to concatenate extension after language name.
     *
     * @param extensionName the name of the file extension without dots.
     * @return The filename.
     */
    @SuppressWarnings("SameParameterValue")
    private @NotNull String getFileWithExtension(@NotNull String extensionName) {
        return this.languageName + "." + extensionName;
    }

    /**
     * Get the default filename of the language.
     * It should be .txt but is not ensured.
     *
     * @return The filename with extension.
     */
    public final @NotNull String getDefaultFilename() {
        return this.getFileWithExtension("txt");
    }

    /**
     * Get the name of this language.
     *
     * @return The language's name.
     */
    @Override
    public final @NotNull String getName() {
        return this.languageName;
    }

    /**
     * Get the URL to download this language file.
     *
     * @return The URL.
     */
    @Override
    public final @NotNull String getUrl() {
        return this.languageUrl;
    }
}
