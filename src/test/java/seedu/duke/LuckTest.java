package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LuckTest {
    private static final String testOutput =

                    "                   .=*+::.                          \n" +
                    "                   =*=-:::.                         \n" +
                    "             .:=+**#***####*+=-:.                   \n" +
                    "          :+#%%%%###%########%%%%*=:                \n" +
                    "       .=#%%%####################%%%#=.             \n" +
                    "      +#%%%####%%%###%%%###%%%%#*###%%#=            \n" +
                    "    :##%%########%%###%%###%%#########%%#:          \n" +
                    "   .##%#*#%%%%#######*##*#######%%%##*#%%#:         \n" +
                    "   *%%%##%%###%%%############%%%%%%%%##%%%#.        \n" +
                    "  .%#*****+*****++*++*****+**++***********%=        \n" +
                    "  :%*-::.-=-:::-==:==-:::-=:-===---==-.::+%+        \n" +
                    "  :%*=:.+@@@%%@@@*=@@@#%%@@%-@@@@%@@@@-.-+#+ .=-..  \n" +
                    "  :%*=.=%+*=+***@-%%+*=+##*@=*%++==**+%::+#=.##---  \n" +
                    "  :%*=.#%-++=--*%-@#-++=--*@+=%-+*=--*@=:+#+ -::-:  \n" +
                    "  :%*=.#@%@%=-*@%-@@%@%=-#@@+=@%@%=-#@@=:+#+  -+:   \n" +
                    "  -%*=:=@@%---%@@:%@@%---%@@=*@@%---%@@::+#+  =*:   \n" +
                    "  -%*=:.#@@###@@@*+@@@###@@@-@@@@###@@=.-+#+  -+:   \n" +
                    "  -%*=:.:*##**+***:********+:***=+=**-..:+%*--+*:   \n" +
                    "  :%*+=====-==========-=-===============+*%*----    \n" +
                    "  +##%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%##*.       \n" +
                    " =*******************************************       \n" +
                    " =++=++++++++++++++++++++++++++++++++++++++++       \n" +
                    " =*++++++++++++++++++++++++++++++++++++++++*+       \n" +
                    " :----------------------------------------::.  \n" +
                    "Gamble Gamble Gamble! Crazy Slots!!!\n" +
                    "10 USD Per round, given to random user in your group!!!\n" +
                    "Win if all 3 MIDDLE slots are the same and clear your debts!!!\n";

    @Test
    void testLuck() {
        Group newGroup = new Group("lmao");
        String username = "heehee";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        Luck newLuck = new Luck(newGroup, username);
        newLuck.printWelcome();
        String output = baos.toString();
        assertEquals(testOutput, output);
    }
}
