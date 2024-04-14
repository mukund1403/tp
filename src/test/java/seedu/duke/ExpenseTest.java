package seedu.duke;
//@@author mukund1403
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.ExpenseCommand;
import seedu.duke.exceptions.ExpensesException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ExpenseTest{
    @Test
    public void newExpenseTest() throws ExpensesException {
        ArrayList<Pair<String, Float>> payees = new ArrayList<>(Arrays.asList(
                new Pair<>("cohii", 2.0f),
                new Pair<>("shao", 3.20f),
                new Pair<>("avril", 1.0f),
                new Pair<>("hafiz", 2.0f),
                new Pair<>("mukund", 1.8f)
        ));
        Expense testExpense1 = new Expense(true,"mukund","disneyland",
                10, payees);

        assertEquals(testExpense1.getPayees(),payees);
    }

    @Test
    public void amountNotFloatTest() {
        ArrayList<String> amountArrayList = new ArrayList<>();
        amountArrayList.add("b");
        HashMap<String,ArrayList<String>> params = new HashMap<>();
        params.put("amount",amountArrayList);
        Exception e = Assertions.assertThrows(ExpensesException.class,
                () -> ExpenseCommand.getTotal(params),
                "Function should throw Expenses exception since exception occured in expenses class.");
        assertEquals("Re-enter expense with amount as a proper number. " +
                "(Good bug to start with tbh!)", e.getMessage(),
                "Exception message should indicate that amount entered was not a number");

    }

    @Test
    public void listIndexGreaterTest(){
        String listIndex = "20";
        int listSize = 2;
        Exception e = Assertions.assertThrows(ExpensesException.class,
                () -> ExpenseCommand.getListIndex(listIndex, listSize),
                "Function should throw ExpensesException");
        assertEquals("List index is greater than list size",e.getMessage(),
                "Exception message should indicate that listIndex entered was greater than list size.");
    }

    @Test
    public void listIndexNegativeTest(){
        String listIndex = "-1";
        int listSize = 2;
        Exception e = Assertions.assertThrows(ExpensesException.class,
                () -> ExpenseCommand.getListIndex(listIndex, listSize),
                "Function should throw ExpensesException");
        assertEquals("List index cannot be 0 or negative",e.getMessage(),
                "Exception message should indicate that listIndex entered was less than or equal to 0.");
    }

    @Test
    public void listIndexNotIntegerTest(){
        String listIndex1 = "a";
        int listSize = 2;
        Exception e1 = Assertions.assertThrows(ExpensesException.class,
                () -> ExpenseCommand.getListIndex(listIndex1, listSize),
                "Function should throw ExpensesException");
        assertEquals("Enter a list index that is an Integer",e1.getMessage(),
                "Exception message should indicate that listIndex entered was not an integer.");

        String listIndex2 = "5.0";
        Exception e2 = Assertions.assertThrows(ExpensesException.class,
                () -> ExpenseCommand.getListIndex(listIndex1, listSize),
                "Function should throw ExpensesException");
        assertEquals("Enter a list index that is an Integer",e2.getMessage(),
                "Exception message should indicate that listIndex entered was not an integer.");
    }

    @Test
    public void unequalExpenseTest() throws ExpensesException {
        ArrayList<String> payeeList = new ArrayList<>(Arrays.asList(
                ("cohii"),
                ("shao 1"),
                ("avril 5.5"),
                ("hafiz"),
                ("mukund 2")
        ));
        ArrayList<Pair<String, Float>> payees = new ArrayList<>();
        float totalAmount = 10;
        String payerName = "mukund";
        String argument = "disneyland";
        Exception e = Assertions.assertThrows(ExpensesException.class,
                () -> ExpenseCommand.addUnequalExpense(payeeList, payees, totalAmount, payerName, argument),
                "Function should throw ExpensesException");
        assertEquals("Amount due for payee with name cohii" +
                " is empty. Enter it and try again", e.getMessage(),
                "Exception message should indicate that amount for user has not been entered");
    }
}
