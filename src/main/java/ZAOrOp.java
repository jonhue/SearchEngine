public final class ZAOrOp<T, U> extends ZABinOp<Boolean, Boolean> {
  public ZAOrOp(ZAExpression<Boolean> left, ZAExpression<Boolean> right) {
    super(left, right);
  }

  protected Boolean func(Boolean left, Boolean right) {
    return left || right;
  }

  protected String symbol() {
    return "∨";
  }
}
