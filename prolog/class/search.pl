%%%%%%%%%%%%%%%
% Problem Setup
%%%%%%%%%%%%%%%

initial(0).

door(0,1).
door(0,2).
door(1,2).
door(1,3).

locked_door(2,4).

key(3).
treasure(4).

%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Your program starts here:
%%%%%%%%%%%%%%%%%%%%%%%%%%%

initial_state(X) :- 
  X = state(Pos,noKey),
  initial(Pos).

take_action(state(A,Key), move(A,B), state(B,Key)) :-
  door(A,B).

take_action(state(A,Key), move(A,B), state(B,Key)) :-
  door(B,A).

take_action(state(A,noKey), move(A,B), state(B,hasKey)) :-
  door(A,B), key(B).

take_action(state(A,noKey), move(A,B), state(B,hasKey)) :-
  door(B,A), key(B).

take_action(state(A,hasKey), move(A,B), state(B,hasKey)) :-
  locked_door(A,B).

take_action(X, move(A,B), state(B,hasKey)) :-
  X = state(A,hasKey),
  locked_door(B,A).

final_state(state(Pos, _)) :- treasure(Pos).

take_steps(S0, [Action], S1) :- take_action(S0, Action, S1).

take_steps(S0, [Action | Rest], S1) :- 
  take_action(S0, Action, SNew),
  take_steps(SNew, Rest, S1).

find_path(Actions) :-
  initial_state(S0),
  length(Actions, _),
  take_steps(S0, Actions, SFinal),
  final_state(SFinal), !.

