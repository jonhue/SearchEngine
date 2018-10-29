import java.lang.IllegalArgumentException;
import utils.Terminal;

public class Date {
  private Integer year;
  private Integer month;
  private Integer day;

  public Date(Integer year, Integer month, Integer day) throws IllegalArgumentException {
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

  public static Integer daysSince(Date date, Date reference) {
    return Date.monthsSince(date, reference) * 30 + (date.day - reference.day);
  }

  private static Integer monthsSince(Date date, Date reference) {
    return Date.yearsSince(date, reference) * 12 + (date.month - reference.month);
  }

  public static Integer yearsSince(Date date, Date reference) {
    return date.year - reference.year;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Integer getMonth() {
    return month;
  }

  public void setMonth(int month) {
    if (month > 12) {
      throw new IllegalArgumentException("Month cannot be higher than 12.");
    } else if (month < 1) {
      throw new IllegalArgumentException("Month cannot be lower than 1.");
    }

    this.month = month;
  }

  public Integer getDay() {
    return day;
  }

  public void setDay(int day) {
    if (day > 31) {
      throw new IllegalArgumentException("Day cannot be higher than 31.");
    } else if (day < 1) {
    throw new IllegalArgumentException("Day cannot be lower than 1.");
  }

    this.day = day;
  }

  private Integer daysSince1970() {
    final Date reference = new Date(1970, 1, 1);
    return Date.daysSince(this, reference);
  }

  public Integer getAgeInDaysAt(Date date) {
    return Date.daysSince(date, this);
  }

  public Integer getAgeInYearsAt(Date date) {
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