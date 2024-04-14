package seedu.duke.commands;

import seedu.duke.Expense;
import seedu.duke.exceptions.ExpensesException;
import seedu.duke.Group;
import seedu.duke.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        float totalAmount = getTotal(params);
        ArrayList<String> payeeList = params.get("user");
        String payerName = params.get("paid").get(0);

        checkDescription(argument);

        //@@author mukund1403
        Expense newTransaction;
        ArrayList<Pair<String,Float>> payees = new ArrayList<>();
        if(userInput.contains("/unequal")){
            newTransaction = addUnequalExpense(payeeList, payees, totalAmount, payerName, argument);
        } else {
            newTransaction = addEqualExpense(payeeList, payees, totalAmount, payerName, argument);
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
        try {
            totalAmount = Float.parseFloat(params.get("amount").get(0));
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

    public static Expense addUnequalExpense(ArrayList<String> payeeList,ArrayList<Pair<String,Float>> payees,
                                          float totalAmount,String payerName,String argument) throws ExpensesException{
        float amountDueByPayees = 0;
        int payeeInfoMinLength = 2;
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
                payees.add(new Pair<>(payeeName, amountDue));
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
        payees.add(new Pair<>(payerName, totalAmount - amountDueByPayees));
        return new Expense(true, payerName, argument, totalAmount, payees);
    }

    public static Expense addEqualExpense(ArrayList<String> payeeList, ArrayList<Pair<String,Float>> payees,
                                           float totalAmount,String payerName,String argument)throws ExpensesException {
        Float amountDue = totalAmount / (payeeList.size() + 1);
        for (String payee : payeeList) {
            checkPayeeInGroup(payee);
            payees.add(new Pair<>(payee, amountDue));
        }
        checkPayeeInGroup(payerName);
        payees.add(new Pair<>(payerName, amountDue));
        return new Expense(payerName, argument, totalAmount, payees);
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
