package seedu.duke.storage;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GroupNameChecker {

    /**
     * Checks if a specific group name exists in the saved files.
     *
     * @param groupNameToCheck the group name to check for
     * @return true if the group name exists, false otherwise
     */
    public boolean doesGroupNameExist(String groupNameToCheck) {
        Path groupsDirectory = getGroupsDirectoryPath();
        if (groupsDirectory == null) {
            return false;
        }
        return checkGroupNameInDirectory(groupsDirectory, groupNameToCheck);
    }

    /**
     * Gets the Path object for the groups directory.
     *
     * @return the Path object for the groups directory, or null if an error occurs
     */
    private Path getGroupsDirectoryPath() {
        try {
            return Paths.get(GroupFilePath.getGroupsDirectory());
        } catch (Exception e) {
            System.out.println("An error occurred while getting the groups directory: " + e.getMessage());
            return null;
        }
    }

    /**
     * Checks if the given group name exists within the specified directory.
     *
     * @param directoryPath    the path to the directory containing group files
     * @param groupNameToCheck the group name to check for
     * @return true if the group name exists, false otherwise
     */
    private boolean checkGroupNameInDirectory(Path directoryPath, String groupNameToCheck) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath, "*.txt")) {
            for (Path file : stream) {
                if (extractGroupNameFromFile(file).equals(groupNameToCheck)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while checking for the group name: " + e.getMessage());
        }
        return false;
    }

    /**
     * Extracts the group name from a file path.
     *
     * @param file the Path object of the file
     * @return the extracted group name
     */
    private String extractGroupNameFromFile(Path file) {
        String fileName = file.getFileName().toString();
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }
}


