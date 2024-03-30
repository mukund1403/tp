//@@author mukund1403
package seedu.duke;


import javafx.util.Pair;

import java.util.ArrayList;

/**
 * A class to add a new expense
 */
public class Expense {
    private String payerName;
    private float totalAmount;
    private ArrayList<Pair<String,Float>> payees = new ArrayList<>();

    private String description;

    /**
     * Constructor to create new Expense
     * @param isUnequal : Boolean showing whether expense is split unequally or not
     * @param payerName : The name of the user who paid for the Expense
     * @param description : Description of the expense
     * @param totalAmount : The total amount before being divided
     * @param payeeList : String array of people who are involved in the transaction
     *                  (Index 0 is the payer and will also be added to the payees but as last index)
     */
    Expense(boolean isUnequal, String payerName, String description, float totalAmount, String[] payeeList)
            throws ExpensesException {
        if(isUnequal){
            float amountDueByPayees = 0;
            for(String payee : payeeList){
                String[] payeeInfo = payee.split(" ");
                String payeeName = payeeInfo[0].trim();
                if(payeeName.equals(payerName)){
                    continue;
                }
                try {
                    float amountDue = Float.parseFloat(payeeInfo[1].trim());
                    amountDueByPayees += amountDue;
                    payees.add(new Pair<>(payeeName,amountDue));
                } catch (NumberFormatException e) {
                    String exceptionMessage = "Re-enter amount due for payee with name "
                            + payeeName + " as a proper number.";
                    throw new ExpensesException(exceptionMessage);
                } catch (ArrayIndexOutOfBoundsException e) {
                    String exceptionMessage = "Amount due for payee with name "
                            + payeeName + " is empty. Enter it and try again";
                    throw new ExpensesException(exceptionMessage);
                }
            }
            if(amountDueByPayees > totalAmount){
                String exceptionMessage = "The amount split between users is greater than total amount. Try again.";
                throw new ExpensesException(exceptionMessage);
            }
            payees.add(new Pair<>(payerName,totalAmount-amountDueByPayees));
        } else {
            Float amountDue = totalAmount/payeeList.length;
            for(String payee : payeeList){
                payees.add(new Pair<>(payee,amountDue));
            }
        }

        this.payerName = payerName;
        this.totalAmount = totalAmount;
        this.description = description;

        System.out.printf("Added new expense with description %s and amount %.2f paid by %s and split between:\n",
                this.description,this.totalAmount,this.payerName);
        for(Pair<String,Float> payee : payees) {
            System.out.printf("%s who owes %.2f\n", payee.getKey(),payee.getValue());
        }
        System.out.println();
    }

    public String getPayerName() {
        return payerName;
    }

    /**
     * @return : float showing the total amount before division
     */
    public float getTotalAmount() {
        return totalAmount;
    }

    public ArrayList<Pair<String,Float>> getPayees() {
        return payees;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        String expensesDetails = "";
        expensesDetails += "description " + description + " and amount " + totalAmount +
                " paid by " + payerName + " and split between:\n";
        for(Pair<String,Float> payee : payees) {
            expensesDetails += payee.getKey() + " who owes " + String.format("%.2f",payee.getValue()) + "\n";
        }
        return expensesDetails;
    }
}


