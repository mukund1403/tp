package seedu.duke;

import java.util.Random;
import java.util.Scanner;

public class Luck {

    private Group currentGroup;

    public Luck(Group group) {
        this.currentGroup = group;
    }
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
            "Gamble Gamble Gamble! Crazy Slots!!!\n" +
            "10 SGD Per round, given to random user in your group!!!\n" +
            "Win if all 3 MIDDLE (ONLY MIDDLE) slots are " +
                    "the same and clear your debts!!!";

    public void printWelcome() {
        System.out.println(icon);
        System.out.println(welcome);
    }


    public void startGambling() {
        SlotMachine slotMachine = new SlotMachine(9, 9);
        System.out.println(slotMachine);
        Scanner in = new Scanner(System.in);
        System.out.println("/exit to leave. /reroll to roll again");
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            switch (userInput.trim()) {
            case "/exit":
                System.out.println("leaving the gambling den");
                return;
            case "/reroll":
                System.out.println("/exit to leave. /reroll to roll again");
                slotMachine.reroll();
                System.out.println(slotMachine);
                if (slotMachine.isWin()) {
                    System.out.println("All debts clear!!");
                    //settle
                } else {
                    System.out.println("Poor, reroll again to win");
                    //add expenses to random user
                    Random rand = new Random();
                    int randomUserIndex = rand.nextInt(this.currentGroup.getMembers().size());
                    //add expense from here
                }
                break;
            default:
                System.out.println("/exit to leave. /reroll to roll again");
                break;
            }
        }
    }

    public static void main(String[] args) {

    }





}
