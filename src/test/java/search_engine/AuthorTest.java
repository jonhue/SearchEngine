package search_engine;

import org.testng.annotations.*;
import org.testng.Assert;

public class AuthorTest {
  private Author author;

  @BeforeMethod
  public void setUp() {
    Date date = new Date(2000, 2, 4);
    author = new Author("Jonas", "Hübotter", date, "Munich", "jonas.huebotter@tum.de");
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<Author firstName=Jonas lastName=Hübotter>";
    final String actual = author.toString();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getName() {
    final String expected = "Jonas Hübotter";
    final String actual = author.getName();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getContactInformation() {
    final String expected = "Jonas Hübotter\r\njonas.huebotter@tum.de\r\nMunich";
    final String actual = author.getContactInformation();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getAgeAt() {
    final Integer expected = 18;
    final Date today = new Date(2018, 10, 27);
    final Integer actual = author.getAgeAt(today);

    Assert.assertEquals(actual, expected);
  }
}
