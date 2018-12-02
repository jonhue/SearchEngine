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
    assertEquals("<LinkedDocument id=ID>", linkedDocument.toString());
  }

  @Test
  public void equals_detectsEqualityWhenLinkedDocument() {
    releaseDate = new Date(9, 11, 2018);
    author = new Author("Foo", "Bar", releaseDate, "Springfield", "me@jonhue.me");
    LinkedDocument anotherLinkedDocument = new LinkedDocument("ID", "Another Title", "de", "Another summary", releaseDate, author,
            "am strand von santa monica stoppen bullige polizisten die revolution eine riesige blinkende " +
                    "anzeigetafel warnt auf dem Weg am strand sind elektronische tretroller verboten hier fangen " +
                    "polizisten viele ab die vom wenige kilometer entfernten venice beach angerast kommen einhundertneunzig " +
                    "dollar müssen die fahrer zahlen noch einmal einhundertneunzig dollar wenn sie keinen helm tragen harte " +
                    "strafen um die plage in den griff zu bekommen");

    assertTrue(linkedDocument.equals(anotherLinkedDocument));
  }

  @Test
  public void equals_detectsEqualityWhenDocument() {
    releaseDate = new Date(9, 11, 2017);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    Document document = new Document("Title", "en", "Summary", releaseDate, author,
            "am strand von santa monica stoppen bullige polizisten die revolution eine riesige blinkende " +
                    "anzeigetafel warnt auf dem Weg am strand sind elektronische tretroller verboten hier fangen " +
                    "polizisten viele ab die vom wenige kilometer entfernten venice beach angerast kommen einhundertneunzig " +
                    "dollar müssen die fahrer zahlen noch einmal einhundertneunzig dollar wenn sie keinen helm tragen harte " +
                    "strafen um die plage in den griff zu bekommen");

    assertTrue(linkedDocument.equals(document));
  }

  @Test
  public void equals_detectsInequalityWhenLinkedDocument() {
    LinkedDocument anotherLinkedDocument = new LinkedDocument("ID2", "Another Title", "de", "Another summary", releaseDate, author, "content");

    assertFalse(linkedDocument.equals(anotherLinkedDocument));
  }

  @Test
  public void equals_detectsInequalityWhenDocument() {
    Document document = new Document("Another Title", "de", "Another summary", releaseDate, author, "content");

    assertFalse(linkedDocument.equals(document));
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    assertFalse(linkedDocument.equals(null));
  }

  @Test
  public void getID() {
    assertEquals("ID", linkedDocument.getID());
  }

  @Test
  public void addIncomingLink_whenNullGiven() {
    linkedDocument.addIncomingLink(null);

    assertTrue(linkedDocument.getIncomingLinks().isEmpty());
  }

  @Test
  public void addIncomingLink_whenReferenceToSelf() {
    linkedDocument.addIncomingLink(linkedDocument);

    assertTrue(linkedDocument.getIncomingLinks().isEmpty());
  }

  @Test
  public void addIncomingLink() {
    linkedDocument.addIncomingLink(new LinkedDocument("ID2", "Another Title", "de", "Another summary", releaseDate, author, "content"));

    assertEquals(1, linkedDocument.getIncomingLinks().numDocuments());
  }

  @Test
  public void createLinkedDocumentFromFile_whenGivenFileHasLessThan2Lines() {
    assertNull(LinkedDocument.createLinkedDocumentFromFile("src/test/support/empty"));
  }

  @Test
  public void createLinkedDocumentFromFile() {
    final LinkedDocument linkedDocument = LinkedDocument.createLinkedDocumentFromFile("src/test/support/ID");

    assertEquals("ID", linkedDocument.getID());
    assertEquals("ID", linkedDocument.getTitle());
    assertEquals("cont", linkedDocument.getWordCounts().getWord(1));
  }

  @Test
  public void getOutgoingLinks_whenEmpty() {
    assertTrue(linkedDocument.getOutgoingLinks().isEmpty());
  }

  @Test
  public void getOutgoingLinks_whenNotEmpty() {
    linkedDocument = new LinkedDocument("ID2", "Another Title", "de", "Another summary", releaseDate, author, "content link:src/test/support/ID");

    assertEquals(1, linkedDocument.getOutgoingLinks().numDocuments());
  }

  @Test
  public void getIncomingLinks_whenEmpty() {
    assertTrue(linkedDocument.getIncomingLinks().isEmpty());
  }

  @Test
  public void getIncomingLinks_whenNotEmpty() {
    linkedDocument.addIncomingLink(new LinkedDocument("ID2", "Another Title", "de", "Another summary", releaseDate, author, "content"));

    assertEquals(1, linkedDocument.getIncomingLinks().numDocuments());
  }
}
