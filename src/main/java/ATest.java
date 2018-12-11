public class ATest {
  public static void main(String[] args) {
    Expression expr1 = new MulOp(new Const<Integer>(3), new AddOp(new Const<Integer>(1), new Const<Integer>(2)));
    System.out.println(expr1.toString() + " = " + expr1.evaluate());

    Expression expr2 = new AndOp(new Const<Boolean>(true),new OrOp(new Const<Boolean>(false), new Const<Boolean>(true)));
    System.out.println(expr2.toString() + " = " + expr2.evaluate());

    Expression expr3 = new AddOp(new SubOp(new Const<Integer>(8)), new AddOp(new MulOp(new Const<Integer>(6), new Const<Integer>(2)), new MulOp(new AddOp(new Const<Integer>(5), new SubOp(new Const<Integer>(3))), new Const<Integer>(3))));
    System.out.println(expr3.toString() + " = " + expr3.evaluate());

    Expression expr4 = new OrOp(new Const<Boolean>(false), new NegOp(new AndOp(new Const<Boolean>(true), new Const<Boolean>(false))));
    System.out.println(expr4.toString() + " = " + expr4.evaluate());

    Expression expr5 = new AddOp(new Const<Integer>(5), new AddOp(new SubOp(new Const<Integer>(2)), new AddOp(new Const<Integer>(9), new MulOp(new Const<Integer>(4), new AddOp(new Const<Integer>(8), new SubOp(new AddOp(new Const<Integer>(5), new Const<Integer>(1))))))));
    System.out.println(expr5.toString() + " = " + expr5.evaluate());
  }
}
