package seedu.duke.exceptions;

/**
 * Represents an exception that occurs during the deletion of a group.
 * This exception is thrown when there is an error or failure in the group deletion process.
 */
public class GroupDeleteException extends Exception {
    /**
     * Constructs a new GroupDeleteException with the specified detail message.
     *
     * @param message the detail message describing the exception
     */
    public GroupDeleteException(String message) {
        super(message);
    }
}
