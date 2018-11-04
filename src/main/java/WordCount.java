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

  public void setCount(int count) {
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
