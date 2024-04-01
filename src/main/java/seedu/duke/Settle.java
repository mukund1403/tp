//@@author avrilgk
package seedu.duke;


/**
 * The Settle class represents a transaction between two users.
 * It extends the Expense class and has a payer, payee and amount.
 * <p>
 * Each Settle object represents a single transaction where one user (the payer)
 * pays another user (the payee) a certain amount.
 */

public class Settle extends Expense {
    private final User payer;
    private final User payee;
    private final double amount;

    /**
     * Constructs a new Settle object.
     *
     * @param payer  The user who is making the payment
     * @param payee  The user who is receiving the payment
     * @param amount The amount of the payment
     */
    public Settle(User payer, User payee, double amount) {
        super(payer, amount);
        assert payer != null : "Payer cannot be null";
        assert payee != null : "Payee cannot be null";
        assert amount >= 0 : "Amount cannot be negative";
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }

    /**
     * Returns the user who is making the payment.
     *
     * @return The user who is making the payment
     */

    public String getPayer() {
        return payer.getName();
    }

    /**
     * Returns a string representation of the Settle object.
     * The returned string is in the format "payerName paid payeeName amount".
     *
     * @return A string representation of the Settle object
     */
    @Override
    public String toString() {
        return payer.getName() + " paid " + payee.getName() + " " + amount;
    }
}
