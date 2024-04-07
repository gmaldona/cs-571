% DCG operator -->.

% lines --> line, semi, lines.
% lines --> line.
% line --> num, comma, line. 
% line --> num.
% num --> digit.
% num --> digit, num.

% semi  --> [';'].
% comma --> [','].
% digit --> ['0'].
% digit --> ['1'].
% digit --> ['2'].
% digit --> ['3'].
% digit --> ['4'].
% digit --> ['5'].
% digit --> ['6'].
% digit --> ['7'].
% digit --> ['8'].
% digit --> ['9']. 

% parse(X) :- phrase(lines, X). 

%%%%%%%%%%%%%%%%%
% Your code here:
%%%%%%%%%%%%%%%%%

lines(X, A) :- line(X, Y), semi(Y, Z), lines(Z, A).
lines(X, Y) :- line(X, Y).

line(X, A) :- num(X, Y), comma(Y, Z), line(Z, A).
line(X, Y) :- num(X, Y).

num(X, Y) :- digit(X, Y).
num(X, A) :- digit(X, Y), num(Y, A).

semi([';' | T], T).
comma([',' | T], T).

digit([X | T], T) :-
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

parse(X) :- lines(X, []). 

% Example execution:
% ?- parse(['3', '2', ',', '0', ';', '1', ',', '5', '6', '7', ';', '2']).
% true.
% ?- parse(['3', '2', ',', '0', ';', '1', ',', '5', '6', '7', ';', '2', ',']).
% false.
% ?- parse(['3', '2', ',', ';', '0']).
% false.
