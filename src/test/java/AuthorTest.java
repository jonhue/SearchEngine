import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class AuthorTest {
  private Author author;
  private Date birthday;

  @Before
  public void setUp() {
    birthday = new Date(4, 2, 2000);
    author = new Author("Jonas", "Hübotter", birthday, "Munich", "jonas.huebotter@tum.de");
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<Author firstName=Jonas lastName=Hübotter>";
    final String actual = author.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void getFirstName() {
    final String expected = "Jonas";
    final String actual = author.getFirstName();

    assertEquals(expected, actual);
  }

  @Test
  public void setFirstName() {
    final String expected = "Foo";
    author.setFirstName(expected);
    final String actual = author.getFirstName();

    assertEquals(expected, actual);
  }

  @Test
  public void getLastName() {
    final String expected = "Hübotter";
    final String actual = author.getLastName();

    assertEquals(expected, actual);
  }

  @Test
  public void setLastName() {
    final String expected = "Bar";
    author.setLastName(expected);
    final String actual = author.getLastName();

    assertEquals(expected, actual);
  }

  @Test
  public void getBirthday() {
    final Date actual = author.getBirthday();

    assertEquals(birthday, actual);
  }

  @Test
  public void setBirthday() {
    final Date expected = new Date(1, 1, 1970);
    author.setBirthday(expected);
    final Date actual = author.getBirthday();

    assertEquals(expected, actual);
  }

  @Test
  public void getResidence() {
    final String expected = "Munich";
    final String actual = author.getResidence();

    assertEquals(expected, actual);
  }

  @Test
  public void setResidence() {
    final String expected = "Springfield";
    author.setResidence(expected);
    final String actual = author.getResidence();

    assertEquals(expected, actual);
  }

  @Test
  public void getEmail() {
    final String expected = "jonas.huebotter@tum.de";
    final String actual = author.getEmail();

    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setEmail_throwsExceptionWhenNotIncludingAt() throws IllegalArgumentException {
    author.setEmail("abc");
  }

  @Test(expected = IllegalArgumentException.class)
  public void setEmail_throwsExceptionWhenAtInFirstPlace() throws IllegalArgumentException {
    author.setEmail("@abc");
  }

  @Test(expected = IllegalArgumentException.class)
  public void setEmail_throwsExceptionWhenShorterThanSix() throws IllegalArgumentException {
    author.setEmail("a@b.c");
  }

  @Test
  public void setEmail_acceptsValuesContainingAtNotInFirstPlaceAndLongerThanFive() {
    final String expected = "me@jonhue.me";
    author.setEmail(expected);
    final String actual = author.getEmail();

    assertEquals(expected, actual);
  }

  @Test
  public void getName() {
    final String expected = "Jonas Hübotter";
    final String actual = author.getName();

    assertEquals(expected, actual);
  }

  @Test
  public void getContactInformation() {
    final String expected = "Jonas Hübotter\r\njonas.huebotter@tum.de\r\nMunich";
    final String actual = author.getContactInformation();

    assertEquals(expected, actual);
  }

  @Test
  public void getAgeAt() {
    final int expected = 18;
    final Date today = new Date(27, 10, 2018);
    final int actual = author.getAgeAt(today);

    assertEquals(expected, actual);
  }
}
