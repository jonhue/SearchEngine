public abstract class BinOp<T> extends Expression<T> {
  private Expression<T> left, right;

  protected BinOp(Expression<T> left, Expression<T> right) {
    this.left = left;
    this.right = right;
  }

  public String toString() {
    return "(" + left.toString() + " " + symbol() + " " + right.toString() + ")";
  }

  protected T evaluate() {
    return func(left.getValue(), right.getValue());
  }

  abstract protected T func(T left, T right);

  abstract protected String symbol();
}
