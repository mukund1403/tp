//@@author avrilgk

package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ExpensesException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SettleTest {

    private Group group;

    @BeforeEach
    public void setup() throws ExpensesException {
        group = new Group("Test Group");
        User payer = new User("Alice");
        User payee = new User("Bob");
        group.addMember(payer.getName());
        group.addMember(payee.getName());
        ArrayList<Pair<String, Float>> payees = new ArrayList<>();
        payees.add(new Pair<>(payee.getName(), 50.0f));
        Expense expense = new Expense(false, payer.getName(), "Test Expense", 100.0f, payees);
        group.addExpense(expense);
    }

    @Test
    public void testSettleCreation() {
        User payer = new User("Alice");
        User payee = new User("Bob");
        Settle settle = new Settle(payer, payee, 50.0);
        assertEquals("Alice", settle.getPayer());
        assertEquals("Alice paid Bob 50.0", settle.toString());
    }

    @Test
    public void testSettleCreationWithNegativeAmount() {
        User payer = new User("Alice");
        User payee = new User("Bob");
        Settle settle = new Settle(payer, payee, -50.0);
        assertEquals("Alice", settle.getPayer());
        assertEquals("Alice paid Bob -50.0", settle.toString());
    }

    @Test
    public void testSettleCreationWithZeroAmount() {
        User payer = new User("Alice");
        User payee = new User("Bob");
        Settle settle = new Settle(payer, payee, 0.0);
        assertEquals("Alice", settle.getPayer());
        assertEquals("Alice paid Bob 0.0", settle.toString());
    }
}
