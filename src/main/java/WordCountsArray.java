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
    if (wordCountsArray.wordCounts == null || wordCounts == null || wordCountsArray.size() != size()) return false;

    boolean isEqual = true;
    for (int i = 0; i < size() && isEqual; ++i)
      if (!wordCountsArray.wordCounts[i].equals(wordCounts[i]))
        isEqual = false;

    return isEqual;
  }

  public String getWord(int i) {
    if (i >= size()) return null;

    return wordCounts[i].getWord();
  }

  public int getCount(int i) {
    if (i >= size()) return -1;

    return wordCounts[i].getCount();
  }

  public int getIndexOfWord(String word) {
    int result = -1;

    for (int i = 0; i < size() && result == -1; ++i)
      if (wordCounts[i].getWord().equals(word))
        result = i;

    return result;
  }

  public void setCount(int i, int count) throws IllegalArgumentException {
    if (i >= size())
      throw new IllegalArgumentException("Invalid index.");

    wordCounts[i].setCount(count);
  }

  public void add(String word, int count) {
    if (word == null || word.equals("") || count < 0) return;

    int i = getIndexOfWord(word.toLowerCase());
    if (i == -1) {
      if (size() == wordCounts.length) extend(wordCounts.length + 1);
      wordCounts[size()] = new WordCount(word.toLowerCase(), count);
    } else {
      wordCounts[i].incrementCount(count);
    }
  }

  public int size() {
    for (int i = 0; i < wordCounts.length; ++i)
      if (wordCounts[i] == null) return i;

    return wordCounts.length;
  }

  public void sort() {
    doBucketSort();
  }

  public double computeSimilarity(WordCountsArray wordCountsArray) {
    return scalarProduct(wordCountsArray) / Math.sqrt(scalarProduct(this) * wordCountsArray.scalarProduct(wordCountsArray));
  }

  private boolean wordsEqual(WordCountsArray wordCountsArray) {
    if (wordCountsArray == null) return false;
    if (wordCountsArray.wordCounts == null && wordCounts == null) return true;
    if (wordCountsArray.wordCounts == null || wordCounts == null || wordCountsArray.size() != size()) return false;

    boolean isEqual = true;
    for (int i = 0; i < size() && isEqual; ++i)
      if (!wordCountsArray.getWord(i).equals(getWord(i)))
        isEqual = false;

    return isEqual;
  }

  private double scalarProduct(WordCountsArray wordCountsArray) {
    double result = 0;
    if (!wordsEqual(wordCountsArray)) return result;

    for (int i = 0; i < size(); ++i)
      result += getCount(i) * wordCountsArray.getCount(i);

    return result;
  }

  private void extend(int n) {
    WordCount[] newWordCounts = new WordCount[wordCounts.length + n];
    java.lang.System.arraycopy(wordCounts, 0, newWordCounts, 0, wordCounts.length);
    wordCounts = newWordCounts;
  }

  private void doSelectionSort() {
    for (int i = 0; i < size() - 1; i++) {
      int min = i;
      for (int j = i + 1; j < size(); j++)
        if (getWord(j).compareTo(getWord(min)) < 0)
          min = j;

      if (i != min) {
        WordCount tmp = wordCounts[i];
        wordCounts[i] = wordCounts[min];
        wordCounts[min] = tmp;
      }
    }
  }

  private void doBucketSort() {
    // First sort by the last character & Last sort by the first
    for (int index = bucketSortGetMaxWordLength(wordCounts) - 1; index >= 0; --index) {
      // One bucket per lowercase letter in the ASCII table + 1 for strings shorter than the current character index
      WordCountsArray[] buckets = new WordCountsArray[27];

      for (int wordCountIndex = 0; wordCountIndex < size(); ++wordCountIndex)
        bucketSortAddToBucket(buckets, bucketSortBucketIndex(wordCounts[wordCountIndex].getWord(), index), wordCounts[wordCountIndex]);

      bucketSortConcatenateBuckets(buckets);
    }
  }

  private static int bucketSortGetMaxWordLength(WordCount[] wordCounts) {
    int maxWordLength = 0;
    for (int i = 0; i < wordCounts.length && wordCounts[i] != null; ++i)
      if (maxWordLength < wordCounts[i].getWord().length())
        maxWordLength = wordCounts[i].getWord().length();

    return maxWordLength;
  }

  private void bucketSortConcatenateBuckets(WordCountsArray[] buckets) {
    int i = 0;
    for (WordCountsArray bucket : buckets)
      if (bucket != null)
        for (int wordCountIndex = 0; wordCountIndex < bucket.size(); ++wordCountIndex)
          wordCounts[i++] = bucket.wordCounts[wordCountIndex];
  }

  private static int bucketSortBucketIndex(String word, int index) {
    if (word.length() > index)
      return word.charAt(index) - 96; // 'a' -> 1, 'z' -> 26
    else
      return 0;
  }

  private static void bucketSortAddToBucket(WordCountsArray[] buckets, int bucketIndex, WordCount wordCount) {
    if (buckets[bucketIndex] == null) {
      buckets[bucketIndex] = new WordCountsArray(1);
      buckets[bucketIndex].wordCounts[0] = wordCount;
    } else {
      if (buckets[bucketIndex].size() == buckets[bucketIndex].wordCounts.length) buckets[bucketIndex].extend(buckets[bucketIndex].wordCounts.length);
      buckets[bucketIndex].wordCounts[buckets[bucketIndex].size()] = wordCount;
    }
  }
}
