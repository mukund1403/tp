package seedu.duke.exceptions;

public class ExpensesException extends Exception {
    public ExpensesException(String s, Throwable err){
        super(s,err);
    }

    public ExpensesException(String s){
        super(s);
    }
}
