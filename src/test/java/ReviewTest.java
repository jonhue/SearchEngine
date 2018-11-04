import org.testng.annotations.*;
import org.testng.Assert;

import java.lang.IllegalArgumentException;

public class ReviewTest {
  private Review review;
  private Author author;
  private Document document;
  private Date releaseDate;

  @BeforeMethod
  public void setUp() throws IllegalArgumentException {
    releaseDate = new Date(2017, 11, 9);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    document = new Document("Title", "en", "Summary", releaseDate, author, "Content ...");
    review = new Review(author, document, "en", releaseDate, 5, "Content ...");
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<Review rating=5 document=<Document title=Title lang=en author=<Author firstName=Jonas lastName=Hübotter>> author=<Author firstName=Jonas lastName=Hübotter>>";
    final String actual = review.toString();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getContent() {
    final String expected = "Content ...";
    final String actual = review.getContent();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void setContent() {
    final String expected = "More ...";
    review.setContent(expected);
    final String actual = review.getContent();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getAuthor() {
    final Author actual = review.getAuthor();

    Assert.assertEquals(actual, author);
  }

  @Test
  public void setAuthor() {
    final Author expected = new Author("Foo", "Bar", releaseDate, "Springfield", "me@jonhue.me");
    review.setAuthor(expected);
    final Author actual = review.getAuthor();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getDocument() {
    final Document actual = review.getDocument();

    Assert.assertEquals(actual, document);
  }

  @Test
  public void setDocument() {
    final Document expected = new Document("Another Title", "de", "Another summary", releaseDate, author, "More ...");
    review.setDocument(expected);
    final Document actual = review.getDocument();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getLang() {
    final String expected = "en";
    final String actual = review.getLang();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void setLang() {
    final String expected = "de";
    review.setLang(expected);
    final String actual = review.getLang();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getReleaseDate() {
    final Date actual = review.getReleaseDate();

    Assert.assertEquals(actual, releaseDate);
  }

  @Test
  public void setReleaseDate() {
    final Date expected = new Date(1970, 1, 1);
    review.setReleaseDate(expected);
    final Date actual = review.getReleaseDate();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getRating() {
    final int expected = 5;
    final int actual = review.getRating();

    Assert.assertEquals(actual, expected);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void setRating_throwsExceptionWhenLowerThanZero() throws IllegalArgumentException {
    review.setRating(-1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void setRating_throwsExceptionWhenHigherThanTen() throws IllegalArgumentException {
    review.setRating(11);
  }

  @Test
  public void setRating_acceptsValuesBetweenZeroAndTen() throws IllegalArgumentException {
    final int expected = 6;
    review.setRating(6);
    final int actual = review.getRating();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getAgeAt() {
    final int expected = 348;
    final Date today = new Date(2018, 10, 27);
    final int actual = review.getAgeAt(today);

    Assert.assertEquals(actual, expected);
  }
}
