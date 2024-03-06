# Instructions

Please only modify the files `AutomatonImpl.java`, `CompilerFrontendImpl.java`, `ParserImpl.java`, and optionally `Grammar.java`.
When we grade your solutions, we will replace all other files with our own versions, so please ensure all required code is in these 4 files.

## Part 1

In the file `AutomatonImpl.java`, implement an interpreter for nondeterministic finite automata.
This interpreter must implement all of the abstract methods declared in `Automaton.java`.

## Part 2

In the file `CompilerFrontendImpl.java`, construct finite automata corresponding to the following lexer specification:
```
NUM: [0-9]*\.[0-9]+
PLUS: \+
MINUS: -
TIMES: \*
DIV: /
LPAREN: \(
RPAREN: \)
WHITE_SPACE (' '|\n|\r|\t)*
```
Use these automata to construct a lexer of type `LexerImpl` (as defined in `LexerImpl.java`). An example of how to construct a lexer is given in the `test_lexer` function in `App.java`.

## Part 3

Write an Earley parser for the following grammar using the given SDT rules:
```
T -> T AddOp F              { if ($2.type == TokenType.PLUS) { $$ = new PlusExpr($1,$3); } else { $$ = new MinusExpr($1, $3); } }
T -> F                      { $$ = $1; }
F -> F MulOp Lit            { if ($2.type == TokenType.Times) { $$ = new TimesExpr($1,$3); } else { $$ = new DivExpr($1, $3); } }
F -> Lit                    { $$ = $1; }
Lit -> NUM                  { $$ = new FloatExpr(Float.parseFloat($1.lexeme)); }
Lit -> LPAREN T RPAREN      { $$ = $2; }
AddOp -> PLUS               { $$ = $1; }
AddOp -> MINUS              { $$ = $1; }
MulOp -> TIMES              { $$ = $1; }
MulOp -> DIV                { $$ = $1; }
```
To implement the parser, you should implemment the `predict`, `scan`, and `complete` functions in `ParserImpl.java`. See also `Parser.java`, which defines the remaining glue components to complete the parser. Also not that, in your functions in `ParserImpl.java`, you may assume the token array `tokens` has been initialized.

For your convenience, data structures related to this grammar are in `Grammar.java`, and you may use and modify these as you see fit.

## Expected Results

The expected results of running the test cases in `App.java` is provided in `expected-results.md`.
