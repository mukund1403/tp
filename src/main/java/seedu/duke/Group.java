//@@author avrilgk

package seedu.duke;

import seedu.duke.exceptions.GroupLoadException;
import seedu.duke.exceptions.GroupSaveException;
import seedu.duke.storage.GroupNameChecker;
import seedu.duke.storage.GroupStorage;
import seedu.duke.storage.FileIOImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Group {
    static Map<String, Group> groups = new HashMap<>();
    static Optional<String> currentGroupName = Optional.empty();
    private static final GroupStorage groupStorage = new GroupStorage(new FileIOImpl());

    private static List<User> members = null;

    private static GroupNameChecker groupNameChecker = new GroupNameChecker();

    private final String groupName;

    private final List<Expense> expenseList;

    public Group(String groupName) {
        this.groupName = groupName;
        this.members = new ArrayList<>();
        this.expenseList = new ArrayList<>();
    }

    /**
     * Retrieves an existing group by its name or creates a new one if it does not exist.
     * It ensures that a user cannot create or join a new group without exiting their current group.
     *
     * @param groupName The name of the group to get or create.
     * @return The existing or newly created group.
     * @throws IllegalStateException If trying to create or join a new group while already in another group.
     */

    //@@ author avrilgk
    public static Optional<Group> getOrCreateGroup(String groupName) {

        // Check if group name is empty
        if (groupName == null || groupName.trim().isEmpty()) {
            System.out.println("Group name cannot be empty. Please try again.");
            return Optional.empty();
        }

        if (currentGroupName.isPresent()) {
            System.out.println("You are currently in " + currentGroupName.get() +
                    ". Exit current group before creating another one.");
            return Optional.empty();
        }

        Optional<Group> group = Optional.ofNullable(groups.get(groupName));

        // Check if user is accessing a group they are already in
        if (group.isPresent() && getCurrentGroup().isPresent() && getCurrentGroup().get().equals(group.get())) {
            System.out.println("You are already in " + groupName);
            return Optional.empty();
        }

        // Create a new group if it doesn't exist
        if (group.isEmpty() && !groupNameChecker.doesGroupNameExist(groupName)) {
            Group newGroup = new Group(groupName);
            groups.put(groupName, newGroup);
            System.out.println(groupName + " created.");
            currentGroupName = Optional.of(groupName);
            group = Optional.of(newGroup);
            System.out.println("You are now in " + groupName);
        } else if (groupNameChecker.doesGroupNameExist(groupName)) {
            System.out.println("Group already exists. Use 'enter " + groupName + "' to enter the group.");
            return Optional.empty();
        }

        assert group.isPresent() : "Group should be created and present";
        assert currentGroupName.isPresent() : "Current group name should be set";
        assert currentGroupName.get().equals(groupName) : "Current group name should match " +
                "the created or retrieved group";
        assert groups.containsKey(groupName) : "Groups map should contain the new or retrieved group";

        return group;
    }

    /**
     * Enter existing group.
     *
     * @param groupName The name of the group to enter.
     * @return The existing group.
     */
    public static Optional<Group> enterGroup(String groupName) {

        if (groupName == null || groupName.trim().isEmpty()) {
            System.out.println("Group name cannot be empty. Please try again.");
            return Optional.empty();
        }

        if (currentGroupName.isPresent()) {
            System.out.println("You are currently in " + currentGroupName.get() +
                    ". Exit current group before entering another one.");
            return Optional.empty();
        }

        Optional<Group> group = Optional.ofNullable(groups.get(groupName));
        if (group.isEmpty()) {
            //@@author hafizuddin-a
            GroupNameChecker groupNameChecker = new GroupNameChecker();
            if (!groupNameChecker.doesGroupNameExist(groupName)) {
                System.out.println("Group does not exist.");
                return Optional.empty();
            }

            try {
                // If the group doesn't exist in memory, try loading it from file
                Optional<Group> loadedGroup = Optional.ofNullable(groupStorage.loadGroupFromFile(groupName));
                if (loadedGroup.isPresent()) {
                    groups.put(groupName, loadedGroup.get());
                    group = loadedGroup;
                } else {
                    //@@ author avrilgk
                    System.out.println("Unable to load group from file.");
                    return Optional.empty();
                }
                // @@author hafizuddin-a
            } catch (GroupLoadException e) {
                String errorMessage = e.getMessage();
                if (errorMessage == null) {
                    errorMessage = "Failed to load group from file.";
                }
                System.out.println(errorMessage);
                return Optional.empty();
            }
        }
        //@@author avrilgk
        currentGroupName = Optional.of(groupName);
        System.out.println("You are now in " + groupName); // don't change this to cat
        return group;
    }

    /**
     * Exits the current group.
     * If the user is not in any group, it displays a message asking the user to try again.
     */
    public static void exitGroup(String groupName) {

        if (currentGroupName.isPresent()) {
            if (!currentGroupName.get().equals(groupName)) {
                System.out.println("Invalid group name. Please enter the correct group name.");
                return;
            }
            //@@author hafizuddin-a
            try {
                groupStorage.saveGroupToFile(groups.get(currentGroupName.get()));
                System.out.println("Group data saved successfully.");
            } catch (GroupSaveException e) {
                System.out.println("Error saving group: " + e.getMessage());
            }
            //@@author avrilgk
            System.out.println("You have exited " + currentGroupName.get() + ".");
            currentGroupName = Optional.empty();
        } else {
            System.out.println("You are not currently in a group.");
        }
    }

    /**
     * Retrieves the current group.
     *
     * @return The current group, or null if the user is not in any group.
     */
    public static Optional<Group> getCurrentGroup() {
        return currentGroupName.map(groups::get);
    }

    /**
     * Checks if the user is currently in a group.
     *
     * @return true if the user is in a group, false otherwise.
     */
    public static boolean isInGroup() {
        return currentGroupName.isPresent();
    }

    /**
     * Checks if a user with the given name is a member of the group.
     *
     * @param memberName The name of the member to check.
     * @return true if the user is a member of the group, false otherwise.
     */
    public static boolean isMember(String memberName) {
        for (User member : members) {
            if (member.getName().equalsIgnoreCase(memberName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new member to the group.
     *
     * @param memberName The name of the member to add.
     * @return The newly added user, or null if the user is already a member of the group.
     */
    public User addMember(String memberName) {
        if (memberName == null || memberName.isEmpty()) {
            UserInterface.printMessage("Please provide a valid member name.", MessageType.ERROR);
            return null;
        }

        if (isMember(memberName)) {
            UserInterface.printMessage(memberName + " is already a member of " + groupName + ".", MessageType.ERROR);
            return null;
        }

        User newMember = new User(memberName);
        members.add(newMember);
        if (!GroupStorage.isLoading) {
            UserInterface.printMessage(memberName + " has been added to " + groupName + ".", MessageType.SUCCESS);
        }
        return newMember;
    }

    /**
     * Adds a new expense to the group.
     *
     * @param expense The Expense object to add.
     */
    public void addExpense(Expense expense) {
        expenseList.add(expense);
    }

    public void deleteExpense(int expenseIndex) {
        expenseList.remove(expenseIndex);
    }

    /**
     * Retrieves the name of the group.
     *
     * @return The name of the group.
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Retrieves the list of members in the group.
     *
     * @return The list of members in the group.
     */
    public List<User> getMembers() {
        return new ArrayList<>(members);
    }

    /**
     * Retrieves the list of expenses in the group.
     *
     * @return The list of expenses in the group.
     */
    public List<Expense> getExpenseList() {
        return new ArrayList<>(expenseList);
    }

    /**
     * Settles the outstanding amount between two users.
     *
     * @param payerName The name of the user who will pay the outstanding amount.
     * @param payeeName The name of the user who will receive the outstanding amount.
     */
    //@@author avrilgk
    public void settle(String payerName, String payeeName) {
        User payer = findUser(payerName);
        User payee = findUser(payeeName);

        if (payer == null || payee == null) {
            UserInterface.printMessage("User not found.", MessageType.ERROR);
            return;
        }

        Balance balance = new Balance(payee.getName(), expenseList, members);
        List<Money> moneyList = balance.getBalanceList().get(payer.getName());

        Money total = new Money(0f, CurrencyConversions.SGD);
        for (Money money : moneyList) {
            total = total.addition(money, CurrencyConversions.SGD);
        }

        if (total.getAmount() <= 0f) {
            UserInterface.printMessage(
                    payerName + " has no outstanding balance with " + payeeName
            );
            return;
        }

        if (total.getAmount() > 0f) {
            UserInterface.printMessage(
                    payerName + " should pay " + payeeName + " " + String.format("SGD %.2f", total.getAmount())
            );
        }

        ArrayList<Pair<String, Money>> repaymentList = new ArrayList<>();
        for (Money money : moneyList) {
            repaymentList.add(new Pair<>(payee.getName(), money));
        }

        Expense repaymentExpense = new Expense(payer.getName(), "Settlement", total, repaymentList);
        expenseList.add(repaymentExpense);
        System.out.println(payerName + " has settled the full amount with " + payeeName);
    }

    public void settleAll(String payerName) {
        User payer = findUser(payerName);
        if (payer == null) {
            System.out.println("User not found.");
            return;
        }
        for (User member : members) {
            if (member.getName().equals(payerName)) {
                continue;
            }
            settle(payerName, member.getName());
        }
    }

    /**
     * Calculates the balance between two users.
     *
     * @param payerName The name of the user who will pay the outstanding amount.
     * @param payeeName The name of the user who will receive the outstanding amount.
     * @return The balance between the two users.
     */
    private float calculateBalance(String payerName, String payeeName) {
        ArrayList<Expense> payeeExpense = getPayerExpense(payeeName);
        ArrayList<Expense> payerExpense = getPayerExpense(payerName);

        float balance = 0;

        for (Expense expense : payeeExpense) {
            for (Pair<String, Money> payee : expense.getPayees()) {
                if (payee.getKey().equals(payerName)) {
                    balance += payee.getValue().getAmount();
                }
            }
        }

        for (Expense expense : payerExpense) {
            for (Pair<String, Money> payee : expense.getPayees()) {
                if (payee.getKey().equals(payeeName)) {
                    balance -= payee.getValue().getAmount();
                }
            }
        }

        return balance;
    }

    /**
     * Retrieves the expenses paid by a user.
     *
     * @param payerName The name of the user to retrieve expenses for.
     * @return The list of expenses paid by the user.
     */
    public ArrayList<Expense> getPayerExpense(String payerName) {
        ArrayList<Expense> payerExpenses = new ArrayList<>();

        for (Expense expense : expenseList) {
            if (expense.getPayerName().equals(payerName)) {
                payerExpenses.add(expense);
            }
        }

        return payerExpenses;
    }

    /**
     * Finds a user by their name.
     *
     * @param userName The name of the user to find.
     * @return The user with the given name, or null if the user is not found.
     */

    private User findUser(String userName) {
        for (User user : members) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public static void removeGroup(String groupName) {
        groups.remove(groupName);
    }
}
