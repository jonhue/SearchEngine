import java.lang.IllegalArgumentException;
import utils.Terminal;

public class Date {
  private int year;
  private int month;
  private int day;

  public Date(int day, int month, int year) throws IllegalArgumentException {
    setYear(year);
    setMonth(month);
    setDay(day);
  }

  public Date() throws IllegalArgumentException {
    setYear(Terminal.TODAYS_YEAR);
    setMonth(Terminal.TODAYS_MONTH);
    setDay(Terminal.TODAYS_DAY);
  }

  public String toString() {
    return "<Date year=" + year + " month=" + month + " day=" + day + ">";
  }

  public boolean equals(Date date) {
    if (date == null) return false;

    return date.getYear() == getYear() && date.getMonth() == getMonth() && date.getDay() == getDay();
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getMonth() {
    return month;
  }

  /* month must be one of {1..12} otherwise throws IllegalArgumentException */
  public void setMonth(int month) {
    if (month > 12)
      throw new IllegalArgumentException("Month must not be higher than 12.");
    else if (month < 1)
      throw new IllegalArgumentException("Month must not be lower than 1.");

    this.month = month;
  }

  public int getDay() {
    return day;
  }

  /* day must be one of {1..(28/29/30/31)} depending on month and year; otherwise throws IllegalArgumentException */
  public void setDay(int day) {
    if (day < 1)
      throw new IllegalArgumentException("Day must not be lower than 1.");

    int maxDays = daysOfMonth(month, year);
    if (day > maxDays)
      throw new IllegalArgumentException("Day must not be higher than " + maxDays + ".");

    this.day = day;
  }

  private int daysSince1970() {
    final Date reference = new Date(1970, 1, 1);
    return Date.daysSince(this, reference);
  }

  public int getAgeInDaysAt(Date date) {
    return Date.daysSince(date, this);
  }

  public int getAgeInYearsAt(Date date) {
    return Date.yearsSince(date, this);
  }

  /* Leap years are the years that are divisible by 4, except those that are divisible by 100 and not divisible by 400. */
  private static boolean isLeapYear(int year) {
    if (year % 100 == 0 && year % 400 != 0) return false;
    return year % 4 == 0;
  }

  private static int daysSince(Date date, Date reference) {
    int result = 0;

    for (int year = reference.year + 1; year < date.year; ++year)
      result += daysInYear(year);

    return result + reference.daysFromDayInYear() + date.daysToDayInYear();
  }

  private static int yearsSince(Date date, Date reference) {
    return date.year - reference.year;
  }

  private static int daysInYear(int year) {
    if (isLeapYear(year)) return 366;
    return 365;
  }

  private int daysToDayInYear() {
    int result = 0;

    for (int month = 1; month < this.month; ++month)
      result += daysOfMonth(month, year);

    return result + day;
  }

  private int daysFromDayInYear() {
    int result = 0;

    for (int month = this.month; month < 13; ++month)
      result += daysOfMonth(month, year);

    return result - day;
  }

  private static int daysOfMonth(int month, int year) {
    switch (month) {
      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      case 2:
        if (isLeapYear(year)) {
          return 29;
        } else {
          return 28;
        }
      default:
        return 31;
    }
  }
}