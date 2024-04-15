package seedu.duke;

import org.junit.jupiter.api.Test;

public class CurrencyConversionsTest {
    @Test
    void testCurrencyConversions() {
        assert (CurrencyConversions.AUD.getName().equals("AUD"));
        assert (CurrencyConversions.AUD.getRate() == 1.12F);
        assert (CurrencyConversions.AUD.getInverseRate() == 1.00F / 1.12F);
        assert (CurrencyConversions.USD.getName().equals("USD"));
        assert (CurrencyConversions.USD.getRate() == 0.74F);
        assert (CurrencyConversions.RMB.getName().equals("RMB"));
        assert (CurrencyConversions.RMB.getRate() == 5.35F);
        assert (CurrencyConversions.EUR.getName().equals("EUR"));
        assert (CurrencyConversions.EUR.getRate() == 0.687F);
        assert (CurrencyConversions.JPY.getName().equals("JPY"));
        assert (CurrencyConversions.JPY.getRate() == 112.12F);
        assert (CurrencyConversions.MYR.getName().equals("MYR"));
        assert (CurrencyConversions.MYR.getRate() == 3.50F);
        assert (CurrencyConversions.SGD.getName().equals("SGD"));
        assert (CurrencyConversions.SGD.getRate() == 1.00F);
    }
}
