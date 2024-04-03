package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ExpensesException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@@author Cohii2
public class BalanceTest {
    @Test
    public void testConstructor() throws ExpensesException {
        List<User> users = new ArrayList<>();
        users.add(new User("member1"));
        users.add(new User("member2"));
        users.add(new User("member3"));

        // public Expense(String payerName, String description, float totalAmount, ArrayList<Pair<String, Float>> payees){
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(true, "member1", "expense1", 20f,
                new ArrayList<>(Arrays.asList(
                        new Pair<>("member2", 5.0f),
                        new Pair<>("member3", 10.0f)
                ))));
        expenses.add(new Expense("member2", "expense2", 30f,
                new ArrayList<>(Arrays.asList(
                        new Pair<>("member1", 10.0f),
                        new Pair<>("member3", 10.0f)
                ))));
        expenses.add(new Expense("member3", "expense3", 100f,
                new ArrayList<>(Arrays.asList(
                        new Pair<>("member1", 100.0f)
                ))));

        Balance member1Balance = new Balance("member1", expenses, users);
        member1Balance.printBalance();
        Balance member2Balance = new Balance("member2", expenses, users);
        member2Balance.printBalance();
        Balance member3Balance = new Balance("member3", expenses, users);
        member3Balance.printBalance();

        assertEquals(-5.0f, member1Balance.getBalanceList().get("member2"));
        assertEquals(-90.0f, member1Balance.getBalanceList().get("member3"));

        assertEquals(5.0f, member2Balance.getBalanceList().get("member1"));
        assertEquals(10.0f, member2Balance.getBalanceList().get("member3"));

        assertEquals(90.0f, member3Balance.getBalanceList().get("member1"));
        assertEquals(-10.0f, member3Balance.getBalanceList().get("member2"));
    }
}
