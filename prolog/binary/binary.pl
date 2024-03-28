% empty
% tree(Key, VaL, left, right)

% insert(key, val, in_tree, out_tree)
insert(K, V, empty, tree(K, V, empty, empty)).
insert(K, V, tree(TK, TV, Left, Right), tree(TK, TV, New_left, Right)) :-
  K < TK, insert(K, V, Left, New_left).
insert(K, V, tree(TK, TV, Left, Right), tree(TK, TV, Left, New_right)) :-
  K > TK, insert(K, V, Right, New_right).
insert(K, V, tree(K, _, Left, Right), tree(K, V, Left, Right)).

% lookup
% lookup(K, T, V)
% lookup(_, empty, no_val).
lookup(K, tree(K, V, _, _), V).
lookup(K, tree(TK, _, Left, _), V) :- K < TK, lookup(K, Left, V).
lookup(K, tree(TK, _, _, Right), V) :- K > TK, lookup(K, Right, V).

ltree(
    tree(3, c, 
      tree(2, b,
        tree(1,a,empty,empty),
        empty
      ),
      empty
    )
).

ltreeR(
    tree(2,b,
      tree(1,a,empty,empty),
      tree(3,c,empty,empty)
    )
).

rtree(
    tree(1,a,
      empty,
      tree(2,b,
        empty,
        tree(3,c,
          empty,
          empty
        )
      )
    )
).


%%%%%%%%%%%%%%%%%
% Your code here:
%%%%%%%%%%%%%%%%%

%rotateRight(T1, T2) :- ???

%rotateLeft(T1, T2) :- ???


% Example execution:
% ?- ltree(T1), rotateRight(T1, T2).
% T1 = tree(3, c, tree(2, b, tree(1, a, empty, empty), empty), empty),
% T2 = tree(2, b, tree(1, a, empty, empty), tree(3, c, empty, empty)).
% ?- rtree(T1), rotateLeft(T1, T2).
% T1 = tree(1, a, empty, tree(2, b, empty, tree(3, c, empty, empty))),
% T2 = tree(2, b, tree(1, a, empty, empty), tree(3, c, empty, empty)).





