//@@author mukund1403
package seedu.duke;



import seedu.duke.storage.GroupStorage;
import java.util.ArrayList;


/**
 * A class to add a new expense
 */
public class Expense {
    private final String payerName;
    private Money totalAmount;
    private ArrayList<Pair<String, Money>> payees = new ArrayList<>();


    private final String description;

    /**
     * Constructor to create new Equal Expense
     * @param payerName : The name of the user who paid for the Expense
     * @param description : Description of the expense
     * @param totalAmount : The total amount before being divided
     * @param payees : ArrayList of pairs containing names of people who are involved in the transaction and
     *                the amount they owe (Index 0 is the payer and will also be added to the payees but as last index)
     */
    public Expense(String payerName, String description, Money totalAmount, ArrayList<Pair<String, Money>> payees){
        this.payees = payees;
        this.payerName = payerName;
        this.totalAmount = totalAmount;
        this.description = description;
    }

    //@@author Cohii2
    public String getPayerName() {
        return payerName;
    }

    /**
     * @return : float showing the total amount before division
     */
    public float getTotalAmount() {
        return totalAmount.getAmount();
    }

    public ArrayList<Pair<String, Money>> getPayees() {
        return payees;
    }

    //@@author mukund1403
    public String getDescription() {
        return description;
    }

    public CurrencyConversions getCurrency() {
        return totalAmount.getCurrency();
    }

    public void clearPayeeValue(String payeeName) {
        // replace the value of the payee with 0
        for (int i = 0; i < payees.size(); i++) {
            if (payees.get(i).getKey().equals(payeeName)) {
                Money amount = new Money(0, payees.get(i).getValue().getCurrency());
                payees.set(i, new Pair<>(payeeName, amount));
            }
        }
    }

    /**
     * Converts the Expense to string form containing all the expense details
     * @return : A string containing expense details in a tabular format
     */
    @Override
    public String toString() {
        StringBuilder expensesDetails = new StringBuilder();
        expensesDetails.append("description: ").append(description).append("\n\tamount: ")
                .append(totalAmount.getCurrency())
                .append(String.format(" %.2f", totalAmount.getAmount())).append("\n\tpaid by: ")
                .append(payerName).append("\n\tThe split is:\n");
        for (Pair<String, Money> payee : payees) {
            expensesDetails.append("\t\t").append(payee.getKey()).append(" : ")
                    .append(payee.getValue().getCurrency())
                    .append(String.format(" %.2f", payee.getValue().getAmount())).append("\n");
        }
        return expensesDetails.toString();
    }

    public void printSuccessMessage() {
        if (!(GroupStorage.isLoading) && !(this instanceof Settle)) {
            System.out.println("Added new expense with description " + description + " and amount "
                    + String.format(totalAmount.getCurrency() + " %.2f",totalAmount.getAmount()) +
                    " paid by " + payerName + ". The split is:");

            for (Pair<String, Money> payee : payees) {
                System.out.println(payee.getKey() + " : " + String.format(payee.getValue().getCurrency() +
                        " %.2f", payee.getValue().getAmount()));
            }
        }
    }

    public void clear() {
        float amount = totalAmount.getAmount();
        CurrencyConversions currency = totalAmount.getCurrency();
        totalAmount = new Money(amount, currency);
        payees = new ArrayList<>();
    }

    public String getPayer() {
        return payerName;
    }
}


