package seedu.duke.commands;
//@@author mukund1403
import seedu.duke.Expense;
import seedu.duke.exceptions.ExpensesException;
import seedu.duke.Group;

import java.util.List;
import java.util.Optional;

public class ListCommand {
    public static void printList() throws ExpensesException {
        Optional<Group> currentGroup = Group.getCurrentGroup();
        if (currentGroup.isEmpty()) {
            String exceptionMessage = "Not signed in to a Group! Use 'create <name>' to create Group";
            throw new ExpensesException(exceptionMessage);
        }
        List<Expense> expenses = currentGroup.get().getExpenseList();
        System.out.println("The expenses for this group are:\n");
        int i = 1;
        for(Expense expense : expenses){
            System.out.println(i + ". " + expense.toString());
            i++;
        }
    }
}
