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
    assertEquals("<LinkedDocumentCollection size=0>", linkedDocumentCollection.toString());
  }

  @Test
  public void prependDocument_returnsWhenNullGiven() {
    linkedDocumentCollection.prependDocument(null);

    assertTrue(linkedDocumentCollection.isEmpty());
  }

  @Test
  public void prependDocument_returnsWhenDocumentGiven() {
    linkedDocumentCollection.prependDocument(new Document("Title", "en", "Summary", releaseDate, author, "content"));

    assertTrue(linkedDocumentCollection.isEmpty());
  }

  @Test
  public void prependDocument_returnsWhenListContainsDocument() {
    linkedDocumentCollection.prependDocument(linkedDocument);
    linkedDocumentCollection.prependDocument(linkedDocument);

    assertEquals(1, linkedDocumentCollection.numDocuments());
  }

  @Test
  public void prependDocument() {
    linkedDocumentCollection.prependDocument(linkedDocument);

    assertFalse(linkedDocumentCollection.isEmpty());
  }

  @Test
  public void appendDocument_returnsWhenNullGiven() {
    linkedDocumentCollection.appendDocument(null);

    assertTrue(linkedDocumentCollection.isEmpty());
  }

  @Test
  public void appendDocument_returnsWhenDocumentGiven() {
    linkedDocumentCollection.appendDocument(new Document("Title", "en", "Summary", releaseDate, author, "content"));

    assertTrue(linkedDocumentCollection.isEmpty());
  }

  @Test
  public void appendDocument_returnsWhenListContainsDocument() {
    linkedDocumentCollection.appendDocument(linkedDocument);
    linkedDocumentCollection.appendDocument(linkedDocument);

    assertEquals(1, linkedDocumentCollection.numDocuments());
  }

  @Test
  public void appendDocument() {
    linkedDocumentCollection.appendDocument(linkedDocument);

    assertFalse(linkedDocumentCollection.isEmpty());
  }

  @Test
  public void calculateIncomingLinks() {
    LinkedDocument linkedDocument = new LinkedDocument("ID2", "Another Title", "de", "Another summary", releaseDate, author, "content link:src/test/support/ID");
    LinkedDocument anotherLinkedDocument = (LinkedDocument) linkedDocument.getOutgoingLinks().get(0);

    linkedDocumentCollection.appendDocument(linkedDocument);
    linkedDocumentCollection.appendDocument(anotherLinkedDocument);
    linkedDocumentCollection.calculateIncomingLinks();

    assertEquals(1, anotherLinkedDocument.getIncomingLinks().numDocuments());
  }

  @Test
  public void crawl() {
    LinkedDocument linkedDocument = new LinkedDocument("Start", "Another Title", "de", "Another summary", releaseDate, author, "content link:src/test/support/ID");

    linkedDocumentCollection.appendDocument(linkedDocument);

    assertEquals(5, linkedDocumentCollection.crawl().numDocuments());
  }
}
