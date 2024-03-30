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
    private static String groupsDirectory = "data/groups";
    private static final String GROUP_FILE_EXTENSION = ".txt";

    /**
     * Returns the file path for the group file.
     *
     * @param groupName The name of the group.
     * @return The file path for the group file.
     */
    public static String getFilePath(String groupName) {
        assert groupName != null && !groupName.isEmpty() : "Group name cannot be null or empty";
        return groupsDirectory + "/" + groupName + GROUP_FILE_EXTENSION;
    }

    /**
     * Creates the groups directory if it does not exist.
     *
     * @throws IOException If an I/O error occurs while creating the directory.
     */
    public static void createGroupDirectory() throws IOException {
        Path path = Paths.get(groupsDirectory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    /**
     * Sets the directory where group files are stored.
     * <p>
     * This method allows changing the default groups directory to a custom directory.
     * It is useful for testing purposes or when the groups need to be stored in a different location.
     * <p>
     * Assertions are used to check that the provided directory is not null or empty.
     * If the assertion fails, an {@code AssertionError} will be thrown, indicating a programming error.
     *
     * @param directory the directory where group files should be stored
     * @throws AssertionError if the provided directory is null or empty
     */
    public static void setGroupsDirectory(String directory) {
        assert directory != null && !directory.isEmpty() : "Groups directory cannot be null or empty";
        groupsDirectory = directory;
    }

    public static String getGroupsDirectory() {
        return groupsDirectory;
    }
}
