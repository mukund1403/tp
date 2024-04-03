package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ExpensesException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ExpenseTest{
    @Test
    public void newExpenseTest() throws ExpensesException {
        Expense testExpense = new Expense(true,"mukund","disneyland",
                10,
                new ArrayList<>(Arrays.asList(
                        new Pair<>("cohii", 2.0f),
                        new Pair<>("shao", 3.20f),
                        new Pair<>("avril", 1.0f),
                        new Pair<>("hafiz", 2.0f),
                        new Pair<>("mukund", 1.8f)
                )));
        assertEquals("description disneyland and amount 10.0 paid by mukund " +
                "and split between:\ncohii who owes 2.00\nshao who owes 3.20\navril who owes 1.00" +
                "\nhafiz who owes 2.00\nmukund who owes 1.80\n",testExpense.toString());
    }
}
