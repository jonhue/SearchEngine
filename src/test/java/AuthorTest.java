import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    assertEquals("<Author firstName=Jonas lastName=Hübotter>", author.toString());
  }

  @Test
  public void equals_detectsEquality() {
    Author anotherAuthor = new Author("Jonas", "Hübotter", birthday, "Munich", "jonas.huebotter@tum.de");

    assertTrue(author.equals(anotherAuthor));
  }

  @Test
  public void equals_detectsInequality() {
    Author anotherAuthor = new Author("Foo", "Bar", birthday, "Springfield", "me@jonhue.me");

    assertFalse(author.equals(anotherAuthor));
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    assertFalse(author.equals(null));
  }

  @Test
  public void getFirstName() {
    assertEquals("Jonas", author.getFirstName());
  }

  @Test
  public void setFirstName() {
    final String firstName = "Bar";
    author.setFirstName(firstName);

    assertEquals(firstName, author.getFirstName());
  }

  @Test
  public void getLastName() {
    assertEquals("Hübotter", author.getLastName());
  }

  @Test
  public void setLastName() {
    final String lastName = "Bar";
    author.setLastName(lastName);

    assertEquals(lastName, author.getLastName());
  }

  @Test
  public void getBirthday() {
    assertEquals(birthday, author.getBirthday());
  }

  @Test
  public void setBirthday() {
    final Date birthday = new Date(1, 1, 1970);
    author.setBirthday(birthday);

    assertEquals(birthday, author.getBirthday());
  }

  @Test
  public void getResidence() {
    assertEquals("Munich", author.getResidence());
  }

  @Test
  public void setResidence() {
    final String residence = "Springfield";
    author.setResidence(residence);

    assertEquals(residence, author.getResidence());
  }

  @Test
  public void getEmail() {
    assertEquals("jonas.huebotter@tum.de", author.getEmail());
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
  public void setEmail_throwsExceptionWhenShorterThan6() throws IllegalArgumentException {
    author.setEmail("a@b.c");
  }

  @Test
  public void setEmail_acceptsValuesContainingAtNotInFirstPlaceAndLongerThan5() {
    final String email = "me@jonhue.me";
    author.setEmail(email);

    assertEquals(email, author.getEmail());
  }

  @Test
  public void getName() {
    assertEquals("Jonas Hübotter", author.getName());
  }

  @Test
  public void getContactInformation() {
    assertEquals("Jonas Hübotter\r\njonas.huebotter@tum.de\r\nMunich", author.getContactInformation());
  }

  @Test
  public void getAgeAt() {
    final Date today = new Date(27, 10, 2018);

    assertEquals(18, author.getAgeAt(today));
  }
}
