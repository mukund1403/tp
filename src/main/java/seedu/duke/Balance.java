package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//@@author Cohii2
public class Balance {
    protected String userName;
    protected Map<String, List<Money>> balanceList;

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
                balanceList.put(user.getName(), new ArrayList<Money>());
            }
        }

        // Add Expenses to balanceList
        for (Expense expense : expenses) {
            addExpense(expense);
        }
    }

    public Map<String, List<Money>> getBalanceList() {
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
                Money payeeMoney = payee.getValue();

                if (payeeName.equals(userName)) {
                    continue;
                }

                List<Money> moneyList = balanceList.get(payeeName);
                addMoney(moneyList, payeeMoney);
            }
        } else {
            for(Pair<String, Money> payee : payees) {
                String payeeName = payee.getKey();
                Money payeeMoney = payee.getValue();

                if (!payeeName.equals(userName)) {
                    continue;
                }

                List<Money> moneyList = balanceList.get(payerName);
                subtractMoney(moneyList, payeeMoney);
            }
        }
    }

    /**
     * Adds a Money object to a List of Money.
     * If money shares a currency with an entry in moneyList, perform addition with the existing entry.
     * Else, append Money as a new entry to the List.
     *
     * @param moneyList The current List of Money.
     * @param money The Money object to be added.
     */
    public void addMoney(List<Money> moneyList, Money money){
        for(int i = 0; i < moneyList.size(); i++){
            CurrencyConversions currency = moneyList.get(i).getCurrency();
            boolean isSameCurrency = currency.equals(money.getCurrency());

            if(!isSameCurrency){
                continue;
            }

            Money oldItem = moneyList.get(i);
            Money newItem= oldItem.addition(money, currency);
            moneyList.set(i, newItem);
            return;
        }

        // no matching currency in moneyList
        moneyList.add(money);
    }

    /**
     * Subtracts a Money object from a List of Money.
     * If money shares a currency with an entry in moneyList, perform subtraction with the existing entry.
     * Else, append -Money as a new entry to the List.
     *
     * @param moneyList The current List of Money.
     * @param money The Money object to be subtracted.
     */
    public void subtractMoney(List<Money> moneyList, Money money){
        for(int i = 0; i < moneyList.size(); i++){
            CurrencyConversions currency = moneyList.get(i).getCurrency();
            boolean isSameCurrency = currency.equals(money.getCurrency());

            if(!isSameCurrency){
                continue;
            }

            Money oldItem = moneyList.get(i);
            Money newItem= oldItem.subtraction(money, currency);
            moneyList.set(i, newItem);
            return;
        }

        // no matching currency in moneyList
        moneyList.add(money.multiplication(-1f, money.getCurrency()));
    }

    public void printBalance() {
        StringBuilder printOutput = new StringBuilder();
        printOutput.append(String.format("User %s's Balance List:", userName));

        for (Map.Entry<String, List<Money>> entry : balanceList.entrySet()) {
            String user = entry.getKey();
            for(Money money : entry.getValue()){
                // if entry is less than $0.001 don't print it
                if(money.getAmount() <= 0.001f && money.getAmount() >= -0.001f){
                    continue;
                }
                printOutput.append(String.format("\n  %s : %s", user, money));
            }
        }

        printOutput.append("\nEnd of Balance List");
        UserInterface.printMessage(printOutput.toString(), MessageType.SUCCESS);
    }
}
