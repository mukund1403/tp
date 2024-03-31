package seedu.duke.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.GroupLoadException;
import seedu.duke.exceptions.GroupSaveException;
import seedu.duke.Group;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GroupStorageTest {
    private static final String TEST_GROUPS_DIRECTORY = "src/test/data/groups";

    private GroupStorage groupStorage;

    @BeforeAll
    static void setUpTestDirectory() {
        GroupFilePath.setGroupsDirectory(TEST_GROUPS_DIRECTORY);
    }

    @BeforeEach
    void setUp() throws IOException {
        FileIO fileIO = new FileIOImpl();
        groupStorage = new GroupStorage(fileIO);

        // Create the test groups directory if it doesn't exist
        Files.createDirectories(Path.of(TEST_GROUPS_DIRECTORY));
    }

    @Test
    void saveGroupToFile_validGroup_successfullySaves() throws GroupSaveException {
        Group group = createSampleGroup();

        groupStorage.saveGroupToFile(group);

        Path filePath = Path.of(TEST_GROUPS_DIRECTORY, "sample_group.txt");
        assertTrue(Files.exists(filePath));
    }

    @Test
    void loadGroupFromFile_validFile_successfullyLoads() throws IOException, GroupLoadException {
        createSampleGroupFile();

        Group loadedGroup = groupStorage.loadGroupFromFile("sample_group");

        assertNotNull(loadedGroup);
        assertEquals("sample_group", loadedGroup.getGroupName());
        assertEquals(2, loadedGroup.getMembers().size());
    }

    @Test
    void loadGroupFromFile_nonExistentFile_throwsGroupLoadException() {
        assertThrows(GroupLoadException.class, () -> groupStorage.loadGroupFromFile("nonexistent_group"));
    }

    private Group createSampleGroup() {
        Group group = Group.getOrCreateGroup("sample_group").get();
        group.addMember("user1");
        group.addMember("user2");
        return group;
    }

    private void createSampleGroupFile() throws IOException {
        Path filePath = Path.of(TEST_GROUPS_DIRECTORY, "sample_group.txt");
        String fileContent = "sample_group\n" +
                "Members:\n" +
                "user1\n" +
                "user2\n" +
                "Expenses:\n";
        Files.writeString(filePath, fileContent);
    }
}
