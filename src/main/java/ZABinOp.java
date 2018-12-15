public abstract class ZABinOp<T, U> extends ZAExpression<U> {
  private ZAExpression<T> left, right;

  protected ZABinOp(ZAExpression<T> left, ZAExpression<T> right) {
    this.left = left;
    this.right = right;
  }

  public String toString() {
    return "(" + left.toString() + " " + symbol() + " " + right.toString() + ")";
  }

  protected U evaluate() {
    return func(left.getValue(), right.getValue());
  }

  abstract protected U func(T left, T right);

  abstract protected String symbol();
}
