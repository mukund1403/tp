package seedu.duke;

import org.junit.jupiter.api.Test;

public class UserInterfaceTest {
    @Test
    public void printTest() {
        UserInterface.printMessage("Success", MessageType.SUCCESS);
        UserInterface.printMessage("Message");
        UserInterface.printMessage("Error", MessageType.ERROR);

    }
}
