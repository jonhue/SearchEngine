import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import utils.Terminal;

public class DateTest {
  private Date date;
  private Date reference;

  @Before
  public void setUp() {
    date = new Date(1971, 2, 3);
    reference = new Date(1970, 1, 1);
  }

  @Test
  public void constructor_withoutParameters() {
    final Date expected = new Date(Terminal.TODAYS_YEAR, Terminal.TODAYS_MONTH, Terminal.TODAYS_DAY);
    final Date actual = new Date();

    assertTrue(actual.equals(expected));
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<Date year=1971 month=2 day=3>";
    final String actual = date.toString();

    assertEquals(actual, expected);
  }

  @Test
  public void equals_detectsEquality() {
    final boolean expected = true;
    final boolean actual = date.equals(new Date(1971, 2, 3));

    assertEquals(actual, expected);
  }

  @Test
  public void equals_detectsInequality() {
    final boolean expected = false;
    final boolean actual = date.equals(new Date());

    assertEquals(actual, expected);
  }

  @Test
  public void daysSince() {
    final int expected = 392;
    final int actual = Date.daysSince(date, reference);

    assertEquals(actual, expected);
  }

  @Test
  public void yearsSince() {
    final int expected = 1;
    final int actual = Date.yearsSince(date, reference);

    assertEquals(actual, expected);
  }

  @Test
  public void getYear() {
    final int expected = 1971;
    final int actual = date.getYear();

    assertEquals(actual, expected);
  }

  @Test
  public void setYear() {
    final int expected = 2018;
    date.setYear(expected);
    final int actual = date.getYear();

    assertEquals(actual, expected);
  }

  @Test
  public void getMonth() {
    final int expected = 2;
    final int actual = date.getMonth();

    assertEquals(actual, expected);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setMonth_throwsExceptionWhenLowerThanOne() throws IllegalArgumentException {
    date.setMonth(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setMonth_throwsExceptionWhenHigherThanThirteen() throws IllegalArgumentException {
    date.setMonth(13);
  }

  @Test
  public void setMonth_acceptsValuesBetweenOneAndTwelve() throws IllegalArgumentException {
    final int expected = 6;
    date.setMonth(6);
    final int actual = date.getMonth();

    assertEquals(actual, expected);
  }

  @Test
  public void getDay() {
    final int expected = 3;
    final int actual = date.getDay();

    assertEquals(actual, expected);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setDay_throwsExceptionWhenLowerThanOne() throws IllegalArgumentException {
    date.setDay(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setDay_throwsExceptionWhenHigherThanThirtytwo() throws IllegalArgumentException {
    date.setDay(32);
  }

  @Test
  public void setDay_acceptsValuesBetweenOneAndThirtyone() throws IllegalArgumentException {
    final int expected = 15;
    date.setDay(15);
    final int actual = date.getDay();

    assertEquals(actual, expected);
  }

  @Test
  public void getAgeInDaysAt() {
    final int expected = 392;
    final int actual = reference.getAgeInDaysAt(date);

    assertEquals(actual, expected);
  }

  @Test
  public void getAgeInYearsAt() {
    final int expected = 1;
    final int actual = reference.getAgeInYearsAt(date);

    assertEquals(actual, expected);
  }
}
