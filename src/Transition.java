/**
 * represents a transition δ: Q × Σ → Q, whereas Q is a set of all possible states,
 * Σ - an alphabet - a set of all possible symbols
 */
public class Transition {
    final public String ID1;
    final public String ID2;
    final public Character trigger;

    /**
     * Class constructor
     * @param id1 - an ID of the start state
     * @param id2 - an ID of the final state
     * @param trig - a transition symbol
     */
    public Transition(String id1, String id2, Character trig) {
        this.ID1 = id1;
        this.ID2 = id2;
        this.trigger = trig;
    }
}
