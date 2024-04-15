//@@author avrilgk

package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ExpensesException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SettleTest {

    private Group group;

    @BeforeEach
    public void setup() throws ExpensesException {
        group = new Group("Test Group");
        User payer = new User("Alice");
        User payee = new User("Bob");
        group.addMember(payer.getName());
        group.addMember(payee.getName());
        ArrayList<Pair<String, Money>> payees = new ArrayList<>();
        Money payeeAmount = new Money(50.0f,CurrencyConversions.SGD);
        payees.add(new Pair<>(payee.getName(), payeeAmount));
        Money totalAmount = new Money(100.0f,CurrencyConversions.SGD);
        Expense expense = new Expense(payer.getName(), "Test Expense", totalAmount, payees);
        group.addExpense(expense);
    }

    @Test
    public void testSettleCreation() {
        User payer = new User("Alice");
        User payee = new User("Bob");
        Settle settle = new Settle(payer, payee, 50.0F);
        assertEquals("Alice", settle.getPayer());
        assertEquals("Alice paid Bob 50.0", settle.toString());
    }

    @Test
    public void testSettleCreationWithNegativeAmount() {
        User payer = new User("Alice");
        User payee = new User("Bob");
        Settle settle = new Settle(payer, payee, -50.0F);
        assertEquals("Alice", settle.getPayer());
        assertEquals("Alice paid Bob -50.0", settle.toString());
    }

    @Test
    public void testSettleCreationWithZeroAmount() {
        User payer = new User("Alice");
        User payee = new User("Bob");
        Settle settle = new Settle(payer, payee, 0.0F);
        assertEquals("Alice", settle.getPayer());
        assertEquals("Alice paid Bob 0.0", settle.toString());
    }

    @Test
    public void testSettleCreationWithZeroAmountAndNegativeAmount() {
        User payer = new User("Alice");
        User payee = new User("Bob");
        Settle settle = new Settle(payer, payee, 0.0F);
        assertEquals("Alice", settle.getPayer());
        assertEquals("Alice paid Bob 0.0", settle.toString());
    }
}
