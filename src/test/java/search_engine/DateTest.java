package search_engine;

import org.testng.annotations.*;
import org.testng.Assert;

public class DateTest {
  private Date date;
  private Date reference;

  @BeforeMethod
  public void setUp() {
    date = new Date(1971, 2, 3);
    reference = new Date(1970, 1, 1);
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<Date year=1971 month=2 day=3>";
    final String actual = date.toString();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void daysSince() {
    final Integer expected = 392;
    final Integer actual = Date.daysSince(date, reference);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void yearsSince() {
    final Integer expected = 1;
    final Integer actual = Date.yearsSince(date, reference);

    Assert.assertEquals(actual, expected);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void setMonth_throwsExceptionWhenLowerThanOne() throws IllegalArgumentException {
    date.setMonth(0);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void setMonth_throwsExceptionWhenHigherThanThirteen() throws IllegalArgumentException {
    date.setMonth(13);
  }

  @Test
  public void setMonth_acceptsValuesBetweenOneAndTwelve() throws IllegalArgumentException {
    final Integer expected = 6;
    date.setMonth(6);
    final Integer actual = date.getMonth();

    Assert.assertEquals(actual, expected);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void setDay_throwsExceptionWhenLowerThanOne() throws IllegalArgumentException {
    date.setDay(0);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void setDay_throwsExceptionWhenHigherThanThirtytwo() throws IllegalArgumentException {
    date.setDay(32);
  }

  @Test
  public void setDay_acceptsValuesBetweenOneAndThirtyone() throws IllegalArgumentException {
    final Integer expected = 15;
    date.setDay(15);
    final Integer actual = date.getDay();

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getAgeInDaysAt() {
    final Integer expected = 392;
    final Integer actual = reference.getAgeInDaysAt(date);

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void getAgeInYearsAt() {
    final Integer expected = 1;
    final Integer actual = reference.getAgeInYearsAt(date);

    Assert.assertEquals(actual, expected);
  }
}
