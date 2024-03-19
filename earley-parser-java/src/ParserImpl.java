import java.lang.reflect.InvocationTargetException;
import java.sql.Array;
import java.util.*;

public class ParserImpl extends Parser {
    private Map<Integer, List<ParseState>> earleyTable;
    private Production[] productions;

    private final static Production START_PROD = Grammar.getProductions()[0];

//    public static class BinaryExprImpl extends BinaryExpr {
//
//        protected BinaryExprImpl(Expr e1, Expr e2) {
//            super(e1, e2);
//        }
//
//        public BinaryExpr cast(Class<? extends BinaryExpr> expr) throws
//              NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//            return expr.getConstructor(expr).newInstance(getE1(), getE2());
//        }
//
//
//        @Override
//        public String toString() {
//            return null;
//        }
//    }

    /**
     * S   -> S + T
     * T   -> F * Lit
     * Lit -> Num
     *
     * The {@link ParseState} that is past into {@link SDT#eval(ParseState)} will be a {@link Token} with a {@link TokenType}.
     * 1. Iterate over each table.
     * 2. Within table (i), the terminal will be found; only the terminals that are completed in (i+1) will be processed.
     * 3. If {@link ParseState} in (i) is completed within (i+1), then the evaluation of the node will be propagated to
     *    the {@link ParseState#derivedFrom} state.
     * 4. Once completed, the next table will be processed.
     */
    public static abstract class SDT {

        public static Expr eval(ParseState state, Token[] tokens) {
            if (state.ast.empty()) {
                return new FloatExpr(Float.parseFloat(tokens[state.initLabel].lexeme));
            }

            if (state.ast.size() == 3) {
                switch (state.ast.get(1).getProduction().getRhsTokenType(0)) {
                    case PLUS -> {
                        var e2 = eval(state.ast.get(2), tokens);
                        var e1 =eval(state.ast.get(0), tokens);
                        var t = new PlusExpr(e1, e2);
                        return t;
                    }
                    case MINUS -> {
                        return new MinusExpr(eval(state.ast.get(0), tokens), eval(state.ast.get(2), tokens));
                    }
                    case TIMES -> {
                        return new TimesExpr(eval(state.ast.get(0), tokens), eval(state.ast.get(2), tokens));
                    }
                    case DIV -> {
                        return new DivExpr(eval(state.ast.get(0), tokens), eval(state.ast.get(2), tokens));
                    }
                    case LPAREN -> {
                        return eval(state.ast.get(1), tokens);
                    }
                }
            }
            return eval(state.ast.get(0), tokens);
        }
    }

    public static abstract class ParseStates {

        public static boolean contains(ParseState state, List<ParseState> states) {
            return states.stream()
                  .anyMatch(state::equals);
        }

        public static boolean deepContains(ParseState state, List<ParseState> states) {
            return states.stream()
                  .anyMatch(state::equals);
        }

        public static void display(List<ParseState> states) {
            states.stream()
                  .map(ParseState::getProduction)
                  .forEach(System.out::println);
        }
    }

    public static class ParseState {

        private final Production production;
        private final int initLabel;

        private final ParseState derivedFrom;
        private int index;

        private Set<ParseState> derivedStates = new HashSet<>();

        private Stack<ParseState> ast = new Stack<>();

        public ParseState(final ParseState parseState) {
            this(parseState.initLabel, parseState.index, parseState.production, parseState.derivedFrom);

            this.derivedStates.addAll(parseState.derivedStates);
        }

        public ParseState(final int initLabel, final Production production) {
            this(initLabel, 0, production, null);
        }

        public ParseState(final int initLabel, final Production production, final ParseState derivedFrom) {
            this(initLabel, 0, production, derivedFrom);
        }

        /**
         * @param initLabel   At what depth was the {@link ParseState} created within in the Earley Table
         * @param production  The production that is set to be tracked.
         * @param derivedFrom Production was derived from another Production.
         */
        public ParseState(final int initLabel, final int index, final Production production, final ParseState derivedFrom) {
            this.production = production;
            this.initLabel = initLabel;
            this.index = index;
            this.derivedFrom = derivedFrom;
        }

        public ParseState increment() {
            index++;
            return this;
        }

        public int getIndex() {
            return index;
        }

        public int getInitLabel() {
            return initLabel;
        }

        public boolean isNonterminal() {
            return index < production.rhsLength() && production.isRhsNonterminal(index);
        }

        public boolean isTokenType() {
            return index < production.rhsLength() && production.isRhsTokenType(index);
        }

        public TokenType getTokenType() {
            return isTokenType() ? production.getRhsTokenType(index) : null;
        }

        public ParseState derivedFrom() {
            return derivedFrom;
        }

        public List<ParseState> newList() {
            return new ArrayList<>(List.of(this));
        }

        public Nonterminal getRhsNonterminal() {
            return production.getRhsNonterminal(getIndex());
        }

        public boolean equals(ParseState other) {
            return this.production.equals(other.production);
        }

        public Production getProduction() {
            return production;
        }

        @Override
        public String toString() {
            return production.toString();
        }

        public boolean isCompleted() {
            return getIndex() == production.rhsLength();
        }
        public void addDerivedStates(List<ParseState> states) {
            this.derivedStates.addAll(states);
        }
    }

    @Override
    void init() {
        earleyTable = new HashMap<>();
        productions = Grammar.getProductions();
        for (int i = 0; i < tokens.length; i++) {
            earleyTable.put(i, new ArrayList<>());
        }
        earleyTable.put(0, new ParseState(0, START_PROD).newList());
    }

    @Override
    void predict(int i) {
        List<ParseState> states = earleyTable.get(i);
        int index = 0;
        while (index < states.size()) {
            ParseState state = states.get(index);
            if (state.isNonterminal()) {
                Nonterminal nt = state.getRhsNonterminal();
                List<ParseState> derivedStates = Grammar.stream()
                      .filter(p -> p.lhs.equals(nt))
                      .map(p -> new ParseState(i, p, state))
                      .filter(s -> ! ParseStates.contains(s, states))
                      .toList();
                state.addDerivedStates(derivedStates);
                states.addAll(derivedStates);
            }
            index++;
        }
    }

    @Override
    void scan(int i) {
        List<ParseState> results = new ArrayList<>();
        for (ParseState state : earleyTable.get(i)) {
            if (state.isTokenType() && tokens[i].ty.equals(state.getTokenType())) {
                ParseState s = new ParseState(state);
                results.add(s.increment());
            }
        }
        earleyTable.put(i + 1, results);
    }

    private void recursiveComplete(ParseState s, int i) {
        List<ParseState> states = earleyTable.get(s.getInitLabel());
        for (ParseState ps : states) {
            if (ps.isNonterminal() && ps.getRhsNonterminal().equals(s.getProduction().lhs)) {
                ParseState ns = new ParseState(ps);
                ns.increment();
                earleyTable.get(i + 1).add(ns);
                if (ns.isCompleted()) {
                    recursiveComplete(ns, i);
                }
            }
        }
    }

    private Stack<ParseState> recursiveFinish(ParseState state, int tableIndex) {
        if (state.getProduction().isRhsTokenType(state.index - 1)) {
            TokenType stateTokenType = state.getProduction().getRhsTokenType(state.index - 1);
            if (stateTokenType.equals(TokenType.NUM)) {
                Stack<ParseState> results = new Stack<>();
                results.push(state);
                return results;
            } else if (stateTokenType.equals(TokenType.RPAREN)) {
                Stack<ParseState> results = new Stack<>();

                List<ParseState> previousTable = earleyTable.get(tableIndex - 1);
                for (ParseState s : previousTable) {
                    if (s.index > 1 && state.getProduction().equals(s.getProduction()) && s.index == state.index - 1) {
                        recursiveFinish(s, tableIndex - 1);
                        results = s.ast;
                    }
                }
                return results;
            }
        }

        ParseState result = null;
        for (ParseState s : earleyTable.get(tableIndex)) {
            if (s.isCompleted() && state.getProduction().isRhsNonterminal(state.index - 1) &&
                  state.getProduction().getRhsNonterminal(state.index - 1).equals(s.production.lhs)) {
                result = s;
                break;
            }
        }

        Stack<ParseState> results = new Stack<>();
        if (state.getProduction().rhsLength() > 1 && tableIndex > 0) {
            boolean found = false;
            for (int index = tableIndex - 1; index >= 0 && !found; index--) {
                for (ParseState s : earleyTable.get(index)) {
                    if (s.initLabel == state.initLabel) {
                        if (s.index > 1 && state.getProduction().equals(s.getProduction()) && s.index == state.index - 1) {
                            recursiveFinish(s, index);
                            results = s.ast;
                            found = true;
                            break;
                        } else if (s.index == 1 && state.getProduction().equals(s.getProduction()) && s.index == state.index - 1) {
                            recursiveFinish(s, index);
                            results = s.ast;
                            found = true;
                            break;
                        }
                    }
                }
            }
        }
        if (result != null) {
            results.addAll(recursiveFinish(result, tableIndex));
        }
        state.ast.addAll(results);

        Stack<ParseState> ast = new Stack<>();
        ast.push(state);
        return ast;
    }

    private void recursiveFinish(ParseState state) {
        recursiveFinish(state, earleyTable.size() - 1);
    }

    @Override
    void complete(int i) {
        List<ParseState> states = new ArrayList<>(earleyTable.get(i + 1));
        for (ParseState state : states) {
            if (state.isCompleted()) {
                recursiveComplete(state, i);
            }
        }
    }

    /**
     * Finds the last table entry first, backtracks to find all derivation nodes and constructs an AST.
     * @return An AST for the parse
     * @throws Exception if failed to parse out an AST
     */
    @Override
    Expr finish() throws Exception {
        List<ParseState> finalTable = earleyTable.get(earleyTable.size() - 1);
        Optional<ParseState> hasFinalState = finalTable.stream()
              .filter(ParseState::isCompleted)
              .filter(s -> START_PROD.equals(s.production))
              .findAny();

        if (hasFinalState.isEmpty()) {
            throw new Exception();
        }

        ParseState state = hasFinalState.get();

        recursiveFinish(state);
        return SDT.eval(state, tokens);
    }

}
