package seedu.duke.commands;

import seedu.duke.Group;
import seedu.duke.Luck;
import seedu.duke.exceptions.ExpensesException;
import seedu.duke.exceptions.LuckException;

import java.util.Optional;

public class LuckCommand {
    /**
     * Checks if user is currently in a Group
     * Checks if the group has more than one person.
     * Creates a Luck object
     * Prints the welcome message and start the slot machine.
     * @throws LuckException If user is not in a Group
     * @throws LuckException If the group has less than 2 people.
     */
    public static void handleLuck(String argument) throws LuckException, ExpensesException {
        // Checks if user is currently in a Group
        Optional<Group> currentGroup = Group.getCurrentGroup();
        if (currentGroup.isEmpty()) {
            String exceptionMessage = "Not signed in to a Group! Use 'create <name>' to create Group";
            throw new LuckException(exceptionMessage);
        }
        Group selectedGroup = currentGroup.get();
        if (selectedGroup.getMembers().size() <= 1){
            String exceptionMessage = "You need more people to get lucky!!!";
            throw new LuckException(exceptionMessage);
        }
        Luck newLuck = new Luck(selectedGroup, argument);
        newLuck.printWelcome();
        newLuck.startGambling();
    }
}
