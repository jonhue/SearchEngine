public final class ZASubOp<T, U> extends ZABinOp<Integer, Integer> {
  public ZASubOp(ZAExpression<Integer> left, ZAExpression<Integer> right) {
    super(left, right);
  }

  protected Integer func(Integer left, Integer right) {
    return left - right;
  }

  protected String symbol() {
    return "-";
  }
}
