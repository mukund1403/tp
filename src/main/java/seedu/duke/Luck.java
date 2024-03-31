package seedu.duke;

import java.util.Scanner;

public class Luck {
    private static final String icon =
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
            " :----------------------------------------::.  ";
    private static final String welcome =
            "Gamble Gamble Gamble! Crazy Slots!!!\n"
                    + "Win if all 3 middle slots are the same!!";
    static void printWelcome() {
        System.out.println(icon);
        System.out.println(welcome);
    }
    static void startGambling() {
        SlotMachine slotMachine = new SlotMachine(9, 9);
        System.out.println(slotMachine);
        Scanner in = new Scanner(System.in);
        System.out.println("/exit to leave. /reroll to roll again");
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            switch (userInput) {
            case "/exit":
                System.out.println("leaving the gambling den");
                return;
            case "/reroll":
                System.out.println("/exit to leave. /reroll to roll again");
                slotMachine.reroll();
                System.out.println(slotMachine);
                if (slotMachine.isWin()) {
                    System.out.println("All debts clear!!");
                } else {
                    System.out.println("Poor, reroll again to win");
                }
                break;
            default:
                break;
            }
        }
    }





}
