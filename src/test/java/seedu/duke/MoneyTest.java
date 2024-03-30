package seedu.duke;

import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    void testMoney() {
        Money a = new Money(10.00, CurrencyConversions.MYR);
        assert(a.getAmount() == 10.00);
        assert(a.getCurrency().equals(CurrencyConversions.MYR)) ;
    }

    @Test
    void testAddition() {
        Money sg = new Money(10.00, CurrencyConversions.SGD);
        Money malaysia = new Money(10.00, CurrencyConversions.MYR);
        //total in SGD
        Money total = sg.addition(malaysia, CurrencyConversions.SGD);
        System.out.println(total.getAmount());
        assert(total.getAmount() == 10.00 * CurrencyConversions.SGD.getRate()
                + 10.00 * CurrencyConversions.MYR.getInverseRate());
        assert(total.getCurrency().equals(CurrencyConversions.SGD));
        assert(total.getAmount() == sg.convertToSGD().getAmount()
                + malaysia.convertToSGD().getAmount());
        assert(total.getCurrency().equals(CurrencyConversions.SGD));
        //total in MYR
        total = sg.addition(malaysia, CurrencyConversions.MYR);
        assert(total.getAmount() == 10.00 * CurrencyConversions.MYR.getRate()
                + 10.00);
        assert(total.getAmount() ==
                sg.convertToOther(CurrencyConversions.MYR).getAmount() + malaysia.getAmount());
        assert(total.getCurrency().equals(CurrencyConversions.MYR));
    }

    @Test
    void testMultiplication() {
        Money jap = new Money(10000.00, CurrencyConversions.JPY);
        //multiplied by 3 fold and then converted to euro
        Money multiplied = jap.multiplication(3, CurrencyConversions.EUR);
        assert(multiplied.getAmount() == new Money(
                30000.00, CurrencyConversions.JPY).
                convertToOther(CurrencyConversions.EUR).getAmount());
        assert(multiplied.getCurrency().equals(CurrencyConversions.EUR));
    }

    @Test
    void testAdditionAndMultiplication() {
        Money sg = new Money(10.00, CurrencyConversions.SGD);
        Money malaysia = new Money(10.00, CurrencyConversions.MYR);
        //compute total = sg + 3 * malaysia, converted to euro;
        Money total = sg.addition(malaysia.multiplication(
                3, CurrencyConversions.MYR), CurrencyConversions.EUR);
        assert(total.getAmount() ==
                sg.addition(new Money(30.00, CurrencyConversions.MYR),
                        CurrencyConversions.EUR).getAmount());
    }

}
