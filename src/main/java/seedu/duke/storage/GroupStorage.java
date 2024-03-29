//@@ author hafizuddin-a
package seedu.duke.storage;

import seedu.duke.Expense;
import seedu.duke.Group;
import seedu.duke.User;
import seedu.duke.exceptions.GroupLoadException;
import seedu.duke.exceptions.GroupSaveException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Represents the storage manager for group data.
 * Handles the saving and loading of group information to and from files.
 */
public class GroupStorage {
    private static final String MEMBERS_HEADER = "Members:";
    private static final String EXPENSES_HEADER = "Expenses:";

    private static FileIO fileIO;

    public GroupStorage(FileIO fileIO) {
        this.fileIO = fileIO;
    }

    /**
     * Saves the group information to a file.
     *
     * @param group The group to save.
     * @throws GroupSaveException If an error occurs while saving the group information.
     */
    public static void saveGroupToFile(Group group) throws GroupSaveException {
        String groupName = group.getGroupName();
        List<User> members = group.getMembers();
        List<Expense> expenses = group.getExpenseList();

        try {
            GroupFilePath.createGroupDirectory();
            String filePath = GroupFilePath.getFilePath(groupName);
            BufferedWriter writer = fileIO.getFileWriter(filePath);

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
        } catch (IOException e) {
            throw new GroupSaveException("An error occurred while saving the group information.");
        }
    }

    /**
     * Loads the group information from a file.
     *
     * @param groupName The name of the group to load.
     * @return The loaded group, or null if the group file does not exist.
     * @throws GroupLoadException If an error occurs while loading the group information.
     */
    public static Group loadGroupFromFile(String groupName) throws GroupLoadException {
        String filePath = GroupFilePath.getFilePath(groupName);

        try {
            BufferedReader reader = fileIO.getFileReader(filePath);

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

            reader.close();
            return group;
        } catch (IOException e) {
            throw new GroupLoadException("An error occurred while loading the group information.");
        }
    }
}
