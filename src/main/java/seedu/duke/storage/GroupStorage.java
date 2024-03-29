//@@ author hafizuddin-a
package seedu.duke.storage;

import seedu.duke.Expense;
import seedu.duke.Group;
import seedu.duke.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GroupStorage {
    private static final String GROUPS_DIRECTORY = "data/groups";
    private static final String GROUP_FILE_EXTENSION = ".txt";
    private static final String MEMBERS_HEADER = "Members:";
    private static final String EXPENSES_HEADER = "Expenses:";

    /**
     * Saves the group information to a file.
     *
     * @param group The group to save.
     */
    public static void saveGroup(Group group) {
        String groupName = group.getGroupName();
        List<User> members = group.getMembers();
        List<Expense> expenses = group.getExpenseList();

        try {
            createGroupDirectory();
            String filePath = getGroupFilePath(groupName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            // Write group name
            writer.write(groupName);
            writer.newLine();

            // Write members
            writer.write(MEMBERS_HEADER);
            writer.newLine();
            for (User member : members) {
                writer.write(member.getName());
                writer.newLine();
            }

            // Write expenses
            writer.write(EXPENSES_HEADER);
            writer.newLine();
            for (Expense expense : expenses) {
                String expenseData = String.format("%.2f,%s,%s", expense.getTotalAmount(), expense.getPayerName(),
                        String.join(",", expense.getPayees()));
                writer.write(expenseData);
                writer.newLine();
            }

            writer.close();
            System.out.println("Group information saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the group information.");
        }
    }

    /**
     * Loads the group information from a file.
     *
     * @param groupName The name of the group to load.
     * @return The loaded group, or null if the group file does not exist.
     */
    public static Group loadGroup(String groupName) {
        String filePath = getGroupFilePath(groupName);
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            System.out.println("Group file does not exist.");
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String name = reader.readLine();
            Group group = Group.getOrCreateGroup(name).orElse(null);

            // Skip the "Members:" header
            reader.readLine();

            // Read members
            String line;
            while ((line = reader.readLine()) != null && !line.equals(EXPENSES_HEADER)) {
                group.addMember(line);
            }

            // Read expenses
            while ((line = reader.readLine()) != null) {
                String[] expenseData = line.split(",");
                float totalAmount = Float.parseFloat(expenseData[0]);
                String payerName = expenseData[1];
                String[] payeeList = expenseData[2].split(",");
                Expense expense = new Expense(payerName, totalAmount, payeeList);
                group.addExpense(expense);
            }

            System.out.println("Group information loaded successfully.");
            return group;
        } catch (IOException e) {
            System.out.println("An error occurred while loading the group information.");
        }

        return null;
    }

    /**
     * Creates the groups directory if it does not exist.
     */
    private static void createGroupDirectory() {
        Path path = Paths.get(GROUPS_DIRECTORY);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("An error occurred while creating the groups directory.");
            }
        }
    }

    /**
     * Returns the file path for the group file.
     *
     * @param groupName The name of the group.
     * @return The file path for the group file.
     */
    private static String getGroupFilePath(String groupName) {
        return GROUPS_DIRECTORY + "/" + groupName + GROUP_FILE_EXTENSION;
    }
}
