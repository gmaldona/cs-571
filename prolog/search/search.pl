%%%%%%%%%%%%%%%%%%%%%%
% Your code goes here:
%%%%%%%%%%%%%%%%%%%%%%

% Not member snippet for undergraduate Programming Languages
% https://github.com/gmaldona/Social-Distance-Maze/blob/main/main.pl
not_member(_, []) :- !.
not_member(key(X, _), [key(H, _)|T]) :-
   X \= H,
   not_member(X, T).

% https://stackoverflow.com/questions/11539203/how-do-i-append-lists-in-prolog
append([], X, [X]).
append([H|T], E, [H, W]) :- append(T, E, W).


initial_state(X) :- 
  X = state(Pos,[]),
  initial(Pos).

take_action(state(A,Key), move(A,B), state(B,Key)) :-
  door(A,B).

take_action(state(A,Key), move(A,B), state(B,Key)) :-
  door(B,A).

take_action(state(A, Key), move(A,B), state(B, Keys)) :-
  door(A,B), key(B, C), not_member(key(B, C), Key), append(Key, key(B, C), Keys) .

take_action(state(A, Key), move(A,B), state(B, Keys)) :-
  door(B, A), key(B, C), not_member(key(B, C), Key), append(Key, key(B, C), Keys) .

take_action(state(A,Keys), move(A,B), state(B,_)) :-
  locked_door(A, B, C), member(key(_, C), Keys).

take_action(X, move(A,B), state(B,_)) :-
  X = state(A,Keys),
  locked_door(B,A, C), member(key(_, C), Keys).

final_state(state(Pos, _)) :- treasure(Pos).

take_steps(S0, [Action], S1) :- take_action(S0, Action, S1).

take_steps(S0, [Action | Rest], S1) :- 
  take_action(S0, Action, SNew),
  take_steps(SNew, Rest, S1).

search(Actions) :-
  initial_state(S0),
  length(Actions, _),
  take_steps(S0, Actions, SFinal),
  final_state(SFinal), !.
