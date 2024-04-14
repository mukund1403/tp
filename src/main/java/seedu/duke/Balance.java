package seedu.duke;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//@@author Cohii2
public class Balance {
    protected String userName;
    protected Map<String, Float> balanceList;

    public Balance(String userName, Map<String, Float> userList) {
        this.userName = userName;
        this.balanceList = userList;
    }

    public Balance(String userName, Group group){
        this(userName, group.getExpenseList(), group.getMembers());
    }

    public Balance(String userName, List<Expense> expenses, List<User> users) {
        this.userName = userName;
        this.balanceList = new HashMap<>();

        // Populate balanceList with other Users from Group
        for (User user : users) {
            if(!user.getName().equals(userName)) {
                balanceList.put(user.getName(), 0f);
            }
        }

        // Add Expenses to balanceList
        for (Expense expense : expenses) {
            addExpense(expense);
        }
    }

    public String getUserName() {
        return userName;
    }

    public Map<String, Float> getBalanceList() {
        return balanceList;
    }

    private void addExpense(Expense expense) {
        String payerName = expense.getPayerName();
        List<Pair<String, Money>> payees = expense.getPayees();

        if(payerName.equals(userName)) {
            for(Pair<String, Money> payee : payees) {
                String payeeName = payee.getKey();
                Float payeeAmount = payee.getValue().getAmount();

                if(payeeName.equals(userName)){
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
        String firstLine = String.format("User %s's Balance List:", userName);
        System.out.println(firstLine);

        for (Map.Entry<String, Float> entry : balanceList.entrySet()) {
            String balanceLine = String.format("  %s : %.2f", entry.getKey(), entry.getValue());
            System.out.println(balanceLine);
        }

        System.out.println("End of Balance List");
    }
}
