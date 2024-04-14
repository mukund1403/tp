//@@author avrilgk

package seedu.duke;

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

    @Test
    public void testGetOrCreateGroup() {
        String groupName = "TestGroup";
        Optional<Group> group = Group.getOrCreateGroup(groupName);
        assertTrue(group.isPresent(), "Group should be created");
        assertEquals(groupName, group.get().getGroupName(), "Group name should match the expected name");
    }

    @Test
    public void testEnterGroup() {
        String groupName = "TestGroup";
        Group.getOrCreateGroup(groupName);
        Group.enterGroup(groupName);
        assertEquals(groupName, Group.currentGroupName.get(), "Current group name should match the expected name");
    }

    @Test
    public void testExitGroup() {
        String groupName = "TestGroup";
        Group.getOrCreateGroup(groupName);
        Group.enterGroup(groupName);
        Group.exitGroup(groupName);
        assertTrue(Group.currentGroupName.isEmpty(), "Current group name should be empty");
    }

    @Test
    public void testNullGroup() {
        String groupName = "TestGroup";
        Group.getOrCreateGroup(groupName);
        Group.enterGroup(groupName);
        Group.exitGroup(groupName);
        Group.exitGroup(groupName);
        assertTrue(Group.currentGroupName.isEmpty(), "Current group name should be empty");
    }

    @Test
    public void testAddMember() {
        String groupName = "TestGroup";
        Group.getOrCreateGroup(groupName);
        Group.enterGroup(groupName);
        Group.getCurrentGroup().get().addMember("Alice");
        assertTrue(Group.getCurrentGroup().get().isMember("Alice"), "Alice should be a member of the group");
    }

    @Test
    public void testAddMultipleMembers() {
        String groupName = "TestGroup";
        Group.getOrCreateGroup(groupName);
        Group.enterGroup(groupName);
        Group.getCurrentGroup().get().addMember("Alice");
        Group.getCurrentGroup().get().addMember("Bob");
        assertTrue(Group.getCurrentGroup().get().isMember("Alice"), "Alice should be a member of the group");
        assertTrue(Group.getCurrentGroup().get().isMember("Bob"), "Bob should be a member of the group");
    }
}