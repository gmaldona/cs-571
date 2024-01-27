CC=gcc -g -Wall -Wextra -Werror

default : dll

expr : dll.c dll.h
	$(CC) $^ -o $@

clean :
	rm -rf expr
