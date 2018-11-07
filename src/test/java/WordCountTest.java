import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.lang.IllegalArgumentException;

public class WordCountTest {
  private WordCount wordCount;

  @Before
  public void setUp() throws IllegalArgumentException {
    wordCount = new WordCount("Word", 1);
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<WordCount content=Word count=1>";
    final String actual = wordCount.toString();

    assertEquals(actual, expected);
  }

  @Test
  public void getContent() {
    final String expected = "Word";
    final String actual = wordCount.getContent();

    assertEquals(actual, expected);
  }

  @Test
  public void getCount() {
    final int expected = 1;
    final int actual = wordCount.getCount();

    assertEquals(actual, expected);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setCount_throwsExceptionWhenLowerThanZero() throws IllegalArgumentException {
    wordCount.setCount(-1);
  }

  @Test
  public void setCount_acceptsValuesAboveZero() {
    final int expected = 2;
    wordCount.setCount(expected);
    final int actual = wordCount.getCount();

    assertEquals(actual, expected);
  }

  @Test
  public void incrementCount_withoutParameter() {
    final int expected = 2;
    final int actual = wordCount.incrementCount();

    assertEquals(actual, expected);
  }

  @Test
  public void incrementCount_withParameter() {
    final int expected = 3;
    final int actual = wordCount.incrementCount(2);

    assertEquals(actual, expected);
  }

  @Test
  public void incrementCount_withNegativeParameter() {
    final int expected = 1;
    wordCount.setCount(2);
    final int actual = wordCount.incrementCount(-1);

    assertEquals(actual, expected);
  }
}

/*
* Kommentar:
*
* Ziel dieser Klasse ist es das Public Interface der Klasse WordCount zu testen.
*
* setUp() liefert die nötigen Instanzen, die für die übrigen Unit Tests notwendig sind und testet den Konstruktor.
* toString_hasCorrectFormat() überprüft den Rückgabewert der Methode toString().
* getDocument(), getContent(), und getCount() überprüfen jeweils die Getter für die Attribute document, content, und count.
* setCount() ist der einzige Setter, der Teil des Public Interfaces ist. Da nicht alle Integers, sondern nur Werte größer als 1,
*   für das Attribut count zulässig sind, muss zwischen zwei Fällen unterschieden werden:
*
*   1. setCount_throwsExceptionWhenLowerThanZero() überprüft die Exception, falls der Aktualparameter für count 0 oder kleiner ist.
*   2. setCount_acceptsValuesAboveZero() stellt sicher, dass Werte größer 0, als Attributwert gesetzt werden.
*
* Auch bei der Methode incrementCount() muss zwischen zwei Fällen unterschieden werden. Diesmal jedoch, da die Methode sowohl mit als auch ohne Parameter definiert ist:
*
*   1. incrementCount_withoutParameter() testet die Methode one Übergabe eines Parameters.
 *   2. incrementCount_withParameter() testet die Methode mit Übergabe eines Parameters.
 *   2. incrementCount_withNegativeParameter() testet die Methode mit Übergabe eines negativen Parameters.
* */
