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
    date = new Date(3, 2, 1971);
    reference = new Date(1, 1, 1970);
  }

  @Test
  public void constructor_withoutParameters() {
    final Date expected = new Date(Terminal.TODAYS_DAY, Terminal.TODAYS_MONTH, Terminal.TODAYS_YEAR);
    final Date actual = new Date();

    assertTrue(actual.equals(expected));
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<Date year=1971 month=2 day=3>";
    final String actual = date.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsEquality() {
    final boolean expected = true;
    final boolean actual = date.equals(new Date(3, 2, 1971));

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsInequality() {
    final boolean expected = false;
    final boolean actual = date.equals(new Date());

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    final boolean expected = false;
    final boolean actual = date.equals(null);

    assertEquals(expected, actual);
  }

  @Test
  public void getYear() {
    final int expected = 1971;
    final int actual = date.getYear();

    assertEquals(expected, actual);
  }

  @Test
  public void setYear() {
    final int expected = 2018;
    date.setYear(expected);
    final int actual = date.getYear();

    assertEquals(expected, actual);
  }

  @Test
  public void getMonth() {
    final int expected = 2;
    final int actual = date.getMonth();

    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setMonth_throwsExceptionWhenLowerThan1() throws IllegalArgumentException {
    date.setMonth(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setMonth_throwsExceptionWhenHigherThan13() throws IllegalArgumentException {
    date.setMonth(13);
  }

  @Test
  public void setMonth_acceptsValuesBetween1And12() throws IllegalArgumentException {
    final int expected = 6;
    date.setMonth(6);
    final int actual = date.getMonth();

    assertEquals(expected, actual);
  }

  @Test
  public void getDay() {
    final int expected = 3;
    final int actual = date.getDay();

    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setDay_throwsExceptionWhenLowerThan1() throws IllegalArgumentException {
    date.setDay(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setDay_throwsExceptionWhenHigherThan31() throws IllegalArgumentException {
    date.setMonth(1);
    date.setDay(32);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setDay_throwsExceptionWhenHigherThan30() throws IllegalArgumentException {
    date.setMonth(4);
    date.setDay(31);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setDay_throwsExceptionWhenHigherThan29InFebruaryOfLeapYears() throws IllegalArgumentException {
    date.setYear(2000);
    date.setMonth(2);
    date.setDay(30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setDay_throwsExceptionWhenHigherThan28InFebruary() throws IllegalArgumentException {
    date.setYear(1900);
    date.setMonth(2);
    date.setDay(29);
  }

  @Test
  public void setDay_acceptsCorrectValuesBetween1And31() throws IllegalArgumentException {
    final int expected = 15;
    date.setDay(15);
    final int actual = date.getDay();

    assertEquals(expected, actual);
  }

  @Test
  public void getAgeInDaysAt() {
    final int expected = 398;
    final int actual = reference.getAgeInDaysAt(date);

    assertEquals(expected, actual);
  }

  @Test
  public void getAgeInYearsAt() {
    final int expected = 1;
    final int actual = reference.getAgeInYearsAt(date);

    assertEquals(expected, actual);
  }
}
