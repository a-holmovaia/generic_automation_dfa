import java.util.Objects;

/**
 * represents a DFA with tupels (Q, Σ, δ, q0, F)
 */
public class DFA extends GenericAutomation {
    private String current;

    /**
     * {@inheritDoc}
     */
    public DFA(Alphabet alphabet) {
        super(alphabet);
        this.current = this.start;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.current = this.start;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccepting() {
        State state = this.findState(current);
        return state.getAccepting();
    }

    /**
     * creates a transition
     * @param q1 - start state
     * @param q2 - end state
     * @param symbol - trigger
     * @throws StateDoesNotExistException
     * @throws SymbolNotInAlphabetException
     * @throws TransitionAlreadyExistsException
     */
    public void makeTransition(String q1, String q2, Character symbol) throws StateDoesNotExistException, SymbolNotInAlphabetException, TransitionAlreadyExistsException {
        Transition trans = new Transition(q1, q2, symbol);
        if (findTransition(trans.ID1, trans.trigger) != null) {
            throw new TransitionAlreadyExistsException();
        }
        addTransition(trans);
    }

    /**
     * makes a transition defined by "symbol" from the start state to the end state.
     * @param symbol
     * @return ID of the current state
     */
    public String delta(Character symbol) throws SymbolNotInAlphabetException {
        if (!this.alphabet.contains(symbol)) {
            throw new SymbolNotInAlphabetException();
        }
        try {
            Transition transition = Objects.requireNonNull(findTransition(this.current, symbol));
            this.current = transition.ID2;
            return this.current;
        } catch (NullPointerException e) {
            throw new IllegalStateException("Unable to carry out a transition: \n current state: " + this.current );
        }

    }

    /**
     * getter
     * @return current
     */
    public String getCurrent() {
        return this.current;
    }
}
