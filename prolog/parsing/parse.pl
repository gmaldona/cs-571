
%%%%%%%%%%%%%%%%%
% Your code here:
%%%%%%%%%%%%%%%%%

lines --> line, ";", lines.
lines --> line.
line --> num, ",", line. 
line --> num.
num --> digit, num.
num --> digit.

digit --> ['0'].
digit --> ['1'].
digit --> ['2'].
digit --> ['3'].
digit --> ['4'].
digit --> ['5'].
digit --> ['6'].
digit --> ['7'].
digit --> ['8'].
digit --> ['9']. 

parse(X) :- phrase(lines, X, _). 

% Example execution:
% ?- parse(['3', '2', ',', '0', ';', '1', ',', '5', '6', '7', ';', '2']).
% true.
% ?- parse(['3', '2', ',', '0', ';', '1', ',', '5', '6', '7', ';', '2' ',']).
% false.
% ?- parse(['3', '2', ',', ';', '0']).
% false.
