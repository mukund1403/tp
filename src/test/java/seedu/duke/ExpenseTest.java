package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ExpenseTest{
    @Test
    public void newExpenseTest() throws ExpensesException {
        Expense testExpense = new Expense(true,"mukund","disneyland",
                10, new String[]{"cohii 2", "shao 3.2", "avril 1", "hafiz 2"});
        assertEquals("description disneyland and amount 10.0 paid by mukund " +
                "and split between:\ncohii who owes 2.00\nshao who owes 3.20\navril who owes 1.00" +
                "\nhafiz who owes 2.00\nmukund who owes 1.80\n",testExpense.toString());
    }
}
