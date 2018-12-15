public abstract class ZAUnOp<T> extends ZAExpression<T> {
  private ZAExpression<T> arg;

  protected ZAUnOp(ZAExpression<T> arg) {
    this.arg = arg;
  }

  public String toString() {
    return "(" + symbol() + arg.toString() + ")";
  }

  protected T evaluate() {
    return func(arg.getValue());
  }

  abstract protected T func(T arg);

  abstract protected String symbol();
}
