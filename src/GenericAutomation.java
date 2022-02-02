import java.util.Arrays;


abstract class GenericAutomation {
    protected State[] states;
    protected Transition[] transitions;
    protected final Alphabet alphabet;
    protected String start;

    /**
     * class constructor, following fields will be initialised:
     * State[] states - Q
     * Transition[] transitions - a set of all possible transitions
     * String start - an ID of the start state
     * @param alphabet - Σ
     */
    public GenericAutomation(Alphabet alphabet) {
        this.alphabet = alphabet;
        this.states = new State[0];
        this.transitions = new Transition[0];
        this.start = null;
    }

    /** resets the current state of an automat to the start state
     */
    public abstract void reset();

    /**
     * proves if a state is accepting
     * @return boolean
     */
    public abstract boolean isAccepting();

    /**
     * adds a state to the set Q
     * @param state
     * @param isStart - true if it's the start state
     * @throws StateAlreadyExistsException
     */
    public void addState(State state, boolean isStart) throws StateAlreadyExistsException {
        if (findState(state.ID) != null) {
            throw new StateAlreadyExistsException();
        }
        int len = this.states.length;
        this.states = Arrays.copyOf(this.states, len + 1);
        this.states[len] = state;
        if (isStart) {
            this.start = state.ID;
        }
    }

    /**
     * searches for a given state
     * @param id
     * @return State Object if a state was found in Q, otherwise null
     */
    public State findState(String id) {
        for(State state : this.states) {
            if (state.ID.equals(id)) {
                System.out.println(id);
                return state;
            }
        } return null;
    }
    /**
     * searches for a given transition
     * @param ID1 - transition start point
     * @param symbol - transition trigger
     * @return Transition Object if a state was found in Q, otherwise null
     */
    protected Transition findTransition(String ID1, char symbol) {
        for(Transition tr : this.transitions) {
            if (tr.ID1.equals(ID1) && tr.trigger.equals(symbol)) {
                return tr;
            }
        } return null;
    }

    /**
     * adds a transition into the array transitions.
     * @param transition
     * @throws StateDoesNotExistException
     * @throws SymbolNotInAlphabetException
     */
    protected void addTransition(Transition transition) throws StateDoesNotExistException, SymbolNotInAlphabetException {
        if (findState(transition.ID1) == null || findState(transition.ID2) == null) {
            throw new StateDoesNotExistException();
        }
        if (!this.alphabet.contains(transition.trigger)) {
            throw new SymbolNotInAlphabetException();
        }
        int len = this.transitions.length;
        this.transitions = Arrays.copyOf(this.transitions, len + 1);
        this.transitions[len] = transition;
    }
}
