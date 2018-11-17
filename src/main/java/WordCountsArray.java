import java.lang.IllegalArgumentException;

public class WordCountsArray {
  private WordCount[] wordCounts;

  public WordCountsArray(int wordCountsLength) {
    wordCounts = new WordCount[wordCountsLength];
  }

  public String toString() {
    return "<WordCountsArray size=" + size() + ">";
  }

  public boolean equals(WordCountsArray wordCountsArray) {
    if (wordCountsArray == null) return false;
    if (wordCountsArray.wordCounts == null && wordCounts == null) return true;
    if (wordCountsArray.wordCounts == null || wordCounts == null || wordCountsArray.wordCounts.length != wordCounts.length) return false;

    boolean isEqual = true;
    for (int i = 0; i < wordCounts.length; ++i) {
      if (wordCountsArray.wordCounts[i] == null && wordCounts[i] == null) continue;
      if (wordCountsArray.wordCounts[i] == null || !wordCountsArray.wordCounts[i].equals(wordCounts[i]))
        isEqual = false;
    }

    return isEqual;
  }

  public String getWord(int i) {
    if (i >= size()) return "";

    return wordCounts[i].getWord();
  }

  public int getCount(int i) {
    if (i >= size()) return -1;

    return wordCounts[i].getCount();
  }

  public void setCount(int i, int count) throws IllegalArgumentException {
    if (i >= size())
      throw new IllegalArgumentException("Invalid index.");

    wordCounts[i].setCount(count);
  }

  public void add(String word, int count) {
    if (word == null || word.equals("")) return;

    if (size() == wordCounts.length) extend(1);
    wordCounts[size()] = new WordCount(word, count);
  }

  public int size() {
    for (int i = 0; i < wordCounts.length; ++i)
      if (wordCounts[i] == null) return i;

    return wordCounts.length;
  }

  private void extend(int n) {
    WordCount[] newWordCounts = new WordCount[wordCounts.length + n];
    java.lang.System.arraycopy(wordCounts, 0, newWordCounts, 0, wordCounts.length);
    wordCounts = newWordCounts;
  }
}
