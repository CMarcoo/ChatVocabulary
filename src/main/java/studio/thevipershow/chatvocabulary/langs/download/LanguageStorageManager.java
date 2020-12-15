package studio.thevipershow.chatvocabulary.langs.download;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.thevipershow.chatvocabulary.langs.Language;

public interface LanguageStorageManager<T extends Enum<T> & Language> {

    /**
     * Get all of the currently saved languages text strings.
     *
     * @return The map with text mapped to each language.
     */
    @NotNull
    Map<T, String> getSavedLanguagesText();

    /**
     * Take a language data and export it to the plugin's folder.
     *
     * @param language The language.
     * @param replace  Whether we should remove previous files
     *                 from the same language or not.
     */
    void exportToBukkitDirectory(@NotNull T language, boolean replace);

    /**
     * Clear all of the current data for languages.
     */
    default void clearLanguagesData() {
        getSavedLanguagesText().clear();
    }

    /**
     * Put new data for a given language.
     *
     * @param language The language.
     * @param data     The data.
     */
    default void putLanguageData(@NotNull T language, @NotNull String data) {
        getSavedLanguagesText().put(language, data);
    }

    /**
     * Get the data mapped to a language.
     *
     * @param language The language.
     * @return The data if mapped, null otherwise.
     */
    @Nullable
    default String getLanguageData(@NotNull T language) {
        return getSavedLanguagesText().get(language);
    }
}
