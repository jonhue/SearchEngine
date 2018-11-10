import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.lang.IllegalArgumentException;

public class ReviewTest {
  private Review review;
  private Author author;
  private Document document;
  private Date releaseDate;

  @Before
  public void setUp() throws IllegalArgumentException {
    releaseDate = new Date(9, 11, 2017);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    document = new Document("Title", "en", "Summary", releaseDate, author, "Content ...");
    review = new Review(author, document, "en", releaseDate, 5, "Content ...");
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<Review rating=5 document=<Document title=Title lang=en author=<Author firstName=Jonas lastName=Hübotter>> author=<Author firstName=Jonas lastName=Hübotter>>";
    final String actual = review.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void getContent() {
    final String expected = "Content ...";
    final String actual = review.getContent();

    assertEquals(expected, actual);
  }

  @Test
  public void setContent() {
    final String expected = "More ...";
    review.setContent(expected);
    final String actual = review.getContent();

    assertEquals(expected, actual);
  }

  @Test
  public void getAuthor() {
    final Author actual = review.getAuthor();

    assertEquals(author, actual);
  }

  @Test
  public void setAuthor() {
    final Author expected = new Author("Foo", "Bar", releaseDate, "Springfield", "me@jonhue.me");
    review.setAuthor(expected);
    final Author actual = review.getAuthor();

    assertEquals(expected, actual);
  }

  @Test
  public void getDocument() {
    final Document actual = review.getDocument();

    assertEquals(document, actual);
  }

  @Test
  public void setDocument() {
    final Document expected = new Document("Another Title", "de", "Another summary", releaseDate, author, "More ...");
    review.setDocument(expected);
    final Document actual = review.getDocument();

    assertEquals(expected, actual);
  }

  @Test
  public void getLang() {
    final String expected = "en";
    final String actual = review.getLang();

    assertEquals(expected, actual);
  }

  @Test
  public void setLang() {
    final String expected = "de";
    review.setLang(expected);
    final String actual = review.getLang();

    assertEquals(expected, actual);
  }

  @Test
  public void getReleaseDate() {
    final Date actual = review.getReleaseDate();

    assertEquals(releaseDate, actual);
  }

  @Test
  public void setReleaseDate() {
    final Date expected = new Date(1, 1, 1970);
    review.setReleaseDate(expected);
    final Date actual = review.getReleaseDate();

    assertEquals(expected, actual);
  }

  @Test
  public void getRating() {
    final int expected = 5;
    final int actual = review.getRating();

    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setRating_throwsExceptionWhenLowerThanZero() throws IllegalArgumentException {
    review.setRating(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setRating_throwsExceptionWhenHigherThanTen() throws IllegalArgumentException {
    review.setRating(11);
  }

  @Test
  public void setRating_acceptsValuesBetweenZeroAndTen() throws IllegalArgumentException {
    final int expected = 6;
    review.setRating(6);
    final int actual = review.getRating();

    assertEquals(expected, actual);
  }

  @Test
  public void getAgeAt() {
    final int expected = 348;
    final Date today = new Date(27, 10, 2018);
    final int actual = review.getAgeAt(today);

    assertEquals(expected, actual);
  }
}
