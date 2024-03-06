import java.util.Objects;

/*
 * The classes in this file provide an object-oriented implementation
 * of the following grammar:
 *   T -> T AddOp F
 *   T -> F
 *   F -> F MulOp Lit
 *   F -> Lit
 *   Lit -> NUM
 *   Lit -> LPAREN T RPAREN
 *   AddOp -> PLUS
 *   AddOp -> MINUS
 *   MulOp -> TIMES
 *   MulOp -> DIV
 * 
 *   The terminals NUM, LPAREN, RPAREN, PLUS, MINUS, TIMES, and DIV are defined in "Lexer.java".
 */


/*
 * An enum representing nonterminals
 */
enum Nonterminal {
    T,
    F,
    Lit,
    AddOp,
    MulOp
}

// A class representing an individual production.
class Production {
    Nonterminal lhs;

    // Always contains either "Nonterminal" or "TokenType"
    private Object[] rhs;

    public Production(Nonterminal lhs_, int rhs_size) {
        lhs = lhs_;
        rhs = new Object[rhs_size];
    }

    public int rhsLength() {
        return rhs.length;
    }

    public void setRhs(int index, Nonterminal n) {
        rhs[index] = n;
    }

    public void setRhs(int index, TokenType n) {
        rhs[index] = n;
    }

    // Returns true if rhs[index] is a nonterminal, and false otherwise.
    public boolean isRhsNonterminal(int index) {
        return rhs[index] instanceof Nonterminal;
    }

    // Returns true if rhs[index] is a terminal, and false otherwise.
    public boolean isRhsTokenType(int index) {
        return rhs[index] instanceof TokenType;
    }

    // Returns the nonterminal represented by rhs[index].
    // Returns null if rhs[index] is not a nonterminal.
    public Nonterminal getRhsNonterminal(int index) {
        if(rhs[index] instanceof Nonterminal) {
            return (Nonterminal) rhs[index];
        }
        return null;
    }

    // Returns the token represented by the literal at rhs[index].
    // Returns null if rhs[index] is not a terminal.
    public TokenType getRhsTokenType(int index) {
        if(rhs[index] instanceof TokenType) {
            return (TokenType) rhs[index];
        }
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lhs, rhs);
    }

    @Override
    public boolean equals(Object obj) {
        Production other = (Production) obj;
        if (other.lhs != lhs) {
            return false;
        }

        if(rhs.length != other.rhs.length) {
            return false;
        }
        for(int i = 0; i < rhs.length; i++) {
            if(rhs[i] instanceof Nonterminal) {
                if(!(other.rhs[i] instanceof Nonterminal)) {
                    return false;
                }
                if (((Nonterminal) rhs[i]) != ((Nonterminal) other.rhs[i])) {
                    return false;
                }
            } else if (rhs[i] instanceof TokenType) {
                if(!(other.rhs[i] instanceof TokenType)) {
                    return false;
                }
                if(((TokenType) rhs[i]) != ((TokenType) other.rhs[i])) {
                    return false;
                }
            } else { assert(false); }
        }
        return true;
    }

    @Override
    public String toString() {
        String ret = lhs.toString() + " -> ";
        for(int i = 0; i < rhs.length; i++) {
            if(rhs[i] instanceof Nonterminal) {
                ret += ((Nonterminal) rhs[i]).toString() + " ";
            } else if (rhs[i] instanceof TokenType) {
                ret += ((TokenType) rhs[i]).toString() + " ";
            } else { assert(false); }
        }
        return ret;
    }
}

// A class representing the grammar defined at the start of this file. It
// represents this grammar using an array of productions, which can be
// retrieved by calling Grammar.getProductions().
public class Grammar {
    private static Production[] productions = null;
    public static Production[] getProductions() {
        if(productions == null) {
            productions = new Production[10];

            // T -> T AddOp F
            productions[0] = new Production(Nonterminal.T, 3);
            productions[0].setRhs(0, Nonterminal.T);
            productions[0].setRhs(1, Nonterminal.AddOp);
            productions[0].setRhs(2, Nonterminal.F);
            
            // T -> F
            productions[1] = new Production(Nonterminal.T, 1);
            productions[1].setRhs(0, Nonterminal.F);

            // F -> F MulOp Lit
            productions[2] = new Production(Nonterminal.F, 3);
            productions[2].setRhs(0, Nonterminal.F);
            productions[2].setRhs(1, Nonterminal.MulOp);
            productions[2].setRhs(2, Nonterminal.Lit);

            // F -> Lit
            productions[3] = new Production(Nonterminal.F, 1);
            productions[3].setRhs(0, Nonterminal.Lit);

            // Lit -> num
            productions[4] = new Production(Nonterminal.Lit, 1);
            productions[4].setRhs(0, TokenType.NUM);

            // Lit -> lparen T rparen
            productions[5] = new Production(Nonterminal.Lit, 3);
            productions[5].setRhs(0, TokenType.LPAREN);
            productions[5].setRhs(1, Nonterminal.T);
            productions[5].setRhs(2, TokenType.RPAREN);

            // AddOp -> plus
            productions[6] = new Production(Nonterminal.AddOp, 1);
            productions[6].setRhs(0, TokenType.PLUS);

            // AddOp -> minus
            productions[7] = new Production(Nonterminal.AddOp, 1);
            productions[7].setRhs(0, TokenType.MINUS);

            // MulOp -> times
            productions[8] = new Production(Nonterminal.MulOp, 1);
            productions[8].setRhs(0, TokenType.TIMES);

            // MulOp -> div
            productions[9] = new Production(Nonterminal.MulOp, 1);
            productions[9].setRhs(0, TokenType.DIV);
        }
        return productions;
    }

    public static String staticToString() {
        Production[] prods = getProductions();
        String ret = "";
        for(int i = 0; i< prods.length; i++) {

            ret += ((Production) prods[i]).toString() + "\n";
        }
        return ret;
    }
}
