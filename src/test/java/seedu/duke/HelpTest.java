package seedu.duke;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class HelpTest {
    private static final String prompt =
            "Welcome, here is a list of commands:\n" +
                    "help: Access help menu.\n" +
                    "create <name>: Create a group.\n" +
                    "exit <name>: Exit current group.\n" +
                    "member <name> : Add a member to the group.\n" +
                    "expense <description> /amount <amount> /currency <currency> /paid <paid_by> /user <user_1> /user <user_2> ...: " +
                    "Add an expense SPLIT EQUALLY.\n" +
                    "expense <description> /unequal /amount <amount> /currency <currency> /paid <paid_by> " +
                    "/user <user_1> <amount_owed> /user <user_2> <amount owed> ...: Add an expense SPLIT UNEQUALLY.\n" +
                    "list: List all expenses in the group.\n" +
                    "balance <user_name>: Show user's balance.\n" +
                    "settle <payer_name> /user <payee_name>: Settle the amount between two users.\n" +
                    "luck <payer_name>: luck is in the air tonight";

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }
    @Test
    public void testPrint() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        printHelp();
        String output = baos.toString();
        assertEquals(prompt, output);
    }

    static void printHelp() {
        System.out.print(prompt);
    }
}
