public final class AddOp extends BinOp<Integer> {
  public AddOp(Expression<Integer> left, Expression<Integer> right) {
    super(left, right);
  }

  protected Integer func(Integer left, Integer right) {
    return left + right;
  }

  protected String symbol() {
    return "+";
  }
}
