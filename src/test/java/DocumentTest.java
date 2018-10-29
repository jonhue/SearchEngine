import org.testng.annotations.*;
import org.testng.Assert;

public class DocumentTest {
  private Document document;

  @BeforeMethod
  public void setUp() {
    Date date = new Date(2017, 11, 9);
    Author author = new Author("Jonas", "Hübotter", date, "Munich", "jonas.huebotter@tum.de");
    document = new Document("Title", "en", "Summary", date, author, "Content ...");
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<Document title=Title lang=en author=<Author firstName=Jonas lastName=Hübotter>>";
    final String actual = document.toString();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getAgeAt() {
    final Integer expected = 348;
    final Date today = new Date(2018, 10, 27);
    final Integer actual = document.getAgeAt(today);

    Assert.assertEquals(actual, expected);
  }
}
