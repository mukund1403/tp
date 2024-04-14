package seedu.duke;

import seedu.duke.exceptions.ExpensesException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//@@author Cohii2
public class Balance {
    protected String userName;
    protected Map<String, Float> balanceList;

    public Balance(String userName, Group group) {
        this(userName, group.getExpenseList(), group.getMembers());
    }

    /**
     * Populates balanceList with list of Users and list of Expenses in Group.
     *
     * @param userName The name of User to print the balance of.
     * @param expenses The list of Expenses in Group.
     * @param users The list of Users in Group
     */
    public Balance(String userName, List<Expense> expenses, List<User> users) {
        this.userName = userName;
        this.balanceList = new HashMap<>();

        // Populate balanceList with other Users from Group
        for (User user : users) {
            if (!user.getName().equals(userName)) {
                balanceList.put(user.getName(), 0f);
            }
        }

        // Add Expenses to balanceList
        for (Expense expense : expenses) {
            addExpense(expense);
        }
    }

    public Map<String, Float> getBalanceList() {
        return balanceList;
    }

    /**
     * Adds an Expense to balanceList.
     *
     * @param expense The Expense to be added.
     */
    private void addExpense(Expense expense) {
        String payerName = expense.getPayerName();
        List<Pair<String, Money>> payees = expense.getPayees();


        if(payerName.equals(userName)) {
            for(Pair<String, Money> payee : payees) {

                String payeeName = payee.getKey();
                Float payeeAmount = payee.getValue().getAmount();

                if (payeeName.equals(userName)) {
                    continue;
                }

                Float currentOwed = balanceList.get(payeeName);
                Float newOwed = currentOwed + payeeAmount;

                balanceList.put(payeeName, newOwed);
            }
        } else {

            for(Pair<String, Money> payee : payees) {
                String payeeName = payee.getKey();
                Float payeeAmount = payee.getValue().getAmount();

                if (!payeeName.equals(userName)) {
                    continue;
                }

                Float currentOwed = balanceList.get(payerName);
                Float newOwed = currentOwed - payeeAmount;

                balanceList.put(payerName, newOwed);
                break;
            }
        }
    }

    public void printBalance() {
        StringBuilder printOutput = new StringBuilder();
        printOutput.append(String.format("User %s's Balance List:", userName));

        for (Map.Entry<String, Float> entry : balanceList.entrySet()) {
            if (entry.getValue() > 0f) {
                printOutput.append(String.format(
                        "\n  %s owes %s : %.2f", entry.getKey(), userName, entry.getValue()
                ));
            }
            else {
                printOutput.append(String.format(
                        "\n  %s owes %s : %.2f", userName, entry.getKey(), -entry.getValue()
                ));
            }
        }

        printOutput.append("\nEnd of Balance List");
        UserInterface.printMessage(printOutput.toString(), MessageType.SUCCESS);
    }
}
