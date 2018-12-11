public final class SubOp extends UnOp<Integer> {
  public SubOp(Expression<Integer> arg) {
    super(arg);
  }

  protected Integer func(Integer arg) {
    return -arg;
  }

  protected String symbol() {
    return "-";
  }
}
