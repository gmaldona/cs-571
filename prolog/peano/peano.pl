
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Peano arithmetic formalism
%%%%%%%%%%%%%%%%%%%%%%%%%%%%

is_number(0).
is_number(s(X)) :- is_number(X).

add(0, N, N) :- is_number(N).
add(s(N), M, s(Y)) :- add(N, M, Y).

mul(0, N, 0) :- is_number(N).
mul(s(N), M, Y) :-
  mul(N, M, YY),
  add(M, YY, Y).

%%%%%%%%%%%%%%%%%%%%%%
% Your code goes here:
%%%%%%%%%%%%%%%%%%%%%%

div(X, Y, Z, R) :- ???

% Example execution:
% swipl peano.pl
% ?- div(s(s(s(0))), s(s(0)), Z, R).
% Z = s(0),
% R = s(0).
% ?- div(s(s(s(s(0)))), s(s(0)), Z, R).
% Z = s(s(0)),
% R = 0.