public abstract class ZAExpression<T> {
  private T value;
  private boolean evaluated = false;

  public T getValue() {
    if (evaluated) return value;

    value = evaluate();
    evaluated = true;
    return value;
  }

  abstract public String toString();

  abstract protected T evaluate();
}
