public class ATest {
  public static void main(String[] args) {
    Expression expr1 = new MulOp<Integer>(new Const<Integer>(3), new AddOp<Integer>(new Const<Integer>(1), new Const<Integer>(2)));
    System.out.println(expr1.toString() + " = " + expr1.evaluate());

    Expression expr2 = new AndOp<Boolean>(new Const<Boolean>(true),new OrOp<Boolean>(new Const<Boolean>(false), new Const<Boolean>(true)));
    System.out.println(expr2.toString() + " = " + expr2.evaluate());

    Expression expr3 = new AddOp<Integer>(new NegOp<Integer>(new Const<Integer>(8)), new AddOp<Integer>(new MulOp<Integer>(new Const<Integer>(6), new Const<Integer>(2)), new MulOp<Integer>(new SubOp<Integer>(new Const<Integer>(5), new Const<Integer>(3)), new Const<Integer>(3))));
    System.out.println(expr3.toString() + " = " + expr3.evaluate());

    Expression expr4 = new OrOp<Boolean>(new Const<Boolean>(false), new NegOp<Boolean>(new AndOp<Boolean>(new Const<Boolean>(true), new Const<Boolean>(false))));
    System.out.println(expr4.toString() + " = " + expr4.evaluate());

    Expression expr5 = new AddOp<Integer>(new Const<Integer>(5), new SubOp<Integer>(new Const<Integer>(2), new AddOp<Integer>(new Const<Integer>(9), new MulOp<Integer>(new Const<Integer>(4), new SubOp<Integer>(new Const<Integer>(8), new AddOp<Integer>(new Const<Integer>(5), new Const<Integer>(1)))))));
    System.out.println(expr5.toString() + " = " + expr5.evaluate());
  }
}
