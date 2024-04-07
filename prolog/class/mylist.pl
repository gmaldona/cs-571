% myList(3, myList(4, myEmpty)) ==== [3,4]

isList(myEmpty).
isList(myList(_,T)) :- isList(T).

% The map predicate takes in a predicate Fn and maps it across
% the input myList X to produce the resulting myList Y.
% For example, consider the predicate fPred:
fPred(X, f(X)).
% This maps every input variable X to f(X) (e.g. fPred(1,f(1)) holds).
% Thus, the predicate
% map(fPred, myList(1, myList(2, myEmpty)), 
%   myList(f(1), myList(f(2), myEmpty)))
% Should hold

map(_, myEmpty, myEmpty).
map(Fn, myList(H1, T1), myList(H2, T2)) :-
  call(Fn, H1, H2), map(Fn, T1, T2). 


% The append(X, Y, Z) predicate should append the list X
% to the list Y to produce the list Z. For example the
% following should be true:
% append(myList(3, myList(4, myEmpty)), myList(4, myEmpty), 
%   myList(3, myList(4, myList(4, myEmpty))))

append(myEmpty, X, X).
append(myList(X, T), Y, myList(X, NewT)) :- append(T,Y,NewT).



