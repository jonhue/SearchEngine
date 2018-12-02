import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordCountTest {
  private WordCount wordCount;

  @Before
  public void setUp() throws IllegalArgumentException {
    wordCount = new WordCount("Word", 1);
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<WordCount word=Word count=1>";
    final String actual = wordCount.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsEquality() {
    final boolean actual = wordCount.equals(new WordCount("Word", 1));

    assertTrue(actual);
  }

  @Test
  public void equals_detectsInequality() {
    final boolean actual = wordCount.equals(new WordCount("Another word", 2));

    assertFalse(actual);
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    final boolean actual = wordCount.equals(null);

    assertFalse(actual);
  }

  @Test
  public void getWord() {
    final String expected = "Word";
    final String actual = wordCount.getWord();

    assertEquals(expected, actual);
  }

  @Test
  public void getCount() {
    final int expected = 1;
    final int actual = wordCount.getCount();

    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setCount_throwsExceptionWhenLowerThan0() throws IllegalArgumentException {
    wordCount.setCount(-1);
  }

  @Test
  public void setCount_acceptsPositiveValues() {
    final int expected = 2;
    wordCount.setCount(expected);
    final int actual = wordCount.getCount();

    assertEquals(expected, actual);
  }

  @Test
  public void getWeight() {
    final double expected = 0.0;
    final double actual = wordCount.getWeight();

    assertEquals(expected, actual, 0.0);
  }

  @Test
  public void setWeight() {
    final double expected = 0.5;
    wordCount.setWeight(expected);
    final double actual = wordCount.getWeight();

    assertEquals(expected, actual, 0.0);
  }

  @Test
  public void getNormalizedWeight() {
    final double expected = 0.0;
    final double actual = wordCount.getNormalizedWeight();

    assertEquals(expected, actual, 0.0);
  }

  @Test
  public void setNormalizedWeight() {
    final double expected = 0.5;
    wordCount.setNormalizedWeight(expected);
    final double actual = wordCount.getNormalizedWeight();

    assertEquals(expected, actual, 0.0);
  }

  @Test
  public void incrementCount_withoutParameter() {
    final int expected = 2;
    final int actual = wordCount.incrementCount();

    assertEquals(expected, actual);
  }

  @Test
  public void incrementCount_withParameter() {
    final int expected = 3;
    final int actual = wordCount.incrementCount(2);

    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void incrementCount_throwsExceptionWhenParameterNegative() throws IllegalArgumentException {
    wordCount.incrementCount(-1);
  }
}
