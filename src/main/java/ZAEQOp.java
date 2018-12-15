public final class ZAEQOp<T, U> extends ZABinOp<T, U> {
  public ZAEQOp(ZAExpression<T> left, ZAExpression<T> right) {
    super(left, right);
  }

  protected U func(T left, T right) {
    return (U) (Object) ((Integer) left == (Integer) right);
  }

  protected String symbol() {
    return "==";
  }
}
