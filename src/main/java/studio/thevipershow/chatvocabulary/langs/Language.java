package studio.thevipershow.chatvocabulary.langs;

import org.jetbrains.annotations.NotNull;

/**
 * Represent a Language.
 */
public interface Language extends LanguageUrl {

    /**
     * Get the name of this language.
     *
     * @return The language's name.
     */
    @NotNull
    String getName();
}
