import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordCountsArrayTest {
  private WordCountsArray wordCountsArray;

  @Before
  public void setUp() {
    wordCountsArray = new WordCountsArray(2);
    wordCountsArray.add("hello", 1);
  }

  @Test
  public void toString_hasCorrectFormat() {
    assertEquals("<WordCountsArray size=1>", wordCountsArray.toString());
  }

  @Test
  public void equals_detectsEquality() {
    WordCountsArray anotherWordCountsArray = new WordCountsArray(2);
    anotherWordCountsArray.add("hello", 1);

    assertTrue(wordCountsArray.equals(anotherWordCountsArray));
  }

  @Test
  public void equals_detectsInequality() {
    WordCountsArray anotherWordCountsArray = new WordCountsArray(1);
    anotherWordCountsArray.add("world", 1);

    assertFalse(wordCountsArray.equals(anotherWordCountsArray));
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    assertFalse(wordCountsArray.equals(null));
  }

  @Test
  public void getWord_invalidIndex() {
    assertEquals("", wordCountsArray.getWord(1));
  }

  @Test
  public void getWord_validIndex() {
    assertEquals("hello", wordCountsArray.getWord(0));
  }

  @Test
  public void getCount_invalidIndex() {
    assertEquals(-1, wordCountsArray.getCount(1));
  }

  @Test
  public void getCount_validIndex() {
    assertEquals(1, wordCountsArray.getCount(0));
  }

  @Test
  public void getIndexOfWord_doesntContainWord() {
    assertEquals(-1, wordCountsArray.getIndexOfWord("world"));
  }

  @Test
  public void getIndexOfWord_containsWord() {
    wordCountsArray.add("world", 1);

    assertEquals(1, wordCountsArray.getIndexOfWord("world"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void setCount_invalidIndex() {
    wordCountsArray.setCount(1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setCount_invalidCount() {
    wordCountsArray.setCount(0, -1);
  }

  @Test
  public void setCount_validIndex() {
    final int count = 2;
    wordCountsArray.setCount(0, count);

    assertEquals(count, wordCountsArray.getCount(0));
  }

  @Test
  public void add_doesNotAddElementIfWordIsEmpty() {
    wordCountsArray.add("", 1);

    assertEquals(1, wordCountsArray.size());
  }

  @Test
  public void add_doesNotAddElementIfWordIsNull() {
    wordCountsArray.add(null, 1);

    assertEquals(1, wordCountsArray.size());
  }

  @Test
  public void add_addsElement() {
    wordCountsArray.add("world", 1);
    wordCountsArray.add("more", 1);

    assertEquals(3, wordCountsArray.size());
  }

  @Test
  public void size_whenArrayNotFull() {
    assertEquals(1, wordCountsArray.size());
  }

  @Test
  public void size_whenArrayIsFull() {
    wordCountsArray.add("world", 1);

    assertEquals(2, wordCountsArray.size());
  }

  @Test
  public void sort() {
    wordCountsArray.add("world", 1);
    wordCountsArray.add("more", 1);
    wordCountsArray.sort();

    assertEquals(2, wordCountsArray.getIndexOfWord("world"));
  }

  @Test
  public void computeSimilarity_whenDocumentCollectionIsNotGiven() {
    wordCountsArray.add("world", 0);
    WordCountsArray anotherWordCountsArray = new WordCountsArray(2);
    anotherWordCountsArray.add("hello", 2);
    anotherWordCountsArray.add("world", 1);

    assertEquals(0.894427191, wordCountsArray.computeSimilarity(anotherWordCountsArray), 0.000000001);
  }

  @Test
  public void computeSimilarity_whenDocumentCollectionIsGiven() {
    DocumentCollection documentCollection = new DocumentCollection();
    Date date = new Date();
    Author author = new Author("Foo", "Bar", date, "Springfield", "me@jonhue.me");
    Document document1 = new Document("Title", "en", "Summary", date, author, "hello world");
    Document document2 = new Document("Another Title", "de", "Another summary", date, author, "some more content about this world");
    Document document3 = new Document("Hello Title", "en", "hello", date, author, "hello hello hello");
    Document query = new Document(null, null, null, null, null, "hello world");

    documentCollection.appendDocument(document1);
    documentCollection.appendDocument(document2);
    documentCollection.appendDocument(document3);

    assertEquals(0.624012790, query.getWordCounts().computeSimilarity(document2.getWordCounts(), documentCollection), 0.000000001);
  }
}
