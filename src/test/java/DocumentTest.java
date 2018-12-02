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
    assertEquals("<Document title=Title lang=en author=<Author firstName=Jonas lastName=Hübotter>>", document.toString());
  }

  @Test
  public void equals_detectsEquality() {
    releaseDate = new Date(9, 11, 2017);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    final Document anotherDocument = new Document("Title", "en", "Summary", releaseDate, author,
            "am strand von santa monica stoppen bullige polizisten die revolution eine riesige blinkende " +
                    "anzeigetafel warnt auf dem Weg am strand sind elektronische tretroller verboten hier fangen " +
                    "polizisten viele ab die vom wenige kilometer entfernten venice beach angerast kommen einhundertneunzig " +
                    "dollar müssen die fahrer zahlen noch einmal einhundertneunzig dollar wenn sie keinen helm tragen harte " +
                    "strafen um die plage in den griff zu bekommen");

    assertTrue(document.equals(anotherDocument));
  }

  @Test
  public void equals_detectsInequality() {
    final Document anotherDocument = new Document("Another Title", "de", "Another summary", releaseDate, author, "content");

    assertFalse(document.equals(anotherDocument));
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    assertFalse(document.equals(null));
  }

  @Test
  public void getTitle() {
    assertEquals("Title", document.getTitle());
  }

  @Test
  public void setTitle() {
    final String title = "Another Title";
    document.setTitle(title);

    assertEquals(title, document.getTitle());
  }

  @Test
  public void getLang() {
    assertEquals("en", document.getLang());
  }

  @Test
  public void setLang() {
    final String lang = "de";
    document.setLang(lang);

    assertEquals(lang, document.getLang());
  }

  @Test
  public void getSummary() {
    assertEquals("Summary", document.getSummary());
  }

  @Test
  public void setSummary() {
    final String summary = "Another summary";
    document.setSummary(summary);

    assertEquals(summary, document.getSummary());
  }

  @Test
  public void getReleaseDate() {
    assertEquals(releaseDate, document.getReleaseDate());
  }

  @Test
  public void setReleaseDate() {
    final Date releaseDate = new Date(1, 1, 1970);
    document.setReleaseDate(releaseDate);

    assertEquals(releaseDate, document.getReleaseDate());
  }

  @Test
  public void getAuthor() {
    assertEquals(author, document.getAuthor());
  }

  @Test
  public void setAuthor() {
    final Author author = new Author("Foo", "Bar", releaseDate, "Springfield", "me@jonhue.me");
    document.setAuthor(author);

    assertEquals(author, document.getAuthor());
  }

  @Test
  public void getWordCountsAtPosOne() {
    assertEquals("am", document.getWordCounts().getWord(0));
  }

  @Test
  public void getWordCountsAtPosTwo() {
    assertEquals("strand", document.getWordCounts().getWord(1));
  }

  @Test
  public void getWordCountsAtPosSix() {
    assertEquals("stopp", document.getWordCounts().getWord(5));
  }

  @Test
  public void getAgeAt() {
    final Date today = new Date(27, 10, 2018);

    assertEquals(352, document.getAgeAt(today));
  }
}
