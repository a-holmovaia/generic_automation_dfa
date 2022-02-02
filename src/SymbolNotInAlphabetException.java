/**
 * represents an exception SymbolNotInAlphabet
 * that'll be thrown if a symbol can't be found in Σ
 */
public class SymbolNotInAlphabetException extends Exception {
    /**
     * class constructor
     */
    public SymbolNotInAlphabetException() {
        super("Unable to add the transition: illegal symbol");
    }
}
