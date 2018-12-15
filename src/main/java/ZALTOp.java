public final class ZALTOp<T, U> extends ZABinOp<T, U> {
  public ZALTOp(ZAExpression<T> left, ZAExpression<T> right) {
    super(left, right);
  }

  protected U func(T left, T right) {
    return (U) (Object) ((Integer) left < (Integer) right);
  }

  protected String symbol() {
    return "<";
  }
}
