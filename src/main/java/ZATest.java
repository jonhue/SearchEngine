public class ZATest {
  public static void main(String[] args) {
    ZAExpression expr1 = new ZAEQOp<Integer, Boolean>(new ZAMulOp<Integer, Integer>(new ZAConst<Integer>(3), new ZAAddOp<Integer, Integer>(new ZAConst<Integer>(1), new ZAConst<Integer>(2))), new ZAConst<Integer>(9));
    System.out.println(expr1.toString() + " = " + expr1.evaluate());

    ZAExpression expr2 = new ZAEQOp<Integer, Boolean>(new ZAMulOp<Integer, Integer>(new ZAConst<Integer>(3), new ZAAddOp<Integer, Integer>(new ZAConst<Integer>(1), new ZAConst<Integer>(2))), new ZAConst<Integer>(10));
    System.out.println(expr2.toString() + " = " + expr2.evaluate());

    ZAExpression expr3 = new ZAGTOp<Integer, Boolean>(new ZAAddOp<Integer, Integer>(new ZAConst<Integer>(3), new ZASubOp<Integer, Integer>(new ZAConst<Integer>(1), new ZAConst<Integer>(2))), new ZAConst<Integer>(10));
    System.out.println(expr3.toString() + " = " + expr3.evaluate());

    ZAExpression expr4 = new ZALTOp<Integer, Boolean>(new ZAMulOp<Integer, Integer>(new ZAConst<Integer>(3), new ZADivOp<Integer, Integer>(new ZAConst<Integer>(4), new ZAConst<Integer>(2))), new ZAConst<Integer>(10));
    System.out.println(expr4.toString() + " = " + expr4.evaluate());

    ZAExpression expr5 = new ZAEQOp<Integer, Boolean>(new ZASubOp<Integer, Integer>(new ZAConst<Integer>(3), new ZAAddOp<Integer, Integer>(new ZAConst<Integer>(1), new ZAConst<Integer>(2))), new ZAConst<Integer>(0));
    System.out.println(expr5.toString() + " = " + expr5.evaluate());
  }
}
