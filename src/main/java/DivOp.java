public final class DivOp extends BinOp<Integer> {
  public DivOp(Expression<Integer> left, Expression<Integer> right) {
    super(left, right);
  }

  protected Integer func(Integer left, Integer right) {
    return left / right;
  }

  protected String symbol() {
    return "/";
  }
}
