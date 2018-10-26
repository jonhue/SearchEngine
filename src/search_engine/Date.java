package search_engine;

import libraries.Terminal;

public class Date {
  private int year;
  private int month;
  private int day;

  public Date(int year, int month, int day) {
    setYear(year);
    setMonth(month);
    setDay(day);
  }

  public Date() {
    setYear(Terminal.TODAYS_YEAR);
    setMonth(Terminal.TODAYS_MONTH);
    setDay(Terminal.TODAYS_DAY);
  }

  public String toString() {
    return "<Date year=" + year + " month=" + month + " day=" + day + ">";
  }

  public static int daysSince(Date date, Date reference) {
    return Date.yearsSince(date, reference) * 360 + (date.month - reference.month) * 30 + (date.day - reference.day);
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

  public void setMonth(int month) {
    this.month = month;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  private int daysSince1970() {
    Date reference = new Date(1970, 1, 1);
    return Date.daysSince(this, reference);
  }

  public int getAgeInDaysAt(Date date) {
    return Date.daysSince(date, this);
  }

  public int getAgeInYearsAt(Date date) {
    return Date.yearsSince(date, this);
  }
}

/*
 *
 * Kommentare:
 *
 * In den meisten Fällen ist ein Kommentar ein Code-Smell. In der Regel deutet ein Kommentar darauf hin, dass der Code in
 * seiner Komplexität vereinfacht werden kann. Besonders wichtig, um Kommentare nicht notwendig werden zu lassen, ist
 * die Isolation von Funktionalität (nicht redundant!). Durch die Benennung von Methoden, kann Code leserlich,
 * verständlich und platzsparend gehalten werden.
 * Zudem werden Kommentare schnell zum Maintenance-Horror: Wenn sich der Code ändert müssen sich die Kommentare
 * ebenfalls anpassen. Das sorgt für unnötige Arbeit. Dazu besteht die Gefahr, dass einige Kommentare vergessen werden.
 * Daher ist self-documenting Code definitiv zu bevorzugen.
 *
 * Allerdings gibt es auch Einzelfälle, in denen Kommentare sinnvoll sein können:
 *
 *   * Der Code kann weder vereinfacht, noch aufgespalten, werden.
 *   * Der Code wurde auf eine bestimmte Weise alteriert, die nicht offensichtlich ist. Ein Kommentar erspart einer
 *     anderen Person, oder dem zukünftigen Selbst, das Refactorn.
 *   * Kommentare werden genutzt, um Dokumentation für eine API, Library, o.ä. zu generieren. Nutzer müssen sich so gar
 *     nicht erst den Code ansehen. */