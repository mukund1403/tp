package seedu.duke;
//@@author MonkeScripts
//all rates are relative to 1 SGD
public enum CurrencyConversions {
    USD("USD", 0.74),
    RMB("RMB", 5.35),
    EUR("EUR", 0.687),
    JPY("JPY", 112.12),
    AUD("AUD", 1.12),
    MYR("MYR", 3.50),
    SGD("SGD", 1.00),

    ;

    private final String name;
    private final double rate;

    CurrencyConversions(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    String getName() {
        return this.name;
    }

    double getRate() {
        return this.rate;
    }

    double getInverseRate() {
        return 1.00 / this.rate;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
