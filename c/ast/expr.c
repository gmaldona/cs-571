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
    struct Expr* var1 = mk_float(2);
    struct Expr* var2 = mk_float(5);
    struct Expr* res1 = mk_times(var1, var2);
    struct Expr* var3 = mk_float(3);
    struct Expr* res2 = mk_plus(var3, res1);

    return res2;
}

/* This function should create the expr ((1 / 2) + (3 / 8))
 * using the above constructors.
 */
struct Expr *mk_expr2() {
    struct Expr* var1 = mk_float(1);
    struct Expr* var2 = mk_float(2);
    struct Expr* res1 = mk_div(var1, var2);

   struct Expr* var3 = mk_float(3);
   struct Expr* var4 = mk_float(8);
   struct Expr* res2 = mk_div(var3, var4);

   return mk_plus(res1, res2);
}

/* This function should create the expr ((1 / 2) - (4 / (2 + 3)))
 * using the above constructors.
 */
struct Expr *mk_expr3() {
    struct Expr* var1 = mk_float(2);
    struct Expr* var2 = mk_float(3);
    struct Expr* res1 = mk_plus(var1, var2);
    struct Expr* var3 = mk_float(4);
    struct Expr* res2 = mk_div(var3, res1);

    struct Expr* var4 = mk_float(1);
    struct Expr* var5 = mk_float(2);
    struct Expr* res3 = mk_div(var4, var5);

    return mk_minus(res3, res2);
}


/*
 * This function should free all memory associated 
 * with the given AST.
*/
void free_expr(struct Expr *e) {
   // TODO: this needs to be recursive.
    switch (e->type) {
      case FLOAT: break;
      default: 
         free_expr(e->subexprs.e1);
         free_expr(e->subexprs.e2);
    }
   free(e);
}

/*
 * This function should evaluate the given AST and
 * return the floating-point result.
*/
float eval(struct Expr *e) {
   switch (e->type) {

      case PLUS:
         return eval(e->subexprs.e1) + eval(e->subexprs.e2);
      case MINUS:
         return eval(e->subexprs.e1) - eval(e->subexprs.e2);
      case TIMES:
         return eval(e->subexprs.e1) * eval(e->subexprs.e2);
      case DIV:
         return eval(e->subexprs.e1) / eval(e->subexprs.e2);;
      case FLOAT:
         return e->literal;
   }

   return 0;
}




