/**
 * represents an exception StateDoesNotExist,
 * that'll be thrown if a state wasn't found in Q.
 */
public class StateDoesNotExistException extends Exception {
    /**
     * class constructor
     */
    public StateDoesNotExistException() {
        super("Unable to add the transition: one of the states doesn't exist.");
    }
}
