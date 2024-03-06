
public abstract class Parser {
    /* Initializes the state of the parser */
    abstract void init();

    /* The predict operation */
    abstract void predict(int i);

    /* The scan operation */
    abstract void scan(int i);

    /* The complete operation */
    abstract void complete(int i);

    /* Finalize the parser state and return the resulting AST.
     * May return "null" if no AST exists for this input.
    */
    abstract Expr finish();

    Token[] tokens;


    public Parser() {}


    Expr parse(TokenList token_list) {
        // Copy the token list into an array
        int num_tokens = token_list.length();
        tokens = new Token[token_list.length()];
        for(int i = 0; i < num_tokens; i++) {
            tokens[i] = token_list.elem;
            token_list = token_list.rest;
        }

        // Run the parser
        init();
        for(int i = 0; i < tokens.length; i++) {
            predict(i);
            scan(i);
            complete(i);
        }

        // Return the result
        return finish();
    }
}
