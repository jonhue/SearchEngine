import org.testng.annotations.*;
import org.testng.Assert;

import java.lang.IllegalArgumentException;

public class ReviewTest {
  private Review review;

  @BeforeMethod
  public void setUp() throws IllegalArgumentException {
    Date date = new Date(2017, 11, 9);
    Author author = new Author("Jonas", "Hübotter", date, "Munich", "jonas.huebotter@tum.de");
    Document document = new Document("Title", "en", "Summary", date, author, "Content ...");
    review = new Review(author, document, "en", date, 5, "Content ...");
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<Review rating=5 document=<Document title=Title lang=en author=<Author firstName=Jonas lastName=Hübotter>> author=<Author firstName=Jonas lastName=Hübotter>>";
    final String actual = review.toString();

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
    final Integer expected = 6;
    review.setRating(6);
    final Integer actual = review.getRating();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getAgeAt() {
    final Integer expected = 348;
    final Date today = new Date(2018, 10, 27);
    final Integer actual = review.getAgeAt(today);

    Assert.assertEquals(actual, expected);
  }
}
