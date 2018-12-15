public final class ZAGTOp<T, U> extends ZABinOp<T, U> {
  public ZAGTOp(ZAExpression<T> left, ZAExpression<T> right) {
    super(left, right);
  }

  protected U func(T left, T right) {
    return (U) (Object) ((Integer) left > (Integer) right);
  }

  protected String symbol() {
    return ">";
  }
}
