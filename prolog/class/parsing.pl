
term(X, A) :- factor(X, Y), addOp(Y, Z), term(Z, A).
term(X, Y) :- factor(X, Y).

factor(X, A) :- literal(X, Y), mulOp(Y, Z), factor(Z, A).
factor(X, Y) :- literal(X, Y).

literal(X, Y) :- num(X, Y).
literal(X, A) :- lparen(X, Y), term(Y, Z), rparen(Z, A).

lparen(['(' | T], T).
rparen([')' | T], T).

addOp(X, Y) :- plus(X, Y).
addOp(X, Y) :- minus(X, Y).

plus(['+' | T], T).
minus(['-' | T], T).

mulOp(X, Y) :- times(X, Y).
mulOp(X, Y) :- div(X, Y).

times(['*' | T], T).
div(['/' | T], T).

num([X | T], T) :-
  X = '0';
  X = '1';
  X = '2';
  X = '3';
  X = '4';
  X = '5';
  X = '6';
  X = '7';
  X = '8';
  X = '9'.

