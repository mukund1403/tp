package seedu.duke;

import seedu.duke.exceptions.ExpensesException;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo =
                ".------..------..------..------..------..------..------..------..------..------.\n" +
                        "|S.--. ||P.--. ||L.--. ||I.--. ||T.--. ||L.--. ||I.--. ||A.--. ||N.--. ||G.--. |\n" +
                        "| :/\\: || :/\\: || :/\\: || (\\/) || :/\\: || :/\\: || (\\/) || (\\/) || :(): || :/\\: |\n" +
                        "| :\\/: || (__) || (__) || :\\/: || (__) || (__) || :\\/: || :\\/: || ()() || :\\/: |\n" +
                        "| '--'S|| '--'P|| '--'L|| '--'I|| '--'T|| '--'L|| '--'I|| '--'A|| '--'N|| '--'G|\n" +
                        "`------'`------'`------'`------'`------'`------'`------'`------'`------'`------'\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Start splitting your expenses now!");

        Scanner in = new Scanner(System.in);

        Help.printHelp();

        while(in.hasNextLine()) {
            String userInput = in.nextLine();
            Parser parser = new Parser(userInput);

            try {
                parser.handleUserInput();
            } catch (Parser.EndProgramException e) {
                break;
            } catch (ExpensesException e) {
                UserInterface.printMessage(e.getMessage(), MessageType.ERROR);
            }
        }
        System.out.println("Goodbye!");
    }
}
