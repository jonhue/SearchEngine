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
  public void getQueryRelevance_whenIndexSmallerThan0() {
    assertEquals(-1.0, linkedDocumentCollection.getQueryRelevance(-1), 0);
  }

  @Test
  public void getQueryRelevance_whenIndexHigherOrEqualToLength() {
    assertEquals(-1.0, linkedDocumentCollection.getQueryRelevance(0), 0);
  }

  @Test
  public void getQueryRelevance_whenIndexHigherThan0AndSmallerThanLength() {
    linkedDocumentCollection.appendDocument(linkedDocument);
    linkedDocumentCollection.match("abc");

    assertEquals(0.1, linkedDocumentCollection.getQueryRelevance(0), 0);
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

  @Test
  public void pageRankRec_whenEmpty() {
    assertEquals(0, linkedDocumentCollection.pageRankRec(0.85).length);
  }

  @Test
  public void pageRankRec_whenNotEmpty() {
    LinkedDocument A = new LinkedDocument("A", "A", null, null, releaseDate, author, "link:src/test/support/B link:src/test/support/C");
    LinkedDocument B = new LinkedDocument("B", "B", null, null, releaseDate, author, "link:src/test/support/A link:src/test/support/C link:src/test/support/D");
    LinkedDocument C = new LinkedDocument("C", "C", null, null, releaseDate, author, "link:src/test/support/D");
    LinkedDocument D = new LinkedDocument("D", "D", null, null, releaseDate, author, "link:src/test/support/C");

    linkedDocumentCollection.appendDocument(A);
    linkedDocumentCollection.appendDocument(B);
    linkedDocumentCollection.appendDocument(C);
    linkedDocumentCollection.appendDocument(D);

    double[] pageRank = linkedDocumentCollection.pageRankRec(0.85);

//    assertEquals(0.0547, pageRank[0], 0.0001);
//    assertEquals(0.0607, pageRank[1], 0.0001);
//    assertEquals(0.4485, pageRank[2], 0.0001);
//    assertEquals(0.4359, pageRank[3], 0.0001);

    assertEquals(0.0012, pageRank[0], 0.0001);
    assertEquals(0.0019, pageRank[1], 0.0001);
    assertEquals(0.3014, pageRank[2], 0.0001);
    assertEquals(0.2459, pageRank[3], 0.0001);
  }

  @Test
  public void pageRank_whenEmpty() {
    assertEquals(0, linkedDocumentCollection.pageRank(0.85).length);
  }

  @Test
  public void pageRank_whenNotEmpty() {
    LinkedDocument A = new LinkedDocument("A", "A", null, null, releaseDate, author, "link:src/test/support/B link:src/test/support/C");
    LinkedDocument B = new LinkedDocument("B", "B", null, null, releaseDate, author, "link:src/test/support/A link:src/test/support/C link:src/test/support/D");
    LinkedDocument C = new LinkedDocument("C", "C", null, null, releaseDate, author, "link:src/test/support/D");
    LinkedDocument D = new LinkedDocument("D", "D", null, null, releaseDate, author, "link:src/test/support/C");

    linkedDocumentCollection.appendDocument(A);
    linkedDocumentCollection.appendDocument(B);
    linkedDocumentCollection.appendDocument(C);
    linkedDocumentCollection.appendDocument(D);

    double[] pageRank = linkedDocumentCollection.pageRank(0.85);

    assertEquals(0.0547, pageRank[0], 0.0001);
    assertEquals(0.0607, pageRank[1], 0.0001);
    assertEquals(0.4485, pageRank[2], 0.0001);
    assertEquals(0.4359, pageRank[3], 0.0001);
  }

  @Test
  public void match_computesRelevanceAndOrdersDesc() {
    LinkedDocument A = new LinkedDocument("a.txt", "a.txt", null, null, releaseDate, author, "es war einmal link:b.txt link:c.txt");
    LinkedDocument B = new LinkedDocument("b.txt", "b.txt", null, null, releaseDate, author, "link:a.txt link:e.txt");
    LinkedDocument C = new LinkedDocument("c.txt", "c.txt", null, null, releaseDate, author, "once upon a time link:d.txt");
    LinkedDocument D = new LinkedDocument("d.txt", "d.txt", null, null, releaseDate, author, "erase una vez link:c.txt");
    LinkedDocument E = new LinkedDocument("e.txt", "e.txt", null, null, releaseDate, author, "c era una volta link:b.txt");

    linkedDocumentCollection.appendDocument(A);
    linkedDocumentCollection.appendDocument(B);
    linkedDocumentCollection.appendDocument(C);
    linkedDocumentCollection.appendDocument(D);
    linkedDocumentCollection.appendDocument(E);

    linkedDocumentCollection.match("einmal");

    assertEquals(A, linkedDocumentCollection.getFirstDocument());
    assertEquals(0.3746, linkedDocumentCollection.getQueryRelevance(0), 0.0001);
    assertEquals(C, linkedDocumentCollection.get(1));
    assertEquals(0.2183, linkedDocumentCollection.getQueryRelevance(1), 0.0001);
    assertEquals(D, linkedDocumentCollection.get(2));
    assertEquals(0.2054, linkedDocumentCollection.getQueryRelevance(2), 0.0001);
    assertEquals(E, linkedDocumentCollection.get(3));
    assertEquals(0.0282, linkedDocumentCollection.getQueryRelevance(3), 0.0001);
    assertEquals(B, linkedDocumentCollection.getLastDocument());
    assertEquals(0.0197, linkedDocumentCollection.getQueryRelevance(4), 0.0001);
  }
}
