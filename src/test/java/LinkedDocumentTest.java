import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedDocumentTest {
  private LinkedDocument linkedDocument;
  private Date releaseDate;
  private Author author;

  @Before
  public void setUp() {
    releaseDate = new Date(9, 11, 2017);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    linkedDocument = new LinkedDocument("ID", "Title", "en", "Summary", releaseDate, author,
            "am strand von santa monica stoppen bullige polizisten die revolution eine riesige blinkende " +
                    "anzeigetafel warnt auf dem Weg am strand sind elektronische tretroller verboten hier fangen " +
                    "polizisten viele ab die vom wenige kilometer entfernten venice beach angerast kommen einhundertneunzig " +
                    "dollar müssen die fahrer zahlen noch einmal einhundertneunzig dollar wenn sie keinen helm tragen harte " +
                    "strafen um die plage in den griff zu bekommen");
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<LinkedDocument id=ID>";
    final String actual = linkedDocument.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsEqualityWhenLinkedDocument() {
    releaseDate = new Date(9, 11, 2018);
    author = new Author("Foo", "Bar", releaseDate, "Springfield", "me@jonhue.me");
    final boolean actual = linkedDocument.equals(new LinkedDocument("ID", "Another Title", "de", "Another summary", releaseDate, author,
            "am strand von santa monica stoppen bullige polizisten die revolution eine riesige blinkende " +
                    "anzeigetafel warnt auf dem Weg am strand sind elektronische tretroller verboten hier fangen " +
                    "polizisten viele ab die vom wenige kilometer entfernten venice beach angerast kommen einhundertneunzig " +
                    "dollar müssen die fahrer zahlen noch einmal einhundertneunzig dollar wenn sie keinen helm tragen harte " +
                    "strafen um die plage in den griff zu bekommen"));

    assertTrue(actual);
  }

  @Test
  public void equals_detectsEqualityWhenDocument() {
    releaseDate = new Date(9, 11, 2017);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    final boolean actual = linkedDocument.equals(new Document("Title", "en", "Summary", releaseDate, author,
            "am strand von santa monica stoppen bullige polizisten die revolution eine riesige blinkende " +
                    "anzeigetafel warnt auf dem Weg am strand sind elektronische tretroller verboten hier fangen " +
                    "polizisten viele ab die vom wenige kilometer entfernten venice beach angerast kommen einhundertneunzig " +
                    "dollar müssen die fahrer zahlen noch einmal einhundertneunzig dollar wenn sie keinen helm tragen harte " +
                    "strafen um die plage in den griff zu bekommen"));

    assertTrue(actual);
  }

  @Test
  public void equals_detectsInequalityWhenLinkedDocument() {
    final boolean actual = linkedDocument.equals(new LinkedDocument("ID2", "Another Title", "de", "Another summary", releaseDate, author, "content"));

    assertFalse(actual);
  }

  @Test
  public void equals_detectsInequalityWhenDocument() {
    final boolean actual = linkedDocument.equals(new Document("Another Title", "de", "Another summary", releaseDate, author, "content"));

    assertFalse(actual);
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    final boolean actual = linkedDocument.equals(null);

    assertFalse(actual);
  }

  @Test
  public void getID() {
    final String expected = "ID";
    final String actual = linkedDocument.getID();

    assertEquals(expected, actual);
  }

  @Test
  public void addIncomingLink_whenNullGiven() {
    final int expected = 0;
    linkedDocument.addIncomingLink(null);
    final int actual = linkedDocument.getIncomingLinks().numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void addIncomingLink_whenReferenceToSelf() {
    final int expected = 0;
    linkedDocument.addIncomingLink(linkedDocument);
    final int actual = linkedDocument.getIncomingLinks().numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void addIncomingLink() {
    final int expected = 1;
    linkedDocument.addIncomingLink(new LinkedDocument("ID2", "Another Title", "de", "Another summary", releaseDate, author, "content"));
    final int actual = linkedDocument.getIncomingLinks().numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void createLinkedDocumentFromFile_whenGivenFileHasLessThan2Lines() {
    final LinkedDocument actual = LinkedDocument.createLinkedDocumentFromFile("src/test/support/empty");

    assertNull(actual);
  }

  @Test
  public void createLinkedDocumentFromFile() {
    final LinkedDocument actual = LinkedDocument.createLinkedDocumentFromFile("src/test/support/ID");

    assertEquals("ID", actual.getID());
    assertEquals("ID", actual.getTitle());
    assertEquals("cont", actual.getWordCounts().getWord(1));
  }

  @Test
  public void getOutgoingLinks_whenEmpty() {
    final int expected = 0;
    final int actual = linkedDocument.getOutgoingLinks().numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void getOutgoingLinks_whenNotEmpty() {
    final int expected = 1;
    linkedDocument = new LinkedDocument("ID2", "Another Title", "de", "Another summary", releaseDate, author, "content link:src/test/support/ID");
    final int actual = linkedDocument.getOutgoingLinks().numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void getIncomingLinks_whenEmpty() {
    final int expected = 0;
    final int actual = linkedDocument.getIncomingLinks().numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void getIncomingLinks_whenNotEmpty() {
    final int expected = 1;
    linkedDocument.addIncomingLink(new LinkedDocument("ID2", "Another Title", "de", "Another summary", releaseDate, author, "content"));
    final int actual = linkedDocument.getIncomingLinks().numDocuments();

    assertEquals(expected, actual);
  }
}
