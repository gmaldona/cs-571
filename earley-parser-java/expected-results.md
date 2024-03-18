```
a1 accepts empty string (should be false): false
a1 accepts 'a' (should be true): true
a1 accepts 'ab' (should be false): false
a2 accepts empty string (should be false): false
a2 accepts 'a' (should be true): true
a2 accepts 'ab' (should be false): false
a2 has transition to 'c' (should be true): true
a2 accepts 'abc' (should be true): true
a2 has transition to 'z' (should be false): false
a2 has transition to 'a' (should be false): false
a_num accepts "1243f" (should be false): false
a_num accepts "3" (should be true): true
a_num accepts "124" (should be true): true
a_num accepts "90983724847619547905718498504" (should be true): true
a_num accepts "124.3f" (should be false): false
Lexing "1 2  3 44214" with simple lexer.
result: [NUM("1"); WHITE_SPACE(" "); NUM("2"); WHITE_SPACE("  "); NUM("3"); WHITE_SPACE(" "); NUM("44214")]
Input: 100.0 + .02 -032.1* (   0.2 / 3.0 /
         4.05)
Tokens: [NUM("100.0"); WHITE_SPACE(" "); PLUS("+"); WHITE_SPACE(" "); NUM(".02"); WHITE_SPACE(" "); MINUS("-"); NUM("032.1"); TIMES("*"); WHITE_SPACE(" "); LPAREN("("); WHITE_SPACE("   "); NUM("0.2"); WHITE_SPACE(" "); DIV("/"); WHITE_SPACE(" "); NUM("3.0"); WHITE_SPACE(" "); DIV("/"); WHITE_SPACE("
         "); NUM("4.05"); RPAREN(")")]
Tokens without whitespace: [NUM("100.0"); PLUS("+"); NUM(".02"); MINUS("-"); NUM("032.1"); TIMES("*"); LPAREN("("); NUM("0.2"); DIV("/"); NUM("3.0"); DIV("/"); NUM("4.05"); RPAREN(")")]
((100.0 + 0.02) - (32.1 * ((0.2 / 3.0) / 4.05)))
Input: 1.0 + 2.0 + 3.0 + 4.0
Tokens: [NUM("1.0"); WHITE_SPACE(" "); PLUS("+"); WHITE_SPACE(" "); NUM("2.0"); WHITE_SPACE(" "); PLUS("+"); WHITE_SPACE(" "); NUM("3.0"); WHITE_SPACE(" "); PLUS("+"); WHITE_SPACE(" "); NUM("4.0")]
Tokens without whitespace: [NUM("1.0"); PLUS("+"); NUM("2.0"); PLUS("+"); NUM("3.0"); PLUS("+"); NUM("4.0")]
(((1.0 + 2.0) + 3.0) + 4.0)
Input: 1.0 + 2.0 - 3.0 + 4.0
Tokens: [NUM("1.0"); WHITE_SPACE(" "); PLUS("+"); WHITE_SPACE(" "); NUM("2.0"); WHITE_SPACE(" "); MINUS("-"); WHITE_SPACE(" "); NUM("3.0"); WHITE_SPACE(" "); PLUS("+"); WHITE_SPACE(" "); NUM("4.0")]
Tokens without whitespace: [NUM("1.0"); PLUS("+"); NUM("2.0"); MINUS("-"); NUM("3.0"); PLUS("+"); NUM("4.0")]
(((1.0 + 2.0) - 3.0) + 4.0)
=======================================
Start of optional partial-credit tests.
=======================================
Input: (1.0 + 2.0 - 3.0
Tokens: [LPAREN("("); NUM("1.0"); WHITE_SPACE(" "); PLUS("+"); WHITE_SPACE(" "); NUM("2.0"); WHITE_SPACE(" "); MINUS("-"); WHITE_SPACE(" "); NUM("3.0")]
Tokens without whitespace: [LPAREN("("); NUM("1.0"); PLUS("+"); NUM("2.0"); MINUS("-"); NUM("3.0")]
Parsing Failed
Input: (1.0 +) 2.0 - 3.0
Tokens: [LPAREN("("); NUM("1.0"); WHITE_SPACE(" "); PLUS("+"); RPAREN(")"); WHITE_SPACE(" "); NUM("2.0"); WHITE_SPACE(" "); MINUS("-"); WHITE_SPACE(" "); NUM("3.0")]
Tokens without whitespace: [LPAREN("("); NUM("1.0"); PLUS("+"); RPAREN(")"); NUM("2.0"); MINUS("-"); NUM("3.0")]
Parsing Failed
```
