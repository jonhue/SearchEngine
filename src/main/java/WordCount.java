import java.lang.IllegalArgumentException;

public class WordCount {
  private Document document;
  private String content;
  private int count;

  public WordCount(Document document, String content, int count) {
    setDocument(document);
    setContent(content);
    setCount(count);
  }

  public String toString() {
    return "<WordCount content=" + content + " count=" + count + " document=" + document.toString() + ">";
  }

  public Document getDocument() {
    return document;
  }

  private void setDocument(Document document) {
    this.document = document;
  }

  public String getContent() {
    return content;
  }

  private void setContent(String content) {
    this.content = content;
  }

  public int getCount() {
    return count;
  }

  /* count must be 1 or higher; otherwise throws IllegalArgumentException */
  public void setCount(int count) throws IllegalArgumentException {
    if (count < 1) {
      throw new IllegalArgumentException("Count must not be smaller than 1.");
    }

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
