public final class ZANegOp<T> extends ZAUnOp<T> {
  private String symbol;

  public ZANegOp(ZAExpression<T> arg) throws IllegalArgumentException {
    super(arg);
    setSymbol(arg.getValue());
  }

  protected T func(T arg) throws IllegalArgumentException {
    if (arg instanceof Boolean)
      return (T) (Object) (!(Boolean) arg);
    else if (arg instanceof Integer)
      return (T) (Object) (-(Integer) arg);
    else
      throw new IllegalArgumentException("The argument must either be an integer or a boolean");
  }

  protected String symbol() {
    return symbol;
  }

  private void setSymbol(T arg) throws IllegalArgumentException {
    if (arg instanceof Boolean)
      symbol = "¬";
    else if (arg instanceof Integer)
      symbol = "-";
    else
      throw new IllegalArgumentException("The argument must either be an integer or a boolean");
  }
}
