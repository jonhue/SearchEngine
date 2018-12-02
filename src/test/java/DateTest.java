import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    final Date todayWithParameters = new Date(Terminal.TODAYS_DAY, Terminal.TODAYS_MONTH, Terminal.TODAYS_YEAR);
    final Date todayWithoutParameters = new Date();

    assertTrue(todayWithoutParameters.equals(todayWithParameters));
  }

  @Test
  public void toString_hasCorrectFormat() {
    assertEquals("<Date year=1971 month=2 day=3>", date.toString());
  }

  @Test
  public void equals_detectsEquality() {
    final Date anotherDate = new Date(3, 2, 1971);

    assertTrue(date.equals(anotherDate));
  }

  @Test
  public void equals_detectsInequality() {
    assertFalse(date.equals(new Date()));
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    assertFalse(date.equals(null));
  }

  @Test
  public void getYear() {
    assertEquals(1971, date.getYear());
  }

  @Test
  public void setYear() {
    final int year = 2018;
    date.setYear(year);

    assertEquals(year, date.getYear());
  }

  @Test
  public void getMonth() {
    assertEquals(2, date.getMonth());
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
    final int month = 6;
    date.setMonth(month);

    assertEquals(month, date.getMonth());
  }

  @Test
  public void getDay() {
    assertEquals(3, date.getDay());
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
    final int day = 15;
    date.setDay(day);

    assertEquals(day, date.getDay());
  }

  @Test
  public void getAgeInDaysAt() {
    assertEquals(398, reference.getAgeInDaysAt(date));
  }

  @Test
  public void getAgeInYearsAt() {
    assertEquals(1, reference.getAgeInYearsAt(date));
  }
}
