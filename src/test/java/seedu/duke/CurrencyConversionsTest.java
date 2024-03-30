package seedu.duke;

import org.junit.jupiter.api.Test;

public class CurrencyConversionsTest {
    @Test
    void testCurrencyConversions() {
        assert (CurrencyConversions.AUD.getName().equals("AUD"));
        assert (CurrencyConversions.AUD.getRate() == 1.12);
        assert (CurrencyConversions.AUD.getInverseRate() == 1.00 / 1.12);
        assert (CurrencyConversions.USD.getName().equals("USD"));
        assert (CurrencyConversions.USD.getRate() == 0.74);
        assert (CurrencyConversions.RMB.getName().equals("RMB"));
        assert (CurrencyConversions.RMB.getRate() == 5.35);
        assert (CurrencyConversions.EUR.getName().equals("EUR"));
        assert (CurrencyConversions.EUR.getRate() == 0.687);
        assert (CurrencyConversions.JPY.getName().equals("JPY"));
        assert (CurrencyConversions.JPY.getRate() == 112.12);
        assert (CurrencyConversions.MYR.getName().equals("MYR"));
        assert (CurrencyConversions.MYR.getRate() == 3.50);
        assert (CurrencyConversions.SGD.getName().equals("SGD"));
        assert (CurrencyConversions.SGD.getRate() == 1.00);
    }
}
