package seedu.duke;

import seedu.duke.commands.ExpenseCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.exceptions.ExpensesException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Parser {
    /**
     * List of parameters to extract from user input.
     * For example, "/amount (amount)".
     * Add new Keys to extract additional user parameters for future functionality.
     */
    private static final String[] paramKeys = {"amount", "paid", "user"};

    private final String userInput;

    /**
     * First word of user input.
     */
    private String command = null;

    /**
     * Input between first word and '/' character.
     * For example, "(command) (argument) /(parameter) (parameter input)...".
     */
    private String argument = null;

    /**
     * Additional parameters provided by user.
     */
    private HashMap<String, ArrayList<String>> params = createParams();

    public static class EndProgramException extends Exception {

    }

    /**
     * Constructor for Test purposes.
     */
    public Parser(String userInput, String command, String argument,
                  String[] amount, String[] paid, String[] user) {
        this.userInput = userInput;
        this.command = command;
        this.argument = argument;
        this.params.put("amount", new ArrayList<>(List.of(amount)));
        this.params.put("paid", new ArrayList<>(List.of(paid)));
        this.params.put("user", new ArrayList<>(List.of(user)));
    }

    public Parser(String userInput) {
        this.userInput = userInput;
        this.parseUserInput();
    }

    /**
     * Creates a new HashMap with Keys equal to additional parameters users might input.
     * Values are arrays that store user input.
     *
     * @return HashMap with Keys in 'additionalFields' and empty array Values.
     */
    private HashMap<String, ArrayList<String>> createParams() {
        HashMap<String, ArrayList<String>> additionalInfo = new HashMap<>();

        for (String paramKey : paramKeys) {
            additionalInfo.put(paramKey, new ArrayList<>());
        }

        return additionalInfo;
    }

    /**
     * Process the String userInput and populates corresponding fields of Parser object.
     */
    public void parseUserInput() {
        String[] tokens = userInput.split(" ", 2);
        this.command = tokens[0].toLowerCase().trim();

        if (tokens.length == 1) {
            return;
        }

        String[] arguments = tokens[1].split("/");
        this.argument = arguments[0].trim();

        for (int i = 1; i < arguments.length; i++) {
            String[] subTokens = arguments[i].split(" ", 2);
            if (subTokens.length == 1) {
                continue;
            }

            String subCommand = subTokens[0].toLowerCase().trim();
            String subArgument = subTokens[1].trim();
            if (!subArgument.isEmpty() && params.containsKey(subCommand)) {
                params.get(subCommand).add(subArgument);
            }
        }
    }

    /**
     * Returns String summarising contents of Parser object.
     * For easier debug printing.
     *
     * @return Contents of Parser object.
     */
    @Override
    public String toString() {
        StringBuilder parser = new StringBuilder();

        parser.append("command: ").append(command).append("\n");

        parser.append("argument: ").append(argument).append("\n");

        for (String paramKey : paramKeys) {
            parser.append(paramKey).append(": ");
            for (String item : params.get(paramKey)) {
                parser.append(item).append(" ");
            }
            parser.append("\n");
        }

        return parser.toString();
    }

    public void handleUserInput() throws EndProgramException, ExpensesException {
        switch (command) {
        case "bye":
            if (Group.isInGroup()) {
                GroupCommand.exitGroup(argument);
            }
            throw new EndProgramException();
        case "help":
            // Help code here
            Help.printHelp();
            assert (true);
            break;
        case "create":
            GroupCommand.createGroup(argument);
            break;
        case "delete":
            GroupCommand.deleteGroup(argument);
            break;
        case "member":
            GroupCommand.addMember(argument);
            break;
        case "enter":
            GroupCommand.enterGroup(argument);
            break;
        case "exit":
            GroupCommand.exitGroup(argument);
            break;
        case "expense":
            ExpenseCommand.addExpense(params, argument, userInput);
            break;
        case "settle":
            String[] commandParts = userInput.split(" ");
            if (commandParts.length < 4 || !commandParts[2].equals("/user")) {
                System.out.println("Invalid command. Syntax: settle payerName /user payeeName");
                return;
            }

            String payer = commandParts[1];
            String payee = commandParts[3];

            Group.getCurrentGroup().ifPresent(group -> group.settle(payer, payee));

            break;
        case "luck":
            Luck.printWelcome();
            Luck.startGambling();
            break;
        case "list":
            ListCommand.printList();
            break;
        case "balance":
            // Checks if user is currently in a Group
            // named 'currentGroup1' to prevent conflict with previous declaration
            Optional<Group> currentGroup1 = Group.getCurrentGroup();
            if (currentGroup1.isEmpty()) {
                String exceptionMessage = "Not signed in to a Group! Use 'create <name>' to create Group";
                throw new ExpensesException(exceptionMessage);
            }

            // Checks if user specified is in Current Group
            if (!currentGroup1.get().isMember(argument)) {
                String exceptionMessage = argument + " is not in current Group!";
                throw new ExpensesException(exceptionMessage);
            }
            Balance balance = new Balance(argument, currentGroup1.get());
            balance.printBalance();
            break;
        default:
            System.out.println("That is not a command. " +
                    "Please use one of the commands given here");
            Help.printHelp();
            break;
        }
    }

}
