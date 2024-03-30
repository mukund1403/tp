package seedu.duke.storage;

import seedu.duke.Expense;
import seedu.duke.Group;
import seedu.duke.User;
import seedu.duke.exceptions.GroupLoadException;
import seedu.duke.exceptions.GroupSaveException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the storage manager for group data.
 * Handles the saving and loading of group information to and from files.
 */
public class GroupStorage {
    private static final String MEMBERS_HEADER = "Members:";
    private static final String EXPENSES_HEADER = "Expenses:";
    private static final String EXPENSE_DELIMITER = ",";

    private final FileIO fileIO;

    /**
     * Constructs a GroupStorage object with the specified FileIO dependency.
     *
     * @param fileIO the FileIO instance for file input/output operations
     */
    public GroupStorage(FileIO fileIO) {
        this.fileIO = fileIO;
    }

    /**
     * Saves the group information to a file.
     *
     * @param group the group to save
     * @throws GroupSaveException if an error occurs while saving the group information
     */
    public void saveGroupToFile(Group group) throws GroupSaveException {
        try {
            GroupFilePath.createGroupDirectory();
            String filePath = GroupFilePath.getFilePath(group.getGroupName());
            BufferedWriter writer = fileIO.getFileWriter(filePath);

            saveGroupName(writer, group.getGroupName());
            saveMembers(writer, group.getMembers());
            saveExpenses(writer, group.getExpenseList());

            writer.close();
        } catch (IOException e) {
            throw new GroupSaveException("An error occurred while saving the group information.");
        }
    }

    /**
     * Saves the group name to the file.
     *
     * @param writer    the BufferedWriter for writing to the file
     * @param groupName the name of the group
     * @throws IOException if an I/O error occurs while writing to the file
     */
    private void saveGroupName(BufferedWriter writer, String groupName) throws IOException {
        writer.write(groupName);
        writer.newLine();
    }

    /**
     * Saves the group members to the file.
     *
     * @param writer  the BufferedWriter for writing to the file
     * @param members the list of members in the group
     * @throws IOException if an I/O error occurs while writing to the file
     */
    private void saveMembers(BufferedWriter writer, List<User> members) throws IOException {
        writer.write(MEMBERS_HEADER);
        writer.newLine();
        for (User member : members) {
            writer.write(member.getName());
            writer.newLine();
        }
    }

    /**
     * Saves the group expenses to the file.
     *
     * @param writer   the BufferedWriter for writing to the file
     * @param expenses the list of expenses in the group
     * @throws IOException if an I/O error occurs while writing to the file
     */
    private void saveExpenses(BufferedWriter writer, List<Expense> expenses) throws IOException {
        writer.write(EXPENSES_HEADER);
        writer.newLine();
        for (Expense expense : expenses) {
            String expenseData = String.format("%.2f%s%s%s%s",
                    expense.getTotalAmount(), EXPENSE_DELIMITER,
                    expense.getPayerName(), EXPENSE_DELIMITER,
                    String.join(EXPENSE_DELIMITER, expense.getPayees()));
            writer.write(expenseData);
            writer.newLine();
        }
    }

    /**
     * Loads the group information from a file.
     *
     * @param groupName the name of the group to load
     * @return the loaded group
     * @throws GroupLoadException if an error occurs while loading the group information
     */
    public Group loadGroupFromFile(String groupName) throws GroupLoadException {
        try {
            String filePath = GroupFilePath.getFilePath(groupName);
            BufferedReader reader = fileIO.getFileReader(filePath);

            Group group = loadGroupName(reader);
            loadMembers(reader, group);
            loadExpenses(reader, group);

            reader.close();
            return group;
        } catch (IOException e) {
            throw new GroupLoadException("An error occurred while loading the group information.");
        }
    }

    /**
     * Loads the group name from the file.
     *
     * @param reader the BufferedReader for reading from the file
     * @return the loaded group
     * @throws IOException if an I/O error occurs while reading from the file
     */
    private Group loadGroupName(BufferedReader reader) throws IOException {
        String name = reader.readLine();
        return Group.getOrCreateGroup(name).orElse(null);
    }

    /**
     * Loads the group members from the file.
     *
     * @param reader the BufferedReader for reading from the file
     * @param group  the group to add the loaded members to
     * @throws IOException if an I/O error occurs while reading from the file
     */
    private void loadMembers(BufferedReader reader, Group group) throws IOException {
        // Skip the "Members:" header
        reader.readLine();

        String line;
        while ((line = reader.readLine()) != null && !line.equals(EXPENSES_HEADER)) {
            group.addMember(line);
        }
    }

    /**
     * Loads the group expenses from the file.
     *
     * @param reader the BufferedReader for reading from the file
     * @param group  the group to add the loaded expenses to
     * @throws IOException if an I/O error occurs while reading from the file
     */
    private void loadExpenses(BufferedReader reader, Group group) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] expenseData = line.split(EXPENSE_DELIMITER, 3);
            float totalAmount = Float.parseFloat(expenseData[0]);
            String payerName = expenseData[1];
            String[] payeeNames = expenseData[2].split(EXPENSE_DELIMITER);

            List<String> payeeList = new ArrayList<>();
            Collections.addAll(payeeList, payeeNames);

            Expense expense = new Expense(payerName, totalAmount, payeeList.toArray(new String[0]));
            group.addExpense(expense);
        }
    }
}
