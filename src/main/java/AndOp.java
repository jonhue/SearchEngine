public final class AndOp extends BinOp<Boolean> {
  public AndOp(Expression<Boolean> left, Expression<Boolean> right) {
    super(left, right);
  }

  protected Boolean func(Boolean left, Boolean right) {
    return left && right;
  }

  protected String symbol() {
    return "∧";
  }
}
