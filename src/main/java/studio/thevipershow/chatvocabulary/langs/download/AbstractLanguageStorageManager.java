package studio.thevipershow.chatvocabulary.langs.download;

import java.util.EnumMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.chatvocabulary.langs.Language;

public abstract class AbstractLanguageStorageManager<T extends Enum<T> & Language> implements LanguageStorageManager<T> {

    protected final Map<T, String> savedLanguageTextMap;

    public AbstractLanguageStorageManager(@NotNull Class<T> enumClass) {
        this.savedLanguageTextMap = new EnumMap<>(enumClass);
    }

    @Override
    public @NotNull Map<T, String> getSavedLanguagesText() {
        return this.savedLanguageTextMap;
    }
}
