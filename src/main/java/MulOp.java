public final class MulOp extends BinOp<Integer> {
  public MulOp(Expression<Integer> left, Expression<Integer> right) {
    super(left, right);
  }

  protected Integer func(Integer left, Integer right) {
    return left * right;
  }

  protected String symbol() {
    return "*";
  }
}
