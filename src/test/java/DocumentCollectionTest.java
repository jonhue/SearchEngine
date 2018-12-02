import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentCollectionTest {
  private DocumentCollection documentCollection;
  private Document document;
  private Date releaseDate;
  private Author author;

  @Before
  public void setUp() {
    releaseDate = new Date(9, 11, 2017);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    document = new Document("Title", "en", "Summary", releaseDate, author, "content and");
    documentCollection = new DocumentCollection();
  }

  @Test
  public void toString_hasCorrectFormat() {
    assertEquals("<DocumentCollection size=0>", documentCollection.toString());
  }

  @Test
  public void equals_detectsEquality() {
    final DocumentCollection anotherDocumentCollection = new DocumentCollection();

    documentCollection.prependDocument(document);
    anotherDocumentCollection.prependDocument(document);

    assertTrue(documentCollection.equals(anotherDocumentCollection));
  }

  @Test
  public void equals_detectsInequality() {
    final DocumentCollection anotherDocumentCollection = new DocumentCollection();

    documentCollection.prependDocument(document);
    anotherDocumentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));

    assertFalse(documentCollection.equals(anotherDocumentCollection));
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    assertFalse(documentCollection.equals(null));
  }

  @Test
  public void prependDocument_returnsWhenNullGiven() {
    documentCollection.prependDocument(null);

    assertTrue(documentCollection.isEmpty());
  }

  @Test
  public void prependDocument_whenListIsEmpty() {
    documentCollection.prependDocument(document);

    assertFalse(documentCollection.isEmpty());
  }

  @Test
  public void prependDocument_whenListIsNotEmpty() {
    documentCollection.prependDocument(document);
    documentCollection.prependDocument(document);

    assertEquals(2, documentCollection.numDocuments());
  }

  @Test
  public void prependDocument_prepends() {
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));
    documentCollection.prependDocument(document);

    assertEquals(document, documentCollection.getFirstDocument());
  }

  @Test
  public void appendDocument_returnsWhenNullGiven() {
    documentCollection.appendDocument(null);

    assertTrue(documentCollection.isEmpty());
  }

  @Test
  public void appendDocument_whenListIsEmpty() {
    documentCollection.appendDocument(document);

    assertFalse(documentCollection.isEmpty());
  }

  @Test
  public void appendDocument_whenListIsNotEmpty() {
    documentCollection.appendDocument(document);
    documentCollection.appendDocument(document);

    assertEquals(2, documentCollection.numDocuments());
  }

  @Test
  public void appendDocument_appends() {
    documentCollection.appendDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));
    documentCollection.appendDocument(document);

    assertEquals(document, documentCollection.getLastDocument());
  }

  @Test
  public void isEmpty_whenEmpty() {
    assertTrue(documentCollection.isEmpty());
  }

  @Test
  public void isEmpty_whenNotEmpty() {
    documentCollection.prependDocument(document);

    assertFalse(documentCollection.isEmpty());
  }

  @Test
  public void numDocuments_whenEmpty() {
    assertEquals(0, documentCollection.numDocuments());
  }

  @Test
  public void numDocuments_whenNotEmpty() {
    documentCollection.prependDocument(document);

    assertEquals(1, documentCollection.numDocuments());
  }

  @Test
  public void getFirstDocument_whenEmpty() {
    assertNull(documentCollection.getFirstDocument());
  }

  @Test
  public void getFirstDocument_whenNotEmpty() {
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));
    documentCollection.prependDocument(document);

    assertEquals(document, documentCollection.getFirstDocument());
  }

  @Test
  public void getLastDocument_whenEmpty() {
    assertNull(documentCollection.getLastDocument());
  }

  @Test
  public void getLastDocument_whenNotEmpty() {
    documentCollection.appendDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));
    documentCollection.appendDocument(document);

    assertEquals(document, documentCollection.getLastDocument());
  }

  @Test
  public void get_whenIndexSmallerThan0() {
    assertNull(documentCollection.get(-1));
  }

  @Test
  public void get_whenIndexHigherOrEqualToLength() {
    assertNull(documentCollection.get(0));
  }

  @Test
  public void get_whenIndexHigherThan0AndSmallerThanLength() {
    documentCollection.appendDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));
    documentCollection.appendDocument(document);

    assertEquals(document, documentCollection.get(1));
  }

  @Test
  public void removeFirstDocument_whenEmpty() {
    documentCollection.removeFirstDocument();

    assertTrue(documentCollection.isEmpty());
  }

  @Test
  public void removeFirstDocument_whenNotEmpty() {
    documentCollection.appendDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));
    documentCollection.appendDocument(document);
    documentCollection.removeFirstDocument();

    assertEquals(document, documentCollection.getFirstDocument());
  }

  @Test
  public void removeLastDocument_whenEmpty() {
    documentCollection.removeLastDocument();

    assertTrue(documentCollection.isEmpty());
  }

  @Test
  public void removeLastDocument_whenNotEmpty() {
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));
    documentCollection.prependDocument(document);
    documentCollection.removeLastDocument();

    assertEquals(document, documentCollection.getLastDocument());
  }

  @Test
  public void remove_whenIndexSmallerThan0() {
    assertFalse(documentCollection.remove(-1));
  }

  @Test
  public void remove_whenIndexHigherOrEqualToLength() {
    assertFalse(documentCollection.remove(0));
  }

  @Test
  public void remove_whenIndexHigherThan0AndSmallerThanLength() {
    documentCollection.appendDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));
    documentCollection.appendDocument(document);

    assertTrue(documentCollection.remove(1));
  }

  @Test
  public void indexOf_whenNullGiven() {
    assertEquals(-1, documentCollection.indexOf(null));
  }

  @Test
  public void indexOf_whenEmpty() {
    assertEquals(-1, documentCollection.indexOf(document));
  }

  @Test
  public void indexOf_whenNotInList() {
    documentCollection.appendDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));

    assertEquals(-1, documentCollection.indexOf(document));
  }

  @Test
  public void indexOf_whenInList() {
    documentCollection.appendDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));
    documentCollection.appendDocument(document);

    assertEquals(1, documentCollection.indexOf(document));
  }

  @Test
  public void indexOf_whenInListTwice() {
    documentCollection.appendDocument(document);
    documentCollection.appendDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));
    documentCollection.appendDocument(document);

    assertEquals(0, documentCollection.indexOf(document));
  }

  @Test
  public void contains_whenNotInList() {
    assertFalse(documentCollection.contains(document));
  }

  @Test
  public void contains_whenInList() {
    documentCollection.appendDocument(document);

    assertTrue(documentCollection.contains(document));
  }

  @Test
  public void match_computesSimilarityAndOrdersDesc() {
    documentCollection.appendDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));
    documentCollection.appendDocument(document);
    documentCollection.match("content");

    assertEquals(0.707106781, documentCollection.getQuerySimilarity(0), 0.000000001);
  }

  @Test
  public void getQuerySimilarity_whenIndexSmallerThan0() {
    assertEquals(-1.0, documentCollection.getQuerySimilarity(-1), 0);
  }

  @Test
  public void getQuerySimilarity_whenIndexHigherOrEqualToLength() {
    assertEquals(-1.0, documentCollection.getQuerySimilarity(0), 0);
  }

  @Test
  public void getQuerySimilarity_whenIndexHigherThan0AndSmallerThanLength() {
    documentCollection.appendDocument(document);
    documentCollection.match("abc");

    assertEquals(0.0, documentCollection.getQuerySimilarity(0), 0);
  }

  @Test
  public void noOfDocumentsContainingWord_whenEmpty() {
    assertEquals(0, documentCollection.noOfDocumentsContainingWord("more"));
  }

  @Test
  public void noOfDocumentsContainingWord_whenNotEmpty() {
    documentCollection.appendDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "more"));
    documentCollection.appendDocument(document);

    assertEquals(1, documentCollection.noOfDocumentsContainingWord("more"));
  }
}
