import java.lang.IllegalArgumentException;
import utils.Terminal;

public class Date {
  private int year;
  private int month;
  private int day;

  public Date(int year, int month, int day) throws IllegalArgumentException {
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
    return date.getYear() == getYear() && date.getMonth() == getMonth() && date.getDay() == getDay();
  }

  /* Assuming that every month has precisely 30 days */
  public static int daysSince(Date date, Date reference) {
    return Date.monthsSince(date, reference) * 30 + (date.day - reference.day);
  }

  private static int monthsSince(Date date, Date reference) {
    return Date.yearsSince(date, reference) * 12 + (date.month - reference.month);
  }

  public static int yearsSince(Date date, Date reference) {
    return date.year - reference.year;
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
    if (month > 12) {
      throw new IllegalArgumentException("Month must not be higher than 12.");
    } else if (month < 1) {
      throw new IllegalArgumentException("Month must not be lower than 1.");
    }

    this.month = month;
  }

  public int getDay() {
    return day;
  }

  /* day must be one of {1..31} otherwise throws IllegalArgumentException */
  public void setDay(int day) {
    if (day > 31) {
      throw new IllegalArgumentException("Day must not be higher than 31.");
    } else if (day < 1) {
    throw new IllegalArgumentException("Day must not be lower than 1.");
  }

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
}