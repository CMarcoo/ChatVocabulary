package studio.thevipershow.chatvocabulary.langs;

import org.jetbrains.annotations.NotNull;

/**
 * An URL to obtain a language.
 * Each language should have an URL that gives a link
 * to a .txt file with a list of language specific words
 * ordered alphabetically, and separated by a newline.
 */
public interface LanguageUrl {

    /**
     * Get the URL to download this language file.
     *
     * @return The URL.
     */
    @NotNull
    String getUrl();
}
