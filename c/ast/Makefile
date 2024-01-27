CC=gcc -g -Wall -Wextra -Werror

all : expr # clion requirement.

default : expr

expr : expr.c main.c print-expr.c
	$(CC) $^ -o $@

clean :
	rm -rf expr
