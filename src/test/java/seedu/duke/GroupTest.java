//@@author avrilgk

package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupTest {
    @BeforeEach
    public void setup() {
        // Clear all groups and reset the current group name before each test
        Group.groups.clear();
        Group.currentGroupName = Optional.empty();
    }

    @AfterEach
    public void teardown() {
        // Optionally clear all after each test if needed
        Group.groups.clear();
        Group.currentGroupName = Optional.empty();
    }

    @Test
    public void testGroupCreation() {
        String expectedName = "GroupName";
        Group.groups.clear();
        Optional<Group> group = Group.getOrCreateGroup(expectedName);
        assertEquals(group.isPresent(), true, "Group should be created");
        assertEquals(expectedName, group.get().getGroupName(), "Group name should match the expected name");
    }

    @Test
    public void testGetOrCreateGroup() {
        String groupName = "NewGroup";
        Group.groups.clear(); // Ensure that the group does not exist before the test
        Optional<Group> newGroup = Group.getOrCreateGroup(groupName);
        assertEquals(newGroup.isPresent(), true, "New group should be created successfully");

        Group.exitGroup(groupName);
        Group.groups.clear(); // Ensure that the group does not exist before the test
        Optional<Group> existingGroup = Group.getOrCreateGroup(groupName);

        assertTrue(existingGroup.isPresent(), "Should retrieve the existing group");
        assertEquals(newGroup.get(), existingGroup.get(), "Should return the same group object for existing group");
    }

    @Test
    public void testExitGroup() {
        String groupName = "ExitingGroup";
        Group.groups.clear(); // Ensure that the group does not exist before the test
        Group.getOrCreateGroup(groupName);
        Group.exitGroup(groupName);
        assertTrue(Group.getCurrentGroup().isEmpty(), "Should successfully exit the group");
    }

    @Test
    public void testGetCurrentGroup() {
        String groupName = "CurrentGroup";
        Group.groups.clear(); // Ensure that the group does not exist before the test
        Group.getOrCreateGroup(groupName);
        Optional<Group> currentGroup = Group.getCurrentGroup();
        assertEquals(currentGroup.isPresent(), true, "Current group should not be empty");
        assertEquals(groupName, currentGroup.get().getGroupName(), "Current group should match the expected group");
    }
}