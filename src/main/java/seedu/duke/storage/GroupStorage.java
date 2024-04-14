package seedu.duke.storage;

import seedu.duke.Group;
import seedu.duke.Money;
import seedu.duke.User;
import seedu.duke.Expense;
import seedu.duke.Pair;
import seedu.duke.CurrencyConversions;

import seedu.duke.commands.ExpenseCommand;
import seedu.duke.exceptions.ExpensesException;
import seedu.duke.exceptions.GroupDeleteException;
import seedu.duke.exceptions.GroupLoadException;
import seedu.duke.exceptions.GroupSaveException;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the storage manager for group data.
 * Handles the saving and loading of group information to and from files.
 */
public class GroupStorage {
    public static boolean isLoading = false;

    private static final String MEMBERS_HEADER = "Members:";
    private static final String EXPENSES_HEADER = "Expenses:";
    private static final String EXPENSE_DELIMITER = "◇";
    private static final String PAYEE_DELIMITER = ":";
    private static final String PAYEE_DATA_DELIMITER = ",";

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
            StringBuilder expenseData = new StringBuilder();
            expenseData.append(expense.getTotalAmount()).append(EXPENSE_DELIMITER)
                    .append(expense.getCurrency()).append(EXPENSE_DELIMITER)
                    .append(expense.getPayerName()).append(EXPENSE_DELIMITER)
                    .append(expense.getDescription()).append(EXPENSE_DELIMITER);

            List<String> payeeData = new ArrayList<>();
            for (Pair<String, Money> payee : expense.getPayees()) {
                payeeData.add(payee.getKey() + PAYEE_DELIMITER + payee.getValue().getAmount()
                        + PAYEE_DELIMITER + payee.getValue().getCurrency());
            }
            expenseData.append(String.join(PAYEE_DATA_DELIMITER, payeeData));

            writer.write(expenseData.toString());
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
        isLoading = true;
        try {
            String filePath = GroupFilePath.getFilePath(groupName);
            BufferedReader reader = fileIO.getFileReader(filePath);

            Group group = loadGroupName(reader);
            if (group == null) {
                throw new GroupLoadException("Invalid group data file. Unable to load group name.");
            }

            try {
                loadMembers(reader, group);
                loadExpenses(reader, group);
            } catch (IOException e) {
                throw new GroupLoadException("Error loading group members or expenses: " + e.getMessage());
            }

            reader.close();
            return group;
        } catch (IOException e) {
            throw new GroupLoadException("An error occurred while loading the group: " + e.getMessage());
        } catch (Exception e) {
            throw new GroupLoadException("An unexpected error occurred while loading the group: " + e.getMessage());
        } finally {
            isLoading = false;
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
        String line = reader.readLine();
        if (line != null && !line.isEmpty()) {
            return new Group(line.trim());
        }
        return null;
    }

    /**
     * Loads the group members from the file.
     *
     * @param reader the BufferedReader for reading from the file
     * @param group  the group to add the loaded members to
     * @throws IOException if an I/O error occurs while reading from the file
     */
    private void loadMembers(BufferedReader reader, Group group) throws IOException {
        String header = reader.readLine();
        if (header == null || !header.equals(MEMBERS_HEADER)) {
            throw new IOException("Invalid group data file. Missing or invalid 'Members:' header.");
        }

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
    private void loadExpenses(BufferedReader reader, Group group) throws IOException, ExpensesException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] expenseData = line.split(EXPENSE_DELIMITER, 5);
            float totalAmount = Float.parseFloat(expenseData[0]);
            String currencyString = expenseData[1];
            String payerName = expenseData[2];
            String description = expenseData[3];
            String[] payeeData = expenseData[4].split(PAYEE_DATA_DELIMITER);

            CurrencyConversions currency = ExpenseCommand.getCurrency(currencyString);
            ArrayList<Pair<String, Money>> payeeList = new ArrayList<>();
            for (String payee : payeeData) {
                String[] payeeInfo = payee.split(PAYEE_DELIMITER);
                String payeeName = payeeInfo[0];
                float amountDue = Float.parseFloat(payeeInfo[1]);
                Money amountAndCurrency = new Money(amountDue, currency);
                payeeList.add(new Pair<>(payeeName, amountAndCurrency));
            }

            try {

                Money amountAndCurrency = new Money(totalAmount, currency);
                Expense expense = new Expense(false, payerName, description, amountAndCurrency, payeeList);
                group.addExpense(expense);
            } catch (ExpensesException e) {
                System.out.println("Error loading expense: " + e.getMessage());
            }
        }
    }

    /**
     * Deletes the group file for the specified group name.
     *
     * @param groupName the name of the group whose file needs to be deleted
     * @throws GroupDeleteException if an error occurs while deleting the group file
     */
    public void deleteGroupFile(String groupName) throws GroupDeleteException {
        try {
            String filePath = GroupFilePath.getFilePath(groupName);
            boolean deleted = fileIO.deleteFile(filePath);
            if (!deleted) {
                throw new GroupDeleteException("Failed to delete the group file.");
            }
        } catch (IOException e) {
            throw new GroupDeleteException("An error occurred while deleting the group file: " + e.getMessage());
        }
    }
}
