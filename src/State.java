/**
 * represents elements of Q - a set of all possible automat's states
 */
public class State {
    public final String ID;
    private final boolean isAccepting;

    /**
     * Class constructor
     * @param id - state id
     * @param status - true - if a state is accepting
     */
    public State(String id, boolean status) {
        this.ID = id;
        this.isAccepting = status;
    }

    /**
     * getter for status
     * @return true if accepting
     */
    public boolean getAccepting() {
        return this.isAccepting;
    }
}
