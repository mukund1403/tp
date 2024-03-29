package seedu.duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GroupFilePath {
    private static final String GROUPS_DIRECTORY = "data/groups";
    private static final String GROUP_FILE_EXTENSION = ".txt";

    public static String getFilePath(String groupName) {
        return GROUPS_DIRECTORY + "/" + groupName + GROUP_FILE_EXTENSION;
    }

    public static void createGroupDirectory() throws IOException {
        Path path = Paths.get(GROUPS_DIRECTORY);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
}
