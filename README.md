# generic_automation_dfa
The project contains the "generic automation" abstract class along with a concrete case of generic automation - the deterministic finite automata (dfa). 

<h1> DFA </h1>

A deterministic finite automata (dfa) is a finite-state machine that accepts or rejects a given string of symbols, by running through a state sequence uniquely determined by the string. 
The machine is called determentistic because for every pair of state and symbol there is only one output state. The machine is called finite, because of the set F that must be defined for a dfa.

A DFA can be represented by a 5-tuple (Q, ∑, δ, q0, F) where −

Q is a finite set of states.

∑ is a finite set of symbols called the alphabet.

δ is the transition function where δ: Q × ∑ → Q

q_0 is the initial state from where any input is processed (q_0 ∈ Q).

F is a set of final state/states of Q (F ⊆ Q).



