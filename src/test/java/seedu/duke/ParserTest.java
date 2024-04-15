package seedu.duke;

import org.junit.jupiter.api.Test;

public class ParserTest {

    public void testParser(String userInput, String command, String argument,
                           String[] amount, String[] paid, String[] user, String[] currency){
        Parser parserFromInput = new Parser(userInput);
        System.out.println(parserFromInput);

        Parser parserFromParams = new Parser(userInput, command, argument, amount, paid, user, currency);
        System.out.println(parserFromParams);

        assert parserFromInput.toString().equals(parserFromParams.toString());
    }

    @Test
    public void test1(){
        testParser("command argument /amount amount /paid paid /currency SGD /user user1 /user user2",
                "command", "argument",
                new String[]{"amount"},
                new String[]{"paid"},
                new String[]{"user1", "user2"},
                new String[]{"SGD"});
    }
}
