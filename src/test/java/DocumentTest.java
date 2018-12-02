import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentTest {
  private Document document;
  private Date releaseDate;
  private Author author;

  @Before
  public void setUp() {
    releaseDate = new Date(9, 11, 2017);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    document = new Document("Title", "en", "Summary", releaseDate, author,
            "am strand von santa monica stoppen bullige polizisten die revolution eine riesige blinkende " +
            "anzeigetafel warnt auf dem Weg am strand sind elektronische tretroller verboten hier fangen " +
            "polizisten viele ab die vom wenige kilometer entfernten venice beach angerast kommen einhundertneunzig " +
            "dollar müssen die fahrer zahlen noch einmal einhundertneunzig dollar wenn sie keinen helm tragen harte " +
            "strafen um die plage in den griff zu bekommen");
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<Document title=Title lang=en author=<Author firstName=Jonas lastName=Hübotter>>";
    final String actual = document.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsEquality() {
    releaseDate = new Date(9, 11, 2017);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    final boolean actual = document.equals(new Document("Title", "en", "Summary", releaseDate, author,
            "am strand von santa monica stoppen bullige polizisten die revolution eine riesige blinkende " +
            "anzeigetafel warnt auf dem Weg am strand sind elektronische tretroller verboten hier fangen " +
            "polizisten viele ab die vom wenige kilometer entfernten venice beach angerast kommen einhundertneunzig " +
            "dollar müssen die fahrer zahlen noch einmal einhundertneunzig dollar wenn sie keinen helm tragen harte " +
            "strafen um die plage in den griff zu bekommen"));

    assertTrue(actual);
  }

  @Test
  public void equals_detectsInequality() {
    final boolean actual = document.equals(new Document("Another Title", "de", "Another summary", releaseDate, author, "Content ..."));

    assertFalse(actual);
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    final boolean actual = document.equals(null);

    assertFalse(actual);
  }

  @Test
  public void getTitle() {
    final String expected = "Title";
    final String actual = document.getTitle();

    assertEquals(expected, actual);
  }

  @Test
  public void setTitle() {
    final String expected = "Another Title";
    document.setTitle(expected);
    final String actual = document.getTitle();

    assertEquals(expected, actual);
  }

  @Test
  public void getLang() {
    final String expected = "en";
    final String actual = document.getLang();

    assertEquals(expected, actual);
  }

  @Test
  public void setLang() {
    final String expected = "de";
    document.setLang(expected);
    final String actual = document.getLang();

    assertEquals(expected, actual);
  }

  @Test
  public void getSummary() {
    final String expected = "Summary";
    final String actual = document.getSummary();

    assertEquals(expected, actual);
  }

  @Test
  public void setSummary() {
    final String expected = "Another summary";
    document.setSummary(expected);
    final String actual = document.getSummary();

    assertEquals(expected, actual);
  }

  @Test
  public void getReleaseDate() {
    final Date actual = document.getReleaseDate();

    assertEquals(releaseDate, actual);
  }

  @Test
  public void setReleaseDate() {
    final Date expected = new Date(1, 1, 1970);
    document.setReleaseDate(expected);
    final Date actual = document.getReleaseDate();

    assertEquals(expected, actual);
  }

  @Test
  public void getAuthor() {
    final Author actual = document.getAuthor();

    assertEquals(author, actual);
  }

  @Test
  public void setAuthor() {
    final Author expected = new Author("Foo", "Bar", releaseDate, "Springfield", "me@jonhue.me");
    document.setAuthor(expected);
    final Author actual = document.getAuthor();

    assertEquals(expected, actual);
  }

  @Test
  public void getWordCountsAtPosOne() {
    final String expected = "am";
    final String actual = document.getWordCounts().getWord(0);

    assertEquals(expected, actual);
  }

  @Test
  public void getWordCountsAtPosTwo() {
    final String expected = "strand";
    final String actual = document.getWordCounts().getWord(1);

    assertEquals(expected, actual);
  }

  @Test
  public void getWordCountsAtPosSix() {
    final String expected = "stopp";
    final String actual = document.getWordCounts().getWord(5);

    assertEquals(expected, actual);
  }

  @Test
  public void getAgeAt() {
    final int expected = 352;
    final Date today = new Date(27, 10, 2018);
    final int actual = document.getAgeAt(today);

    assertEquals(expected, actual);
  }
}
