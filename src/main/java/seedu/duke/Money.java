package seedu.duke;
//@@author MonkeScripts
public class Money {
    private final float amount;
    private final CurrencyConversions currency;

    public Money(float amount, CurrencyConversions currency) {
        this.amount = amount;
        this.currency = currency;
    }

    Money convertToSGD() {
        float amountInSGD = this.amount * this.currency.getInverseRate();
        return new Money(amountInSGD, CurrencyConversions.SGD);
    }

    Money convertToOther(CurrencyConversions resultCurrency) {
        float amountInSGD = this.amount * this.currency.getInverseRate();
        float foreignAmount = amountInSGD * resultCurrency.getRate();
        return new Money(foreignAmount, resultCurrency);
    }

    Money addition(Money other, CurrencyConversions resultCurrency) {
        float amountInSGD = this.convertToSGD().getAmount();
        float otherAmountInSGD = other.convertToSGD().getAmount();
        float foreignAmount = (amountInSGD + otherAmountInSGD)
                * resultCurrency.getRate();
        return new Money(foreignAmount, resultCurrency);
    }

    Money subtraction(Money other, CurrencyConversions resultCurrency) {
        float amountInSGD = this.convertToSGD().getAmount();
        float otherAmountInSGD = other.convertToSGD().getAmount();
        float foreignAmount = (amountInSGD - otherAmountInSGD)
                * resultCurrency.getRate();
        return new Money(foreignAmount, resultCurrency);
    }

    Money multiplication(float constant, CurrencyConversions resultCurrency) {
        float amountInSGD = this.convertToSGD().getAmount();
        float foreignAmount = (amountInSGD * constant)
                * resultCurrency.getRate();
        return new Money(foreignAmount, resultCurrency);
    }

    Money division(float constant, CurrencyConversions resultCurrency) {
        float amountInSGD = this.convertToSGD().getAmount();
        float foreignAmount = (amountInSGD / constant)
                * resultCurrency.getRate();
        return new Money(foreignAmount, resultCurrency);
    }

    public float getAmount() {
        return this.amount;
    }
    public CurrencyConversions getCurrency() {
        return this.currency;
    }
    @Override
    public String toString() {
        return String.format("%.2f%s", this.amount, this.currency);
    }
}
