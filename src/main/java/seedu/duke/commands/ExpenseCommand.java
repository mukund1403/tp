package seedu.duke.commands;

import seedu.duke.Expense;
import seedu.duke.exceptions.ExpensesException;
import seedu.duke.Group;
import seedu.duke.Pair;

import java.util.ArrayList;
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

        float totalAmount = checkTotal(params);

        // Obtain necessary information from 'params' and create new Expense
        ArrayList<String> payeeList = params.get("user");

        for(String payee : payeeList){
            if(!Group.isMember(payee)){
                throw new ExpensesException(payee + " is not a member of the group!");
            }
        }

        String payerName = params.get("paid").get(0);
        if(!Group.isMember(payerName)){
            throw new ExpensesException(payerName + " is not a member of the group!");
        }
        if(argument.isEmpty()){
            System.out.println("Warning! Empty description");
        }

        Expense newTransaction;
        ArrayList<Pair<String,Float>> payees = new ArrayList<>();
        if(userInput.contains("/unequal")){
            newTransaction = addUnequalExpense(payeeList, payees, totalAmount, payerName, argument);
        } else {
            newTransaction = addEqualExpense(payeeList, payees, totalAmount, payerName, argument);
        }
        currentGroup.get().addExpense(newTransaction);
    }

    public static void deleteExpense(String argument){

    }


    //@@author mukund1403
    private static Float checkTotal(HashMap <String, ArrayList<String>> params) throws ExpensesException {
        float totalAmount;
        try {
            totalAmount = Float.parseFloat(params.get("amount").get(0));
        } catch (NumberFormatException e) {
            String exceptionMessage = "Re-enter expense with amount as a proper number.";
            throw new ExpensesException(exceptionMessage);
        }
        return totalAmount;
    }
    private static Expense addUnequalExpense(ArrayList<String> payeeList,ArrayList<Pair<String,Float>> payees,
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

    private static Expense addEqualExpense(ArrayList<String> payeeList, ArrayList<Pair<String,Float>> payees,
                                           float totalAmount, String payerName, String argument){
        Float amountDue = totalAmount / (payeeList.size() + 1);
        for (String payee : payeeList) {
            payees.add(new Pair<>(payee, amountDue));
        }
        payees.add(new Pair<>(payerName, amountDue));
        return new Expense(payerName, argument, totalAmount, payees);
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
