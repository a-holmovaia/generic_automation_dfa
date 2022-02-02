import java.util.Arrays;

/**
 * represents Σ - an alphabet -
 * which contains symbols σ needed for interrogation with an automat
 */
public class Alphabet {
    private final Character[] alpha;

    /**
     * Class constructor
     * @param alpha - Array of characters, an alphabet
     */
    public Alphabet(Character[] alpha) {
        this.alpha = alpha;
    }

    /**
     * proves if a character ch is in an alphabet
     * @param ch
     * @return boolean
     */
    public boolean contains(char ch) {
        int ind = Arrays.toString(this.alpha).indexOf(ch);
        return ind >= 0;
    }
    /**
     * getter for alpha
     */
    public String getAlpha() {
        return this.alpha.toString();
    }

}
