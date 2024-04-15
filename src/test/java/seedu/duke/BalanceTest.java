package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ExpensesException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@@author Cohii2
public class BalanceTest {
    @Test
    public void testConstructor() throws ExpensesException {
        List<User> users = new ArrayList<>();
        users.add(new User("member1"));
        users.add(new User("member2"));
        users.add(new User("member3"));

        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("member1", "expense1",
                new Money(20f, CurrencyConversions.SGD),
                new ArrayList<>(Arrays.asList(
                        new Pair<>("member2", new Money(5.0f, CurrencyConversions.SGD)),
                        new Pair<>("member3", new Money(10.0f, CurrencyConversions.SGD))
                ))));
        expenses.add(new Expense("member2", "expense2",
                new Money(30f, CurrencyConversions.SGD),
                new ArrayList<>(Arrays.asList(
                        new Pair<>("member1", new Money(10.0f, CurrencyConversions.SGD)),
                        new Pair<>("member3", new Money(10.0f, CurrencyConversions.SGD))
                ))));
        expenses.add(new Expense("member3", "expense3",
                new Money(100f, CurrencyConversions.AUD),
                new ArrayList<>(Arrays.asList(
                        new Pair<>("member1", new Money(50.0f, CurrencyConversions.AUD))
                ))));

        Balance member1Balance = new Balance("member1", expenses, users);
        member1Balance.printBalance();
        Balance member2Balance = new Balance("member2", expenses, users);
        member2Balance.printBalance();
        Balance member3Balance = new Balance("member3", expenses, users);
        member3Balance.printBalance();

        testList(moneyListMaker(new Money(-5.00f, CurrencyConversions.SGD)),
                member1Balance.getBalanceList().get("member2"));
        testList(moneyListMaker(new Money(10.0f, CurrencyConversions.SGD),
                new Money(-50.0f, CurrencyConversions.AUD)),
                member1Balance.getBalanceList().get("member3"));

        testList(moneyListMaker(new Money(5.0f, CurrencyConversions.SGD)),
                member2Balance.getBalanceList().get("member1"));
        testList(moneyListMaker(new Money(10.0f, CurrencyConversions.SGD)),
                member2Balance.getBalanceList().get("member3"));

        testList(moneyListMaker(new Money(-10.0f, CurrencyConversions.SGD)),
                member3Balance.getBalanceList().get("member2"));
        testList(moneyListMaker(new Money(-10.0f, CurrencyConversions.SGD),
                new Money(50.0f, CurrencyConversions.AUD)),
                member3Balance.getBalanceList().get("member1"));
    }

    public void testList(List<Money> l1, List<Money> l2){
        assert(l1.size() == l2.size());
        for(int i = 0; i < l1.size(); i++){
            assertEquals(l1.get(i).getCurrency(), l2.get(i).getCurrency());
            assertEquals(l1.get(i).getAmount(), l2.get(i).getAmount());
        }
    }

    public List<Money> moneyListMaker(Money money1, Money money2){
        List<Money> moneyList = new ArrayList<>();
        moneyList.add(money1);
        moneyList.add(money2);
        return moneyList;
    }

    public List<Money> moneyListMaker(Money money1){
        List<Money> moneyList = new ArrayList<>();
        moneyList.add(money1);
        return moneyList;
    }
}
