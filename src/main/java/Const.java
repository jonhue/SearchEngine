public final class Const<T> extends Expression<T> {
  private T n;

  public Const(T n) {
    this.n = n;
  }

  public String toString() {
    return String.valueOf(n);
  }

  protected T evaluate() {
    return n;
  }
}
