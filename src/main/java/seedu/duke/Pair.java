package seedu.duke;

//@@author cs2030-reused
//Utility class for Java Pair given in cs2030 module
public class Pair<T, U> {
    private final T t;
    private final U u;

    public Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T getKey() {
        return this.t;
    }

    public U getValue() {
        return this.u;
    }

    @Override
    public String toString() {
        return "(" + this.t + ", " + this.u + ")";
    }
}
//@@author
