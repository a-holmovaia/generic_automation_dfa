/**
 * represents an exception StateAlreadyExists,
 * that'll be thrown if a state already exists in Q
 */
public class StateAlreadyExistsException extends Exception {
    /**
     * class constructor
     */
    public StateAlreadyExistsException() {
        super("State already exists!");

    }
}

