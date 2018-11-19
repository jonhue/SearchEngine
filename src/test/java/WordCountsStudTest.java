import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class WordCountsStudTest {
  private WordCountsArray wordCountsArray;

  @Before
  public void setUp() {
    wordCountsArray = new WordCountsArray(2);
    wordCountsArray.add("Hello", 1);
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<WordCountsArray size=1>";
    final String actual = wordCountsArray.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsEquality() {
    final boolean expected = true;
    WordCountsArray newWordCountsArray = new WordCountsArray(2);
    newWordCountsArray.add("Hello", 1);
    final boolean actual = wordCountsArray.equals(newWordCountsArray);

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsInequality() {
    final boolean expected = false;
    WordCountsArray newWordCountsArray = new WordCountsArray(1);
    newWordCountsArray.add("World", 1);
    final boolean actual = wordCountsArray.equals(newWordCountsArray);

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    final boolean expected = false;
    final boolean actual = wordCountsArray.equals(null);

    assertEquals(expected, actual);
  }

  @Test
  public void getWord_invalidIndex() {
    final String expected = null;
    final String actual = wordCountsArray.getWord(1);

    assertEquals(expected, actual);
  }

  @Test
  public void getWord_validIndex() {
    final String expected = "hello";
    final String actual = wordCountsArray.getWord(0);

    assertEquals(expected, actual);
  }

  @Test
  public void getCount_invalidIndex() {
    final int expected = -1;
    final int actual = wordCountsArray.getCount(1);

    assertEquals(expected, actual);
  }

  @Test
  public void getCount_validIndex() {
    final int expected = 1;
    final int actual = wordCountsArray.getCount(0);

    assertEquals(expected, actual);
  }

  @Test
  public void getIndexOfWord_doesntContainWord() {
    final int expected = -1;
    final int actual = wordCountsArray.getIndexOfWord("world");

    assertEquals(expected, actual);
  }

  @Test
  public void getIndexOfWord_containsWord() {
    final int expected = 1;
    wordCountsArray.add("World", 1);
    final int actual = wordCountsArray.getIndexOfWord("world");

    assertEquals(expected, actual);
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
    final int expected = 2;
    wordCountsArray.setCount(0, 2);
    final int actual = wordCountsArray.getCount(0);

    assertEquals(expected, actual);
  }

  @Test
  public void add_doesNotAddElementIfWordIsEmpty() {
    final int expected = 1;
    wordCountsArray.add("", 1);
    final int actual = wordCountsArray.size();

    assertEquals(expected, actual);
  }

  @Test
  public void add_doesNotAddElementIfWordIsNull() {
    final int expected = 1;
    wordCountsArray.add(null, 1);
    final int actual = wordCountsArray.size();

    assertEquals(expected, actual);
  }

  @Test
  public void add_addsElement() {
    final int expected = 3;
    wordCountsArray.add("World", 1);
    wordCountsArray.add("Another word", 1);
    final int actual = wordCountsArray.size();

    assertEquals(expected, actual);
  }

  @Test
  public void size_whenArrayNotFull() {
    final int expected = 1;
    final int actual = wordCountsArray.size();

    assertEquals(expected, actual);
  }

  @Test
  public void size_whenArrayIsFull() {
    final int expected = 2;
    wordCountsArray.add("World", 1);
    final int actual = wordCountsArray.size();

    assertEquals(expected, actual);
  }

  @Test
  public void sort() {
    final int expected = 2;
    wordCountsArray.add("World", 1);
    wordCountsArray.add("Another word", 1);
    wordCountsArray.sort();
    final int actual = wordCountsArray.getIndexOfWord("world");

    assertEquals(expected, actual);
  }

  @Test
  public void computeSimilarity() {
    final double expected = 0.894427191;
    wordCountsArray.add("World", 0);
    WordCountsArray anotherWordCountsArray = new WordCountsArray(2);
    anotherWordCountsArray.add("Hello", 2);
    anotherWordCountsArray.add("World", 1);
    final double actual = wordCountsArray.computeSimilarity(anotherWordCountsArray);

    assertEquals(expected, actual, 0.00000000001);
  }
}
