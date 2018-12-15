public final class ZAAddOp<T, U> extends ZABinOp<Integer, Integer> {
  public ZAAddOp(ZAExpression<Integer> left, ZAExpression<Integer> right) {
    super(left, right);
  }

  protected Integer func(Integer left, Integer right) {
    return left + right;
  }

  protected String symbol() {
    return "+";
  }
}
