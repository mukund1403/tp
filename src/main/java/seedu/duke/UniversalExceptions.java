package seedu.duke;

public class UniversalExceptions extends Exception {
    private final String errorMessage;
    UniversalExceptions(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}
