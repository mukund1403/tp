package seedu.duke.exceptions;

/**
 * Represents an exception that occurs when saving group information fails.
 * This exception is thrown when an error occurs while saving a group to a file.
 */
public class GroupSaveException extends Exception {
    /**
     * Constructs a new GroupSaveException with the specified detail message.
     *
     * @param message The detail message.
     */
    public GroupSaveException(String message) {
        super(message);
    }
}
