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
    document = new Document("Title", "en", "Summary", releaseDate, author, "Content ...");
    documentCollection = new DocumentCollection();
  }

  @Test
  public void toString_hasCorrectFormat() {
    final String expected = "<DocumentCollection size=0>";
    final String actual = documentCollection.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsEquality() {
    final boolean expected = true;
    documentCollection.prependDocument(document);
    final DocumentCollection anotherDocumentCollection = new DocumentCollection();
    anotherDocumentCollection.prependDocument(document);
    final boolean actual = documentCollection.equals(anotherDocumentCollection);

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsInequality() {
    final boolean expected = false;
    documentCollection.prependDocument(document);
    final DocumentCollection anotherDocumentCollection = new DocumentCollection();
    anotherDocumentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    final boolean actual = documentCollection.equals(anotherDocumentCollection);

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    final boolean actual = documentCollection.equals(null);

    assertFalse(actual);
  }

  @Test
  public void prependDocument_returnsWhenNullGiven() {
    documentCollection.prependDocument(null);
    final boolean actual = documentCollection.isEmpty();

    assertTrue(actual);
  }

  @Test
  public void prependDocument_whenListIsEmpty() {
    documentCollection.prependDocument(document);
    final boolean actual = documentCollection.isEmpty();

    assertFalse(actual);
  }

  @Test
  public void prependDocument_whenListIsNotEmpty() {
    documentCollection.prependDocument(document);
    final int expected = 2;
    documentCollection.prependDocument(document);
    final int actual = documentCollection.numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void prependDocument_prepends() {
    final Document expected = document;
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    documentCollection.prependDocument(document);
    final Document actual = documentCollection.getFirstDocument();

    assertTrue(expected.equals(actual));
  }

  @Test
  public void appendDocument_returnsWhenNullGiven() {
    documentCollection.appendDocument(null);
    final boolean actual = documentCollection.isEmpty();

    assertTrue(actual);
  }

  @Test
  public void appendDocument_whenListIsEmpty() {
    documentCollection.appendDocument(document);
    final boolean actual = documentCollection.isEmpty();

    assertFalse(actual);
  }

  @Test
  public void appendDocument_whenListIsNotEmpty() {
    documentCollection.appendDocument(document);
    final int expected = 2;
    documentCollection.appendDocument(document);
    final int actual = documentCollection.numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void appendDocument_appends() {
    final Document expected = document;
    documentCollection.appendDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    documentCollection.appendDocument(document);
    final Document actual = documentCollection.getLastDocument();

    assertTrue(expected.equals(actual));
  }

  @Test
  public void isEmpty_whenEmpty() {
    final boolean actual = documentCollection.isEmpty();

    assertTrue(actual);
  }

  @Test
  public void isEmpty_whenNotEmpty() {
    documentCollection.prependDocument(document);
    final boolean actual = documentCollection.isEmpty();

    assertFalse(actual);
  }

  @Test
  public void numDocuments_whenEmpty() {
    final int expected = 0;
    final int actual = documentCollection.numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void numDocuments_whenNotEmpty() {
    final int expected = 1;
    documentCollection.prependDocument(document);
    final int actual = documentCollection.numDocuments();

    assertEquals(expected, actual);
  }

  @Test
  public void getFirstDocument_whenEmpty() {
    final Document actual = documentCollection.getFirstDocument();

    assertNull(actual);
  }

  @Test
  public void getFirstDocument_whenNotEmpty() {
    final Document expected = document;
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    documentCollection.prependDocument(document);
    final Document actual = documentCollection.getFirstDocument();

    assertTrue(expected.equals(actual));
  }

  @Test
  public void getLastDocument_whenEmpty() {
    final Document actual = documentCollection.getLastDocument();

    assertNull(actual);
  }

  @Test
  public void getLastDocument_whenNotEmpty() {
    final Document expected = document;
    documentCollection.prependDocument(document);
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    final Document actual = documentCollection.getLastDocument();

    assertTrue(expected.equals(actual));
  }

  @Test
  public void get_whenIndexSmallerThan0() {
    final Document actual = documentCollection.get(-1);

    assertNull(actual);
  }

  @Test
  public void get_whenIndexHigherOrEqualToLength() {
    final Document actual = documentCollection.get(0);

    assertNull(actual);
  }

  @Test
  public void get_whenIndexHigherThan0AndSmallerThanLength() {
    final Document expected = document;
    documentCollection.prependDocument(document);
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    final Document actual = documentCollection.get(1);

    assertTrue(expected.equals(actual));
  }

  @Test
  public void removeFirstDocument_whenEmpty() {
    documentCollection.removeFirstDocument();
    final boolean actual = documentCollection.isEmpty();

    assertTrue(actual);
  }

  @Test
  public void removeFirstDocument_whenNotEmpty() {
    final Document expected = document;
    documentCollection.prependDocument(document);
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    documentCollection.removeFirstDocument();
    final Document actual = documentCollection.getFirstDocument();

    assertTrue(expected.equals(actual));
  }

  @Test
  public void removeLastDocument_whenEmpty() {
    documentCollection.removeLastDocument();
    final boolean actual = documentCollection.isEmpty();

    assertTrue(actual);
  }

  @Test
  public void removeLastDocument_whenNotEmpty() {
    final Document expected = document;
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    documentCollection.prependDocument(document);
    documentCollection.removeLastDocument();
    final Document actual = documentCollection.getLastDocument();

    assertTrue(expected.equals(actual));
  }

  @Test
  public void remove_whenIndexSmallerThan0() {
    final boolean actual = documentCollection.remove(-1);

    assertFalse(actual);
  }

  @Test
  public void remove_whenIndexHigherOrEqualToLength() {
    final boolean actual = documentCollection.remove(0);

    assertFalse(actual);
  }

  @Test
  public void remove_whenIndexHigherThan0AndSmallerThanLength() {
    documentCollection.prependDocument(document);
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    final boolean actual = documentCollection.remove(1);

    assertTrue(actual);
  }

  @Test
  public void indexOf_whenNullGiven() {
    final int expected = -1;
    final int actual = documentCollection.indexOf(null);

    assertEquals(expected, actual);
  }

  @Test
  public void indexOf_whenEmpty() {
    final int expected = -1;
    final int actual = documentCollection.indexOf(document);

    assertEquals(expected, actual);
  }

  @Test
  public void indexOf_whenNotInList() {
    final int expected = -1;
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    final int actual = documentCollection.indexOf(document);

    assertEquals(expected, actual);
  }

  @Test
  public void indexOf_whenInList() {
    final int expected = 1;
    documentCollection.prependDocument(document);
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    final int actual = documentCollection.indexOf(document);

    assertEquals(expected, actual);
  }

  @Test
  public void indexOf_whenInListTwice() {
    final int expected = 0;
    documentCollection.prependDocument(document);
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    documentCollection.prependDocument(document);
    final int actual = documentCollection.indexOf(document);

    assertEquals(expected, actual);
  }

  @Test
  public void contains_whenNotInList() {
    final boolean actual = documentCollection.contains(document);

    assertFalse(actual);
  }

  @Test
  public void contains_whenInList() {
    documentCollection.prependDocument(document);
    final boolean actual = documentCollection.contains(document);

    assertTrue(actual);
  }

  @Test
  public void match_computesSimilarityAndOrdersDesc() {
    final double expected = 0.707106781;
    documentCollection.prependDocument(document);
    documentCollection.prependDocument(new Document("Another Title", "de", "Another summary", releaseDate, author, "More ..."));
    documentCollection.match("content");
    final double actual = documentCollection.getQuerySimilarity(0);

    assertEquals(expected, actual, 0.000000001);
  }

  @Test
  public void getQuerySimilarity_whenIndexSmallerThan0() {
    final double expected = -1.0;
    final double actual = documentCollection.getQuerySimilarity(-1);

    assertEquals(expected, actual, 0);
  }

  @Test
  public void getQuerySimilarity_whenIndexHigherOrEqualToLength() {
    final double expected = -1.0;
    final double actual = documentCollection.getQuerySimilarity(0);

    assertEquals(expected, actual, 0);
  }

  @Test
  public void getQuerySimilarity_whenIndexHigherThan0AndSmallerThanLength() {
    final double expected = 0.0;
    documentCollection.prependDocument(document);
    documentCollection.match("abc");
    final double actual = documentCollection.getQuerySimilarity(0);

    assertEquals(expected, actual, 0);
  }
}
