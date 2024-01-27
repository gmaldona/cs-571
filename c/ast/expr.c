#include "expr.h"
#include "stdlib.h"
#include "stdio.h"


struct Expr *mk_plus(struct Expr *e1, struct Expr *e2) {
    struct Expr *ret = malloc(sizeof(struct Expr));
    ret->type = PLUS;
    ret->subexprs.e1 = e1;
    ret->subexprs.e2 = e2;

    return ret;
}

struct Expr *mk_minus(struct Expr *e1, struct Expr *e2) {
    struct Expr *ret = malloc(sizeof(struct Expr));
    ret->type = MINUS;
    ret->subexprs.e1 = e1;
    ret->subexprs.e2 = e2;

    return ret;
}

struct Expr *mk_times(struct Expr *e1, struct Expr *e2) {
    struct Expr *ret =  malloc(sizeof(struct Expr));
    ret->type = TIMES;
    ret->subexprs.e1 = e1;
    ret->subexprs.e2 = e2;

    return ret;
}

struct Expr *mk_div(struct Expr *e1, struct Expr *e2) {
    struct Expr *ret = malloc(sizeof(struct Expr));
    ret->type = DIV;
    ret->subexprs.e1 = e1;
    ret->subexprs.e2 = e2;

    return ret;
}

struct Expr *mk_float(float f) {
    struct Expr *ret = malloc(sizeof(struct Expr));
    ret->type = FLOAT;
    ret->literal = f;

    return ret;
}

/* This function should create the expr (3 + (2 * 5))
 * using the above constructors.
 */
struct Expr *mk_expr1() {
    // TODO: Your code goes here
}

/* This function should create the expr ((1 / 2) + (3 / 8))
 * using the above constructors.
 */
struct Expr *mk_expr2() {
    // TODO: Your code goes here
}

/* This function should create the expr ((1 / 2) - (4 / (2 + 3)))
 * using the above constructors.
 */
struct Expr *mk_expr3() {
    // TODO: Your code goes here
}


/*
 * This function should free all memory associated 
 * with the given AST.
*/
void free_expr(struct Expr *e) {
    // TODO: Your code goes here
}

/*
 * This function should evaluate the given AST and
 * return the floating-point result.
*/
float eval(struct Expr *e) {
    // TODO: Your code goes here
}




