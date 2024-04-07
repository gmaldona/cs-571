%%%%%%%%%%%%%%%%%%%%%%
% Your code goes here:
%%%%%%%%%%%%%%%%%%%%%%

% https://stackoverflow.com/questions/11539203
% Appending to a list in Prolog

append([], X, X).
append([X | Y], Z, [X | W]) :- append(Y, Z, W).

move(Actions, [X | Y]) :- append().

search(Actions) :- 
   initial(S), 
   move(Actions, ).
