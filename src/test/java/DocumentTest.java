import org.testng.annotations.*;
import org.testng.Assert;

public class DocumentTest {
  private Document document;
  private Date releaseDate;
  private Author author;

  @BeforeMethod
  public void setUp() {
    releaseDate = new Date(2017, 11, 9);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    document = new Document("Title", "en", "Summary", releaseDate, author, "Content ...");
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<Document title=Title lang=en author=<Author firstName=Jonas lastName=Hübotter>>";
    final String actual = document.toString();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getTitle() {
    final String expected = "Title";
    final String actual = document.getTitle();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void setTitle() {
    final String expected = "Another Title";
    document.setTitle(expected);
    final String actual = document.getTitle();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getContent() {
    final String expected = "Content ...";
    final String actual = document.getContent();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void setContent() {
    final String expected = "More ...";
    document.setContent(expected);
    final String actual = document.getContent();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getLang() {
    final String expected = "en";
    final String actual = document.getLang();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void setLang() {
    final String expected = "de";
    document.setLang(expected);
    final String actual = document.getLang();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getSummary() {
    final String expected = "Summary";
    final String actual = document.getSummary();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void setSummary() {
    final String expected = "Another summary";
    document.setSummary(expected);
    final String actual = document.getSummary();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getReleaseDate() {
    final Date actual = document.getReleaseDate();

    Assert.assertEquals(actual, releaseDate);
  }

  @Test
  public void setReleaseDate() {
    final Date expected = new Date(1970, 1, 1);
    document.setReleaseDate(expected);
    final Date actual = document.getReleaseDate();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getAuthor() {
    final Author actual = document.getAuthor();

    Assert.assertEquals(actual, author);
  }

  @Test
  public void setAuthor() {
    final Author expected = new Author("Foo", "Bar", releaseDate, "Springfield", "me@jonhue.me");
    document.setAuthor(expected);
    final Author actual = document.getAuthor();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getAgeAt() {
    final int expected = 348;
    final Date today = new Date(2018, 10, 27);
    final int actual = document.getAgeAt(today);

    Assert.assertEquals(actual, expected);
  }
}
