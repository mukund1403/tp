//@@author avrilgk
package seedu.duke;


import java.util.ArrayList;

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
        super(payer.getName(), "", (float) amount, new ArrayList<>());
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }

    @Override
    public String getPayer() {
        return payer.getName();
    }

    @Override
    public String toString() {
        return payer.getName() + " paid " + payee.getName() + " " + amount;
    }
}
