package seedu.duke;
//@@author MonkeScripts
//all rates are relative to 1 SGD
public enum CurrencyConversions {
    USD("USD", 0.74F),
    RMB("RMB", 5.35F),
    EUR("EUR", 0.687F),
    JPY("JPY", 112.12F),
    AUD("AUD", 1.12F),
    MYR("MYR", 3.50F),
    SGD("SGD", 1.00F),

    ;

    private final String name;
    private final float rate;

    CurrencyConversions(String name, float rate) {
        this.name = name;
        this.rate = rate;
    }

    String getName() {
        return this.name;
    }

    float getRate() {
        return this.rate;
    }

    float getInverseRate() {
        return (1.00f / this.rate);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
