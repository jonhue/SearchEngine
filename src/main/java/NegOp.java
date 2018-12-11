public final class NegOp extends UnOp<Boolean> {
  public NegOp(Expression<Boolean> arg) {
    super(arg);
  }

  protected Boolean func(Boolean arg) {
    return !arg;
  }

  protected String symbol() {
    return "¬";
  }
}
