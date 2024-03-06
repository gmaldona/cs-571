package src;


interface Visitor {

   <T extends BinaryExpr> boolean visit(PlusExpr expr1, T expr2);
   <T extends BinaryExpr> boolean visit(MinusExpr expr1, T expr2);
   <T extends BinaryExpr> boolean visit(TimesExpr expr1, T expr2);
   <T extends BinaryExpr> boolean visit(DivExpr expr1, T expr2);
   <T extends FloatExpr> boolean visit(FloatExpr expr1, T expr2);
}

class VisitorImpl implements Visitor {

   public <T extends BinaryExpr> boolean visit(PlusExpr expr1, T expr2) {
      boolean lhs = expr1.getE1().accept(this) && expr2.getE1().accept(this);
      boolean rhs = expr1.getE2().accept(this) && expr2.getE2().accept(this);
      return lhs && rhs;
   }

   public <T extends BinaryExpr> boolean visit(MinusExpr expr1, T expr2) {
      boolean lhs = expr1.getE1().accept(this) && expr2.getE1().accept(this);
      boolean rhs = expr1.getE2().accept(this) && expr2.getE2().accept(this);
      return lhs && rhs;
   }

   public <T extends BinaryExpr> boolean visit(TimesExpr expr1, T expr2) {
      boolean lhs = expr1.getE1().accept(this) && expr2.getE1().accept(this);
      boolean rhs = expr1.getE2().accept(this) && expr2.getE2().accept(this);
      return lhs && rhs;
   }

   public <T extends BinaryExpr> boolean visit(DivExpr expr1, T expr2) {
      boolean lhs = expr1.getE1().accept(this) && expr2.getE1().accept(this);
      boolean rhs = expr1.getE2().accept(this) && expr2.getE2().accept(this);
      return lhs && rhs;
   }

   public <T extends FloatExpr> boolean visit(FloatExpr expr1, T expr2) {
      return expr1.getLiteral() == expr2.getLiteral();
   }

}

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

   abstract <T> boolean accept(Visitor v);
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

   @Override
   float eval() {
      return getE1().eval() + getE2().eval();
   }

   @Override
   <T> boolean accept(Visitor v) {
      return false;
   }

   <T extends BinaryExpr> boolean accept(Visitor v, T other) {
      return v.visit(this, other);
   }

   boolean equals(DivExpr other) {
      Visitor v = new VisitorImpl();
      return other.accept(v, this);
   }
}

class MinusExpr extends BinaryExpr {

   public MinusExpr(Expr e1, Expr e2) {
      super(e1, e2);
   }

   @Override
   float eval() {
      return getE1().eval() - getE2().eval();
   }

   @Override
   <T> boolean accept(Visitor v) {
      return false;
   }

   <T extends BinaryExpr> boolean accept(Visitor v, T other) {
      return v.visit(this, other);
   }

   boolean equals(DivExpr other) {
      Visitor v = new VisitorImpl();
      return other.accept(v, this);
   }
}

class TimesExpr extends BinaryExpr {

   public TimesExpr(Expr e1, Expr e2) {
      super(e1, e2);
   }

   @Override
   float eval() {
      return getE1().eval() * getE2().eval();
   }

   @Override
   <T> boolean accept(Visitor v) {
      return false;
   }

   <T extends BinaryExpr> boolean accept(Visitor v, T other) {
      return v.visit(this, other);
   }

   boolean equals(DivExpr other) {
      Visitor v = new VisitorImpl();
      return other.accept(v, this);
   }

}

class DivExpr extends BinaryExpr {

   public DivExpr(Expr e1, Expr e2) {
      super(e1, e2);
   }

   @Override
   float eval() {
      return getE1().eval() / getE2().eval();
   }

   @Override
   <T> boolean accept(Visitor v) {
      return false;
   }

   <T extends BinaryExpr> boolean accept(Visitor v, T other) {
      return v.visit(this, other);
   }

   boolean equals(DivExpr other) {
      Visitor v = new VisitorImpl();
      return other.accept(v, this);
   }

}

class FloatExpr extends Expr {

   private float literal;

   public FloatExpr(float f) {
      this.literal = f;
   }

   @Override
   float eval() {
      return literal;
   }

   @Override
   <T> boolean accept(Visitor v) {
      return false;
   }

   <T extends FloatExpr> boolean accept(Visitor v, T other) {
      return v.visit(this, other);
   }

   float getLiteral() {
      return literal;
   }

   boolean equals(FloatExpr other) {
      Visitor v = new VisitorImpl();
      return other.accept(v, this);
   }

}