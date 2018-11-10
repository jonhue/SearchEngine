import java.lang.IllegalArgumentException;

public class WordCount {
  private String word;
  private int count;

  public WordCount(String word, int count) {
    setWord(word);
    setCount(count);
  }

  public String toString() {
    return "<WordCount word=" + word + " count=" + count + ">";
  }

  public String getWord() {
    return word;
  }

  private void setWord(String word) {
    this.word = word;
  }

  public int getCount() {
    return count;
  }

  /* count must be 1 or higher; otherwise throws IllegalArgumentException */
  public void setCount(int count) throws IllegalArgumentException {
    if (count < 0)
      throw new IllegalArgumentException("Count must not be smaller than 0.");

    this.count = count;
  }

  public int incrementCount() {
    int newCount = getCount() + 1;
    setCount(newCount);

    return newCount;
  }

  public int incrementCount(int n) {
    int newCount = getCount() + n;
    setCount(newCount);

    return newCount;
  }
}
