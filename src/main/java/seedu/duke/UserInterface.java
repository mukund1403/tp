package seedu.duke;

public class UserInterface {

    private static final String SUCCESS_BORDER = "<----------SUCCESS----------->";
    private static final String ERROR_BORDER = "<-----------ERROR------------>";
    private static final String DEFAULT_BORDER = "<---------------------------->";
    private static final String HAPPY_CAT =
            "  /\\_/\\\n" +
                    " ( ^.^ )\n" +
                    "  > ^ <";
    private static final String GRUMPY_CAT =
            "  /\\_/\\\n" +
                    " ( >_< )\n" +
                    "  > ^ <";

    private static final String SAD_CAT =
            "  /\\_/\\\n" +
                    " ( ._. )\n" +
                    "  > ^ <";

    public static void printMessage(String message, MessageType type) {
        switch (type) {
        case SUCCESS:
            System.out.println(HAPPY_CAT);
            System.out.println(SUCCESS_BORDER);
            break;
        case ERROR:
            System.out.println(GRUMPY_CAT);
            System.out.println(ERROR_BORDER);
            break;
        default:
            break;
        }

        System.out.println(message);
        System.out.println(DEFAULT_BORDER);
    }

    public static void printMessage(String message) {
        System.out.println(SAD_CAT);
        System.out.println(DEFAULT_BORDER);
        System.out.println(message);
        System.out.println(DEFAULT_BORDER);
    }
}
