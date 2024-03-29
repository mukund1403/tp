package seedu.duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents the file path management for group files.
 * Provides methods to get the file path for a group and create the groups directory.
 */
public class GroupFilePath {
    private static final String GROUPS_DIRECTORY = "data/groups";
    private static final String GROUP_FILE_EXTENSION = ".txt";

    /**
     * Returns the file path for the group file.
     *
     * @param groupName The name of the group.
     * @return The file path for the group file.
     */
    public static String getFilePath(String groupName) {
        return GROUPS_DIRECTORY + "/" + groupName + GROUP_FILE_EXTENSION;
    }

    /**
     * Creates the groups directory if it does not exist.
     *
     * @throws IOException If an I/O error occurs while creating the directory.
     */
    public static void createGroupDirectory() throws IOException {
        Path path = Paths.get(GROUPS_DIRECTORY);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
}
