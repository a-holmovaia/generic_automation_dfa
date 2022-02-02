/**
 * represents an exception TransitionAlreadyExists
 * that'll be thrown if a transition already exists in the list "transitions".
 */

public class TransitionAlreadyExistsException extends Exception {
    /**
     * class constructor
     */
    public TransitionAlreadyExistsException() {

        super("Unable to create a transition: transition already exists");
    }
}
