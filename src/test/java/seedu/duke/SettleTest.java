//@@author avrilgk

package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SettleTest {

    private User payer;
    private User payee;
    private Settle settle;

    @BeforeEach
    void setUp() {
        // Initialize your objects before each test
        payer = new User("Alice");
        payee = new User("Bob");
        settle = new Settle(payer, payee, 100.0); // Assuming the amount to settle is 100.0
    }

    @Test
    void testGetPayer() {
        assertEquals(payer.getName(), settle.getPayer(), "Payer's name should match the one provided at creation");
    }

    @Test
    void testToString() {
        String expected = "Alice paid Bob 100.0";
        assertEquals(expected, settle.toString(), "toString should return a string in the format 'payerName paid payeeName amount'");
    }

    @Test
    void testNegativeAmount() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Settle(payer, payee, -50.0),
                "Constructor should throw IllegalArgumentException for negative amounts");
        assertTrue(exception.getMessage().contains("Amount cannot be negative"), "Exception message should indicate the negative amount problem");
    }

    @Test
    void testNullPayer() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Settle(null, payee, 50.0),
                "Constructor should throw IllegalArgumentException for null payer");
        assertTrue(exception.getMessage().contains("Payer cannot be null"), "Exception message should indicate the null payer problem");
    }

    @Test
    void testNullPayee() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Settle(payer, null, 50.0),
                "Constructor should throw IllegalArgumentException for null payee");
        assertTrue(exception.getMessage().contains("Payee cannot be null"), "Exception message should indicate the null payee problem");
    }

    @Test
    void testNullPayerAndPayee() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Settle(null, null, 50.0),
                "Constructor should throw IllegalArgumentException for null payer and payee");
        assertTrue(exception.getMessage().contains("Payer cannot be null"), "Exception message should indicate the null payer problem");
    }

    @Test
    void testNullPayerPayeeAndAmount() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Settle(null, null, -50.0),
                "Constructor should throw IllegalArgumentException for null payer, payee and negative amount");
        assertTrue(exception.getMessage().contains("Payer cannot be null"), "Exception message should indicate the null payer problem");
    }
}
