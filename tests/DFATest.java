import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// For the graphic representation of the test case see "graph_test.jpg" in the project directory

class DFATest {
    Alphabet alpha = new Alphabet(new Character[]{'!', '?'});
    DFA dfa = new DFA(alpha);
    State st1 = new State("wake up", false);
    State st2 = new State("smile", false);
    State st3 = new State("speak", false);
    State st4 = new State("eat", false);
    State st5 = new State("go home", false);
    State st6 = new State("sleep", true);

    @BeforeEach
    void config() throws StateAlreadyExistsException, SymbolNotInAlphabetException, StateDoesNotExistException, TransitionAlreadyExistsException {
        dfa.addState(st1, true);
        dfa.addState(st2, false);
        dfa.addState(st3, false);
        dfa.addState(st4, false);
        dfa.addState(st5, false);
        dfa.addState(st6, false);
        dfa.makeTransition("wake up", "smile", '?');
        dfa.makeTransition("smile", "speak", '!');
        dfa.makeTransition( "smile", "eat", '?');
        dfa.makeTransition( "eat", "smile", '!');
        dfa.makeTransition("speak", "go home", '?');
        dfa.makeTransition("go home", "sleep", '!');
        dfa.reset();
    }

    @Test
    void addStateTest() throws StateAlreadyExistsException {
        State st7 = new State("go forward", false);
        dfa.addState(st7, false);
        Assertions.assertEquals(st7, dfa.findState("go forward"));
    }

    @Test
    void findStateTest() {
        Assertions.assertEquals(st2, dfa.findState("smile"));
    }

    @Test
    void testDFA1() throws SymbolNotInAlphabetException {
        dfa.delta('?');
        Assertions.assertEquals("smile", dfa.getCurrent());
        Assertions.assertFalse(dfa.isAccepting());
    }

    @Test
    void testDFA2() throws SymbolNotInAlphabetException {
        dfa.delta('?');
        dfa.delta('!');
        Assertions.assertEquals("speak", dfa.getCurrent());
        Assertions.assertFalse(dfa.isAccepting());
    }

    @Test
    void testDFA3() throws SymbolNotInAlphabetException {
        dfa.delta('?');
        dfa.delta('!');
        dfa.delta('?');
        Assertions.assertEquals("go home", dfa.getCurrent());
        Assertions.assertFalse(dfa.isAccepting());
    }

    @Test
    void testDFA4() throws SymbolNotInAlphabetException {
        dfa.delta('?');
        dfa.delta('!');
        dfa.delta('?');
        dfa.delta('!');
        Assertions.assertTrue(dfa.isAccepting());
    }

    @Test
    void testDFA5() throws SymbolNotInAlphabetException {
        dfa.delta('?');
        dfa.delta('?');
        Assertions.assertEquals("eat", dfa.getCurrent());
        Assertions.assertFalse(dfa.isAccepting());
    }

    @Test
    void testDFA6() throws SymbolNotInAlphabetException {
        dfa.delta('?');
        dfa.delta('?');
        dfa.delta('!');
        dfa.delta('?');
        dfa.delta('!');
        dfa.delta('?');
        dfa.delta('!');
        dfa.delta('!');
        dfa.delta('?');
        dfa.delta('!');
        Assertions.assertTrue(dfa.isAccepting());
    }

    @Test
    void testEStateExists() {
        Assertions.assertThrows(StateAlreadyExistsException.class, ()->{dfa.addState(st1, true);});
    }

    @Test
    void testEStateNotE() {
        Assertions.assertThrows(StateDoesNotExistException.class, ()->{dfa.makeTransition("fight", "wake up", '?');});
    }

    @Test
    void testIllegalSymbol() {
        Assertions.assertThrows(SymbolNotInAlphabetException.class, ()->{dfa.makeTransition("smile", "wake up", 'l');});
    }

    @Test
    void testIllegalSymbol2() throws SymbolNotInAlphabetException {
        dfa.delta('?');
        dfa.delta('?');
        Assertions.assertThrows(SymbolNotInAlphabetException.class, ()->{dfa.delta('h');});
    }

    @Test
    void testIllegalTransition() {
        Assertions.assertThrows(TransitionAlreadyExistsException.class, ()->{dfa.makeTransition("wake up", "smile", '?');});
    }

    @Test
    void testTransitionNotPossible() throws SymbolNotInAlphabetException {
        dfa.delta('?');
        dfa.delta('!');
        Assertions.assertThrows(IllegalStateException.class,
                ()->{dfa.delta('!');});
    }
}


