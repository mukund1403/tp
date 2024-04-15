package seedu.duke;

import seedu.duke.exceptions.ExpensesException;

import java.util.ArrayList;
import java.util.Random;
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
            "Gamble Gamble Gamble! Crazy Slots!!!\n" +
                    "10 USD Per round, given to random user in your group!!!\n" +
                    "Win if all 3 MIDDLE slots are " +
                    "the same and clear your debts!!!";

    private Group currentGroup;
    private String username;

    /**
     * Slot Machine interface
     *
     * @param group The Group the selected User is in
     * @param username The username of the user
     */
    public Luck(Group group, String username) {
        this.currentGroup = group;
        this.username = username;
    }

    /**
     * Prints welcome message
     *
     */
    public void printWelcome() {
        System.out.println(icon);
        System.out.println(welcome);
    }


    public void startGambling() throws ExpensesException {
        SlotMachine slotMachine = new SlotMachine(9, 9);
        System.out.println(slotMachine);
        Scanner in = new Scanner(System.in);
        System.out.println("/exit to leave. /reroll to roll again");
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            switch (userInput.trim()) {
            case "/exit":
                System.out.println("leaving the gambling den");
                Help.printHelp();
                return;
            case "/reroll":
                System.out.println("/exit to leave. /reroll to roll again");
                slotMachine.reroll();
                System.out.println(slotMachine);
                if (slotMachine.isWin()) {
                    System.out.println("All debts clear!!");
                    currentGroup.settleAll(this.username);
                    break;
                }
                System.out.println("Poor, reroll again to win. " +
                        "Win if all 3 MIDDLE slots are the same");
                //add expenses to random user
                Expense buyInDebt = calculateDebt();
                this.currentGroup.addExpense(buyInDebt);
                break;
            default:
                System.out.println("/exit to leave. /reroll to roll again");
                break;
            }
        }
    }

    /**
     * Creates an expense from each reroll
     * @return of $10USD expense paid by user to a random person in the group
     */
    private Expense calculateDebt() throws ExpensesException {
        Random rand = new Random();
        int randomUserIndex = rand.nextInt(
                this.currentGroup.getMembers().size());
        while(this.currentGroup.getMembers()
                .get(randomUserIndex).getName().equals(username)) {
            randomUserIndex = rand.nextInt(this.currentGroup.
                    getMembers().size());
        }
        User randomLuckyUser = this.currentGroup.getMembers().
                get(randomUserIndex);
        String buyInDescription = "unlucky moments";
        Money buyIn = new Money(10.0f, CurrencyConversions.USD);
        Pair<String, Money> paymentDetails =
                new Pair<String, Money>(this.username, buyIn);
        ArrayList<Pair<String, Money>> payee =
                new ArrayList<Pair<String, Money>>();
        payee.add(paymentDetails);
        return new Expense(randomLuckyUser.getName(),
                buyInDescription, buyIn, payee);
    }

}
