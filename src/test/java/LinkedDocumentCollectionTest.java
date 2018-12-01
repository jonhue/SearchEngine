import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedDocumentCollectionTest {
  private LinkedDocumentCollection linkedDocumentCollection;
  private LinkedDocument linkedDocument;
  private Date releaseDate;
  private Author author;

  @Before
  public void setUp() {
    releaseDate = new Date(9, 11, 2017);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    linkedDocument = new LinkedDocument("ID", "Title", "en", "Summary", releaseDate, author, "content");
    linkedDocumentCollection = new LinkedDocumentCollection();
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<LinkedDocumentCollection size=0>";
    final String actual = linkedDocumentCollection.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void prependDocument_returnsWhenNullGiven() {
    linkedDocumentCollection.prependDocument(null);
    final boolean actual = linkedDocumentCollection.isEmpty();

    assertTrue(actual);
  }

  @Test
  public void prependDocument_returnsWhenDocumentGiven() {
    linkedDocumentCollection.prependDocument(new Document("Title", "en", "Summary", releaseDate, author, "content"));
    final boolean actual = linkedDocumentCollection.isEmpty();

    assertTrue(actual);
  }

  @Test
  public void prependDocument_returnsWhenListContainsDocument() {
    linkedDocumentCollection.prependDocument(linkedDocument);
    final int expected = 1;
    linkedDocumentCollection.prependDocument(linkedDocument);
    final int actual = linkedDocumentCollection.numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void prependDocument() {
    linkedDocumentCollection.prependDocument(linkedDocument);
    final boolean actual = linkedDocumentCollection.isEmpty();

    assertFalse(actual);
  }

  @Test
  public void appendDocument_returnsWhenNullGiven() {
    linkedDocumentCollection.appendDocument(null);
    final boolean actual = linkedDocumentCollection.isEmpty();

    assertTrue(actual);
  }

  @Test
  public void appendDocument_returnsWhenDocumentGiven() {
    linkedDocumentCollection.appendDocument(new Document("Title", "en", "Summary", releaseDate, author, "content"));
    final boolean actual = linkedDocumentCollection.isEmpty();

    assertTrue(actual);
  }

  @Test
  public void appendDocument_returnsWhenListContainsDocument() {
    linkedDocumentCollection.appendDocument(linkedDocument);
    final int expected = 1;
    linkedDocumentCollection.appendDocument(linkedDocument);
    final int actual = linkedDocumentCollection.numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void appendDocument() {
    linkedDocumentCollection.appendDocument(linkedDocument);
    final boolean actual = linkedDocumentCollection.isEmpty();

    assertFalse(actual);
  }

  @Test
  public void calculateIncomingLinks() {
    LinkedDocument linkedDocument = new LinkedDocument("ID2", "Another Title", "de", "Another summary", releaseDate, author, "content link:src/test/support/ID");
    linkedDocumentCollection.appendDocument(linkedDocument);
    LinkedDocument anotherLinkedDocument = (LinkedDocument) linkedDocument.getOutgoingLinks().get(0);
    linkedDocumentCollection.appendDocument(anotherLinkedDocument);
    linkedDocumentCollection.calculateIncomingLinks();
    final int expected = 1;
    final int actual = anotherLinkedDocument.getIncomingLinks().numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void crawl() {
    LinkedDocument linkedDocument = new LinkedDocument("Start", "Another Title", "de", "Another summary", releaseDate, author, "content link:src/test/support/ID");
    linkedDocumentCollection.appendDocument(linkedDocument);
    final int expected = 5;
    final int actual = linkedDocumentCollection.crawl().numDocuments();

    assertEquals(expected, actual);
  }
}
