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
    assertEquals("<WordCount word=Word count=1>", wordCount.toString());
  }

  @Test
  public void equals_detectsEquality() {
    WordCount anotherWordCount = new WordCount("Word", 1);

    assertTrue(wordCount.equals(anotherWordCount));
  }

  @Test
  public void equals_detectsInequality() {
    WordCount anotherWordCount = new WordCount("Another word", 2);

    assertFalse(wordCount.equals(anotherWordCount));
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    assertFalse(wordCount.equals(null));
  }

  @Test
  public void getWord() {
    assertEquals("Word", wordCount.getWord());
  }

  @Test
  public void getCount() {
    assertEquals(1, wordCount.getCount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setCount_throwsExceptionWhenLowerThan0() throws IllegalArgumentException {
    wordCount.setCount(-1);
  }

  @Test
  public void setCount_acceptsPositiveValues() {
    final int count = 2;
    wordCount.setCount(count);

    assertEquals(count, wordCount.getCount());
  }

  @Test
  public void getWeight() {
    assertEquals(0.0, wordCount.getWeight(), 0.0);
  }

  @Test
  public void setWeight() {
    final double weight = 0.5;
    wordCount.setWeight(weight);

    assertEquals(weight, wordCount.getWeight(), 0.0);
  }

  @Test
  public void getNormalizedWeight() {
    assertEquals(0.0, wordCount.getNormalizedWeight(), 0.0);
  }

  @Test
  public void setNormalizedWeight() {
    final double normalizedWeight = 0.5;
    wordCount.setNormalizedWeight(normalizedWeight);

    assertEquals(normalizedWeight, wordCount.getNormalizedWeight(), 0.0);
  }

  @Test
  public void incrementCount_withoutParameter() {
    assertEquals(2, wordCount.incrementCount());
  }

  @Test
  public void incrementCount_withParameter() {
    assertEquals(3, wordCount.incrementCount(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void incrementCount_throwsExceptionWhenParameterNegative() throws IllegalArgumentException {
    wordCount.incrementCount(-1);
  }
}
