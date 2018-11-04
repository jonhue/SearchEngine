import org.testng.annotations.*;
import org.testng.Assert;

import java.lang.IllegalArgumentException;

public class WordCountTest {
  private Document document;
  private WordCount wordCount;

  @BeforeMethod
  public void setUp() throws IllegalArgumentException {
    Date date = new Date(2017, 11, 9);
    Author author = new Author("Jonas", "Hübotter", date, "Munich", "jonas.huebotter@tum.de");
    document = new Document("Title", "en", "Summary", date, author, "Content ...");
    wordCount = new WordCount(document, "Word", 1);
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<WordCount content=Word count=1 document=<Document title=Title lang=en author=<Author firstName=Jonas lastName=Hübotter>>>";
    final String actual = wordCount.toString();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getDocument() {
    final Document actual = wordCount.getDocument();

    Assert.assertEquals(actual, document);
  }

  @Test
  public void getContent() {
    final String expected = "Word";
    final String actual = wordCount.getContent();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getCount() {
    final int expected = 1;
    final int actual = wordCount.getCount();

    Assert.assertEquals(actual, expected);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void setCount_throwsExceptionWhenLowerThanZero() throws IllegalArgumentException {
    wordCount.setCount(-1);
  }

  @Test
  public void setCount_acceptsValuesAboveZero() {
    final int expected = 2;
    wordCount.setCount(expected);
    final int actual = wordCount.getCount();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void incrementCount_withoutParameter() {
    final int expected = 2;
    final int actual = wordCount.incrementCount();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void incrementCount_withParameter() {
    final int expected = 3;
    final int actual = wordCount.incrementCount(2);

    Assert.assertEquals(actual, expected);
  }
}
