package studio.thevipershow.chatvocabulary.langs.download;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.chatvocabulary.ChatVocabulary;
import studio.thevipershow.chatvocabulary.langs.StandardLanguage;

public final class StandardLanguageStorageManager extends AbstractLanguageStorageManager<StandardLanguage> {

    private final Map<StandardLanguage, Boolean> lockedFiles = new EnumMap<>(StandardLanguage.class);

    private StandardLanguageStorageManager(@NotNull ChatVocabulary chatVocabulary) {
        super(StandardLanguage.class);
        this.chatVocabulary = chatVocabulary;
    }

    private final ChatVocabulary chatVocabulary;
    private static StandardLanguageStorageManager instance = null;

    /**
     * Get the global instance of StandardLanguageStorageManager.
     *
     * @param chatVocabulary The ChatVocabulary instance.
     * @return The global instance.
     */
    @NotNull
    public static StandardLanguageStorageManager getInstance(@NotNull ChatVocabulary chatVocabulary) {
        if (instance == null) {
            instance = new StandardLanguageStorageManager(chatVocabulary);
        }
        return instance;
    }

    /**
     * Take a language data and export it to the plugin's folder.
     *
     * @param language The language.
     * @param replace  Whether we should remove previous files
     * @throws UnsupportedOperationException when someone is trying to export the same language too many times
     */
    @Override
    public final void exportToBukkitDirectory(@NotNull StandardLanguage language, boolean replace) {
        var lockStatus = this.lockedFiles.getOrDefault(language, false);

        if (lockStatus) {
            throw new UnsupportedOperationException(String.format("You cannot export the vocabulary file %s more times while it is already copying!", language.getName()));
        } else {
            this.lockedFiles.put(language, true);
        }

        var targetFile = new File(this.chatVocabulary.getDataFolder(), language.getDefaultFilename());
        final var data = Objects.requireNonNull(this.savedLanguageTextMap.get(language), String.format("Data for language %s was null.", language.getName()));
        final var stringList = data.split("\\r?\\n");
        final var stringArrayList = new ArrayList<String>(stringList.length);

        for (int i = 0; i < stringList.length; i++) {
            stringArrayList.add(i, stringList[i].stripTrailing()); // copying and removing spaces
        }

        try {
            if (targetFile.exists() && replace) {
                FileUtils.writeLines(targetFile, "ISO-8859-1", stringArrayList);
            } else if (!targetFile.exists()) {
                var dataFolder = this.chatVocabulary.getDataFolder();
                if (!dataFolder.exists()) {
                    dataFolder.mkdirs();
                }

                this.chatVocabulary.getLogger().info("Creating new language file at " + targetFile.getCanonicalPath());
                var result = targetFile.createNewFile();
                if (result && targetFile.canRead()) {
                    FileUtils.writeLines(targetFile, "ISO-8859-1", stringArrayList);
                } else {
                    throw new IOException(String.format("Could not create file %s or read it.", targetFile.getAbsolutePath()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.lockedFiles.put(language, false);
        }
    }
}
