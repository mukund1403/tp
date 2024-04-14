//@@author mukund1403
package seedu.duke;


import seedu.duke.exceptions.ExpensesException;
import seedu.duke.storage.GroupStorage;

import java.util.ArrayList;


/**
 * A class to add a new expense
 */
public class Expense {
    private String payerName;
    private float totalAmount;
    private ArrayList<Pair<String, Float>> payees;

    private String description;

    /**
     * Constructor to create new Expense
     *
     * @param isUnequal   : Boolean showing whether expense is split unequally or not
     * @param payerName   : The name of the user who paid for the Expense
     * @param description : Description of the expense
     * @param totalAmount : The total amount before being divided
     * @param payees      : ArrayList of pairs containing names of people who are involved in the transaction and
     *                    the amount they owe (Index 0 is the payer and will also be added to the payees but as last index)
     */
    public Expense(boolean isUnequal, String payerName, String description,
                   float totalAmount, ArrayList<Pair<String, Float>> payees)
            throws ExpensesException {
        this.payees = payees;
        this.payerName = payerName;
        this.totalAmount = totalAmount;
        this.description = description;
        printSuccessMessage();
    }

    public Expense(String payerName, String description, float totalAmount, ArrayList<Pair<String, Float>> payees) {
        this.payees = payees;
        this.payerName = payerName;
        this.totalAmount = totalAmount;
        this.description = description;
        printSuccessMessage();
    }

    //@@author Cohii2
    public String getPayerName() {
        return payerName;
    }

    /**
     * @return : float showing the total amount before division
     */
    public float getTotalAmount() {
        return totalAmount;
    }

    public ArrayList<Pair<String, Float>> getPayees() {
        return payees;
    }

    //@@author mukund1403
    public String getDescription() {
        return description;
    }


    public void clearPayeeValue(String payeeName) {
        // replace the value of the payee with 0
        for (int i = 0; i < payees.size(); i++) {
            if (payees.get(i).getKey().equals(payeeName)) {
                payees.set(i, new Pair<>(payeeName, 0f));
            }
        }
    }

    @Override
    public String toString() {
        String expensesDetails = "";
        expensesDetails += "description " + description + " and amount " + totalAmount +
                " paid by " + payerName + " and split between:\n";
        for (Pair<String, Float> payee : payees) {
            expensesDetails += payee.getKey() + " who owes " + String.format("%.2f", payee.getValue()) + "\n";
        }
        return expensesDetails;
    }

    void printSuccessMessage() {
        if (!GroupStorage.isLoading) {
            if (!(this instanceof Settle)) {
                System.out.println("Added new expense with description " + description + " and amount " + totalAmount
                        + " paid by " + payerName + " and split between:");
                for (Pair<String, Float> payee : payees) {
                    System.out.println(payee.getKey() + " who owes " + String.format("%.2f", payee.getValue()));
                }
                System.out.println();
            }
        }
    }

    public void clear() {
        totalAmount = 0;
        payees = new ArrayList<>();
    }

    public String getPayer() {
        return payerName;
    }
}


