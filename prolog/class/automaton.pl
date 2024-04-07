%%%%%%%%%%%%%%%%%%%%
% Automaton Encoding
%%%%%%%%%%%%%%%%%%%%

initial(0).

transition(0, a, 1).
transition(1, b, 2).
transition(2, b, 2).
transition(1, c, 3).
transition(3, c, 3).
transition(2, c, 3).
transition(3, b, 2).

accepts(2).
accepts(3).

%%%%%%%%%%%%%%%%%
% Your code here:
%%%%%%%%%%%%%%%%%

do_transitions(S0, [Trans], SNew) :- transition(S0, Trans, SNew).
do_transitions(S0, [Trans | Tail], SNew) :-
  transition(S0, Trans, S1),
  do_transitions(S1, Tail, SNew).

accepts_seq(Seq) :-
  initial(S0),
  do_transitions(S0, Seq, SFinal),
  accepts(SFinal).

accepts_six(Seq) :-
  length(Seq, 6),
  accepts_seq(Seq).

accepts_six_list(X) :-
  findall(Seq, accepts_six(Seq), X).


