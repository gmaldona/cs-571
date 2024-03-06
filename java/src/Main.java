package src;
public class Main {
    public static void main(String[] args) {
        Expr expr1 = mkExpr1();
        System.out.println("Result of expr1: " + expr1.eval());

        Expr expr2 = mkExpr2();
        System.out.println("Result of expr2: " + expr2.eval());

        Expr expr3 = mkExpr3();
        System.out.println("Result of expr3: " + expr3.eval());

        Expr expr4 = mkExpr3();

        Expr expr5 = mkExpr5();

        Expr expr6 = mkExpr6();

        System.out.println("expr1 == expr3 (should output false): " + expr1.equals(expr3));
        System.out.println("expr3 == expr3 (should output true): " + expr3.equals(expr3));
        System.out.println("expr3 == expr4 (should output true): " + expr3.equals(expr4));
        System.out.println("expr5 == expr5 (should output true): " + expr5.equals(expr5));
        System.out.println("expr5 == expr6 (should output false): " + expr5.equals(expr6));
        
    }
    public static Expr mkExpr1() {
        return new PlusExpr(new FloatExpr(3), new TimesExpr(new FloatExpr(2), new FloatExpr(5)));
    }

    public static Expr mkExpr2() {
        return new PlusExpr(new DivExpr(new FloatExpr(1), new FloatExpr(2)), new DivExpr(new FloatExpr(3), new FloatExpr(8)));
    }

    public static Expr mkExpr3() {
        return new MinusExpr(new DivExpr(new FloatExpr(1), new FloatExpr(2)), new DivExpr(new FloatExpr(4), new PlusExpr(new FloatExpr(2), new FloatExpr(3))));
    }

    public static Expr mkExpr5() {
        return new DivExpr(new FloatExpr(4), new FloatExpr(4));
    }

    public static Expr mkExpr6() {
        return new DivExpr(new FloatExpr(4), new FloatExpr (4));
    }

}