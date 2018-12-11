public final class OrOp extends BinOp<Boolean> {
  public OrOp(Expression<Boolean> left, Expression<Boolean> right) {
    super(left, right);
  }

  protected Boolean func(Boolean left, Boolean right) {
    return left || right;
  }

  protected String symbol() {
    return "∨";
  }
}
