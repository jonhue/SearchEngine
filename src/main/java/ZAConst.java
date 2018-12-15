public final class ZAConst<T> extends ZAExpression<T> {
  private T n;

  public ZAConst(T n) {
    this.n = n;
  }

  public String toString() {
    return String.valueOf(n);
  }

  protected T evaluate() {
    return n;
  }
}
