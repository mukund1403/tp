package seedu.duke.commands;
//@@author mukund1403

import seedu.duke.Group;
import seedu.duke.Pair;
import seedu.duke.CurrencyConversions;
import seedu.duke.Money;
import seedu.duke.Expense;
import seedu.duke.exceptions.ExpensesException;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;

public class ExpenseCommand {

    //@@author Cohii2
    public static void addExpense(HashMap <String, ArrayList<String>> params,String argument, String userInput)
            throws ExpensesException {
        Optional<Group> currentGroup = Group.getCurrentGroup();
        if (currentGroup.isEmpty()) {
            throw new ExpensesException("Not signed in to a Group! Use 'create <name>' to create Group");
        }

        String[] expenseParams = {"amount", "paid", "user"};
        for (String expenseParam : expenseParams) {
            if (params.get(expenseParam).isEmpty()) {
                throw new ExpensesException("No " + expenseParam + " for expenses! Add /" + expenseParam);
            }
        }
        //@@author mukund1403
        float totalAmount = getTotal(params);
        String currencyString;
        if(params.get("currency").isEmpty()){
            currencyString = "";
        } else {
            currencyString = params.get("currency").get(0);
        }

        CurrencyConversions currency = getCurrency(currencyString);

        Money amountAndCurrency = new Money(totalAmount, currency);
        ArrayList<String> payeeList = params.get("user");
        String payerName = params.get("paid").get(0);

        checkDescription(argument);

        Expense newTransaction;
        ArrayList<Pair<String, Money>> payees = new ArrayList<>();
        if(userInput.contains("/unequal")){
            newTransaction = addUnequalExpense(payeeList, payees, amountAndCurrency, payerName, argument);
        } else {
            newTransaction = addEqualExpense(payeeList, payees, amountAndCurrency, payerName, argument);
        }
        currentGroup.get().addExpense(newTransaction);
    }

    //@@author mukund1403

    /**
     * The method deletes expense from the expenses list
     * @param listIndex : The index from the list, the user wishes to delete (will be 1 indexed)
     */
    public static void deleteExpense(String listIndex) throws ExpensesException {
        Optional<Group> currentGroup = Group.getCurrentGroup();
        if (currentGroup.isEmpty()) {
            String exceptionMessage = "Not signed in to a Group! Use 'create <name>' to create Group";
            throw new ExpensesException(exceptionMessage);
        }
        List<Expense> expenseList = currentGroup.get().getExpenseList();
        int listSize = expenseList.size();
        int index = getListIndex(listIndex, listSize) - 1;
        String deletedExpenseDescription = expenseList.get(index).toString();
        currentGroup.get().deleteExpense(index);
        System.out.println("Deleted expense:\n" + deletedExpenseDescription);
    }

    public static Float getTotal(HashMap<String, ArrayList<String>> params) throws ExpensesException {
        float totalAmount;
        String amount = params.get("amount").get(0);
        try {
            totalAmount = Float.parseFloat(amount);
        } catch (NumberFormatException e) {
            String exceptionMessage = "Re-enter expense with amount as a proper number. (Good bug to start with tbh!)";
            throw new ExpensesException(exceptionMessage);
        }
        int maxNumberHandled = 2000000000;
        if(totalAmount <= 0){
            String exceptionMessage = "Expense amount cannot be 0 or a negative number " +
                    "(Can try using special characters. I have not handled that!)";
            throw new ExpensesException(exceptionMessage);
        } else if(totalAmount > maxNumberHandled) {
            String exceptionMessage = "This amount is too big for a small computer like me to handle :(. " +
                    "Please use a smaller amount";
            throw new ExpensesException(exceptionMessage);
        }
        return totalAmount;
    }

    public static CurrencyConversions getCurrency(String currencyString)
            throws ExpensesException {
        if(currencyString.isEmpty()){
            return CurrencyConversions.SGD;
        } else {
            switch(currencyString.toUpperCase()) {
            case "USD":
                return CurrencyConversions.USD;
            case "RMB":
                return CurrencyConversions.RMB;
            case "EUR":
                return CurrencyConversions.EUR;
            case "JPY":
                return CurrencyConversions.JPY;
            case "AUD":
                return CurrencyConversions.AUD;
            case "MYR":
                return CurrencyConversions.MYR;
            case "SGD":
                return CurrencyConversions.SGD;
            default:
                throw new ExpensesException("Sorry! Either you have entered the currency name incorrectly or" +
                        " the app does not currently support this currency :(");
            }
        }
    }

    public static int getListIndex(String listIndex, int listSize) throws ExpensesException {
        int index;
        try{
            index = Integer.parseInt(listIndex);
        } catch(NumberFormatException e){
            String exceptionMessage = "Enter a list index that is an Integer";
            throw new ExpensesException(exceptionMessage);
        }

        if(index > listSize){
            String exceptionMessage = "List index is greater than list size";
            throw new ExpensesException(exceptionMessage);
        } else if (index <= 0){
            String exceptionMessage = "List index cannot be 0 or negative";
            throw new ExpensesException(exceptionMessage);
        }
        return index;
    }

    public static Expense addUnequalExpense(ArrayList<String> payeeList, ArrayList<Pair<String, Money>> payees,
                                            Money totalAmountAndCurrency,
                                            String payerName, String argument) throws ExpensesException{
        float totalAmount = totalAmountAndCurrency.getAmount();
        float amountDueByPayees = 0;
        int payeeInfoMinLength = 2;
        CurrencyConversions currency = totalAmountAndCurrency.getCurrency();
        for (String payee : payeeList) {
            String[] payeeInfo = payee.split(" ");

            if (payeeInfo.length < payeeInfoMinLength) {
                String exceptionMessage = "Amount due for payee with name "
                        + payeeInfo[0] + " is empty. Enter it and try again";
                throw new ExpensesException(exceptionMessage);
            }
            String payeeName = mergeBack(payeeInfo);
            checkPayeeInGroup(payeeName);
            try {
                float amountDue = Float.parseFloat(payeeInfo[payeeInfo.length - 1]);
                amountDueByPayees += amountDue;
                Money amountDueAndCurrency = new Money(amountDue, currency);
                payees.add(new Pair<>(payeeName, amountDueAndCurrency));
            } catch (NumberFormatException e) {
                String exceptionMessage = "Re-enter amount due for payee with name "
                        + payeeName + " as a proper number.";
                throw new ExpensesException(exceptionMessage);
            }
        }
        if (amountDueByPayees > totalAmount) {
            String exceptionMessage = "The amount split between users is greater than total amount. Try again.";
            throw new ExpensesException(exceptionMessage);
        }
        float amountDueForPayer = totalAmount - amountDueByPayees;
        Money amountDueAndCurrency = new Money(amountDueForPayer, currency);
        payees.add(new Pair<>(payerName, amountDueAndCurrency));
        return new Expense(true, payerName, argument, totalAmountAndCurrency, payees);
    }


    public static Expense addEqualExpense(ArrayList<String> payeeList, ArrayList<Pair<String, Money>> payees,
                                           Money totalAmountAndCurrency,
                                          String payerName,String argument)throws ExpensesException {
        float totalAmount = totalAmountAndCurrency.getAmount();
        CurrencyConversions currency = totalAmountAndCurrency.getCurrency();
        float amountDue = totalAmount / (payeeList.size() + 1);
        Money amountDueAndCurrency = new Money(amountDue, currency);
        for (String payee : payeeList) {
            checkPayeeInGroup(payee);
            payees.add(new Pair<>(payee, amountDueAndCurrency));
        }
        checkPayeeInGroup(payerName);
        payees.add(new Pair<>(payerName, amountDueAndCurrency));
        return new Expense(payerName, argument, totalAmountAndCurrency, payees);
    }

    private static void checkDescription(String argument) throws ExpensesException {
        if(argument.isEmpty()){
            System.out.println("Warning! Empty description");
        } else if(argument.contains("◇")){
            throw new ExpensesException("Special characters not allowed in description! " +
                    "(Good try trynna catch a bug!)");
        }
    }

    private static void checkPayeeInGroup(String payee)
            throws ExpensesException {
        if(!Group.isMember(payee)){
            throw new ExpensesException(payee + " is not a member of the group!");
        }
    }

    private static String mergeBack(String[] splitArray){
        String mergedString = "";
        for(int i = 0; i < splitArray.length-2; i++){
            mergedString += splitArray[i].trim() + " ";
        }
        mergedString += splitArray[splitArray.length-2];
        return mergedString;
    }
}
