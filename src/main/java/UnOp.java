public abstract class UnOp<T> extends Expression<T> {
  private Expression<T> arg;

  protected UnOp(Expression<T> arg) {
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
