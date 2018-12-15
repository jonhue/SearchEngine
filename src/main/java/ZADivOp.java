public final class ZADivOp<T, U> extends ZABinOp<Integer, Integer> {
  public ZADivOp(ZAExpression<Integer> left, ZAExpression<Integer> right) {
    super(left, right);
  }

  protected Integer func(Integer left, Integer right) {
    return left / right;
  }

  protected String symbol() {
    return "/";
  }
}
