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
    private void delta(Character symbol) throws SymbolNotInAlphabetException {
        if (!this.alphabet.contains(symbol)) {
            this.reset();
            throw new SymbolNotInAlphabetException();
        }
        try {
            Transition transition = Objects.requireNonNull(findTransition(this.current, symbol));
            this.current = transition.ID2;
        } catch (NullPointerException e) {
            this.reset();
            throw new IllegalStateException("Unable to carry out a transition: \n current state: " + this.current );
        }

    }

    /**
     * takes a word, makes transition according to the path defined in the word.
     * @param word
     * @return If the final state is accepting, the word'll be accepted
     * @throws SymbolNotInAlphabetException
     */
    public String acceptWord(String word) throws SymbolNotInAlphabetException {
        char[] symbols = word.toCharArray();
        for (char symbol : symbols) {
            delta(symbol);
        }
        if (this.isAccepting()) {
            return "The word is accepted. The current state: " + this.current;
        } else {
            return "The word can't be accepted. Please, check the prerequisites of the dfa. The current state: " + this.current;
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
