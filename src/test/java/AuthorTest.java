import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class AuthorTest {
  private Author author;
  private Date birthday;

  @Before
  public void setUp() {
    birthday = new Date(2000, 2, 4);
    author = new Author("Jonas", "Hübotter", birthday, "Munich", "jonas.huebotter@tum.de");
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<Author firstName=Jonas lastName=Hübotter>";
    final String actual = author.toString();

    assertEquals(actual, expected);
  }

  @Test
  public void getFirstName() {
    final String expected = "Jonas";
    final String actual = author.getFirstName();

    assertEquals(actual, expected);
  }

  @Test
  public void setFirstName() {
    final String expected = "Foo";
    author.setFirstName(expected);
    final String actual = author.getFirstName();

    assertEquals(actual, expected);
  }

  @Test
  public void getLastName() {
    final String expected = "Hübotter";
    final String actual = author.getLastName();

    assertEquals(actual, expected);
  }

  @Test
  public void setLastName() {
    final String expected = "Bar";
    author.setLastName(expected);
    final String actual = author.getLastName();

    assertEquals(actual, expected);
  }

  @Test
  public void getBirthday() {
    final Date actual = author.getBirthday();

    assertEquals(actual, birthday);
  }

  @Test
  public void setBirthday() {
    final Date expected = new Date(1970, 1, 1);
    author.setBirthday(expected);
    final Date actual = author.getBirthday();

    assertEquals(actual, expected);
  }

  @Test
  public void getResidence() {
    final String expected = "Munich";
    final String actual = author.getResidence();

    assertEquals(actual, expected);
  }

  @Test
  public void setResidence() {
    final String expected = "Springfield";
    author.setResidence(expected);
    final String actual = author.getResidence();

    assertEquals(actual, expected);
  }

  @Test
  public void getEmail() {
    final String expected = "jonas.huebotter@tum.de";
    final String actual = author.getEmail();

    assertEquals(actual, expected);
  }

  @Test
  public void setEmail() {
    final String expected = "me@jonhue.me";
    author.setEmail(expected);
    final String actual = author.getEmail();

    assertEquals(actual, expected);
  }

  @Test
  public void getName() {
    final String expected = "Jonas Hübotter";
    final String actual = author.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void getContactInformation() {
    final String expected = "Jonas Hübotter\r\njonas.huebotter@tum.de\r\nMunich";
    final String actual = author.getContactInformation();

    assertEquals(actual, expected);
  }

  @Test
  public void getAgeAt() {
    final int expected = 18;
    final Date today = new Date(2018, 10, 27);
    final int actual = author.getAgeAt(today);

    assertEquals(actual, expected);
  }
}
