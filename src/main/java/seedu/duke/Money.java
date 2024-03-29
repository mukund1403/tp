package seedu.duke;

public class Money {
    private final double amount;
    private final CurrencyConversions currency;

    public Money(double amount, CurrencyConversions currency) {
        this.amount = amount;
        this.currency = currency;
    }

    Money convertToSGD() {
        double amountInSGD = this.amount * this.currency.getInverseRate();
        return new Money(amountInSGD, CurrencyConversions.SGD);
    }

    Money convertToOther(CurrencyConversions resultCurrency) {
        double amountInSGD = this.amount * this.currency.getInverseRate();
        double foreignAmount = amountInSGD * resultCurrency.getRate();
        return new Money(foreignAmount, resultCurrency);
    }

    Money addition(Money other, CurrencyConversions resultCurrency) {
        double amountInSGD = this.convertToSGD().getAmount();
        double otherAmountInSGD = other.convertToSGD().getAmount();
        double foreignAmount = (amountInSGD + otherAmountInSGD)
                * resultCurrency.getRate();
        return new Money(foreignAmount, resultCurrency);
    }

    Money subtraction(Money other, CurrencyConversions resultCurrency) {
        double amountInSGD = this.convertToSGD().getAmount();
        double otherAmountInSGD = other.convertToSGD().getAmount();
        double foreignAmount = (amountInSGD - otherAmountInSGD)
                * resultCurrency.getRate();
        return new Money(foreignAmount, resultCurrency);
    }

    Money multiplication(Money other, CurrencyConversions resultCurrency) {
        double amountInSGD = this.convertToSGD().getAmount();
        double otherAmountInSGD = other.convertToSGD().getAmount();
        double foreignAmount = (amountInSGD * otherAmountInSGD)
                * resultCurrency.getRate();
        return new Money(foreignAmount, resultCurrency);
    }

    Money division(double constant, CurrencyConversions resultCurrency) {
        double amountInSGD = this.convertToSGD().getAmount();
        double foreignAmount = (amountInSGD / constant)
                * resultCurrency.getRate();
        return new Money(foreignAmount, resultCurrency);
    }

    double getAmount() {
        return this.amount;
    }
    CurrencyConversions getCurrency() {
        return this.currency;
    }
    @Override
    public String toString() {
        return String.format("%.2f%s", this.amount, this.currency);
    }
}
