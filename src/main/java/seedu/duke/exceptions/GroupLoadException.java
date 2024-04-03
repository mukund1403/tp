package seedu.duke.exceptions;

/**
 * Represents an exception that occurs when loading group information fails.
 * This exception is thrown when an error occurs while loading a group from a file.
 */
public class GroupLoadException extends UniversalExceptions {
    /**
     * Constructs a new GroupLoadException with the specified detail message.
     *
     * @param message The detail message.
     */
    public GroupLoadException(String message) {
        super(message);
    }
}
