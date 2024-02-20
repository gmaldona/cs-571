package src;

abstract class Expr {
    /*
     *  Part 1: implement the eval function for all
     *  non-abstract subclasses of Expr
     */
    abstract float eval();

    /*
     * Part 2: Implement the "equals" function for all 
     * non-abstract subclasses of Expr, which
     * checks "structural equality" between the "this"
     * expression and the input expression "other".
     * Structural equality means comparing two
     * abstract syntax trees to determine if they
     * represent the same syntactic structure. It
     * requires you to traverse the tree and determine
     * if a) all floating point values at the leaves
     * are the same, b) all operations in the mniddle
     * of the tree are the same, and c) the operations
     * are arranged in the same manner between the two
     * trees. Tests for this behavior are available in
     * "Main.java".
     * 
     * For this part, you may not use downcasting,
     * "instanceof", or any other Java tools to access
     * the runtime type of a class such as the
     * reflection library. Use the visitor pattern 
     * instead! Note that implementing the visitor
     * pattern will require you to extend the abstract
     * base class Expr with additional methods.
     */
    abstract boolean equals(Expr other);
}

abstract class BinaryExpr extends Expr {
    private Expr e1;
    private Expr e2;

    protected BinaryExpr(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Expr getE1() {
        return e1;
    }

    public Expr getE2() {
        return e2;
    }
}

class PlusExpr extends BinaryExpr {
    public PlusExpr(Expr e1, Expr e2) {
        super(e1, e2);
    }
}

class MinusExpr extends BinaryExpr {
    public MinusExpr(Expr e1, Expr e2) {
        super(e1, e2);
    }
}

class TimesExpr extends BinaryExpr {
    public TimesExpr(Expr e1, Expr e2) {
        super(e1, e2);
    }
}

class DivExpr extends BinaryExpr {
    public DivExpr(Expr e1, Expr e2) {
        super(e1, e2);
    }
}

class FloatExpr extends Expr {
    private float literal;

    public FloatExpr(float f) {
        this.literal = f;
    }
}