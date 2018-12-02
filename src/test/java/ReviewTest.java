import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    document = new Document("Title", "en", "Summary", releaseDate, author, "content");
    review = new Review(author, document, "en", releaseDate, 5, "content");
  }

  @Test
  public void toString_hasCorrectFormat() {
    assertEquals("<Review rating=5 document=<Document title=Title lang=en author=<Author firstName=Jonas lastName=Hübotter>> author=<Author firstName=Jonas lastName=Hübotter>>", review.toString());
  }

  @Test
  public void equals_detectsEquality() {
    releaseDate = new Date(9, 11, 2017);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    document = new Document("Title", "en", "Summary", releaseDate, author, "content");
    Review anotherReview = new Review(author, document, "en", releaseDate, 5, "content");

    assertTrue(review.equals(anotherReview));
  }

  @Test
  public void equals_detectsInequality() {
    Review anotherReview = new Review(author, document, "de", releaseDate, 10, "more");

    assertFalse(review.equals(anotherReview));
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    assertFalse(review.equals(null));
  }

  @Test
  public void getContent() {
    assertEquals("content", review.getContent());
  }

  @Test
  public void setContent() {
    final String content = "more";
    review.setContent(content);

    assertEquals(content, review.getContent());
  }

  @Test
  public void getAuthor() {
    assertEquals(author, review.getAuthor());
  }

  @Test
  public void setAuthor() {
    final Author author = new Author("Foo", "Bar", releaseDate, "Springfield", "me@jonhue.me");
    review.setAuthor(author);

    assertEquals(author, review.getAuthor());
  }

  @Test
  public void getDocument() {
    assertEquals(document, review.getDocument());
  }

  @Test
  public void setDocument() {
    final Document document = new Document("Another Title", "de", "Another summary", releaseDate, author, "more");
    review.setDocument(document);

    assertEquals(document, review.getDocument());
  }

  @Test
  public void getLang() {
    assertEquals("en", review.getLang());
  }

  @Test
  public void setLang() {
    final String lang = "de";
    review.setLang(lang);

    assertEquals(lang, review.getLang());
  }

  @Test
  public void getReleaseDate() {
    assertEquals(releaseDate, review.getReleaseDate());
  }

  @Test
  public void setReleaseDate() {
    final Date releaseDate = new Date(1, 1, 1970);
    review.setReleaseDate(releaseDate);

    assertEquals(releaseDate, review.getReleaseDate());
  }

  @Test
  public void getRating() {
    assertEquals(5, review.getRating());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setRating_throwsExceptionWhenLowerThan0() throws IllegalArgumentException {
    review.setRating(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setRating_throwsExceptionWhenHigherThan10() throws IllegalArgumentException {
    review.setRating(11);
  }

  @Test
  public void setRating_acceptsValuesBetween0And10() throws IllegalArgumentException {
    final int rating = 6;
    review.setRating(rating);

    assertEquals(rating, review.getRating());
  }

  @Test
  public void getAgeAt() {
    final Date today = new Date(27, 10, 2018);

    assertEquals(352, review.getAgeAt(today));
  }
}
