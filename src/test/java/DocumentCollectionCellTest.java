import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentCollectionCellTest {
  private DocumentCollectionCell documentCollectionCell;
  private Document document;
  private Date releaseDate;
  private Author author;

  @Before
  public void setUp() {
    releaseDate = new Date(9, 11, 2017);
    author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    document = new Document("Title", "en", "Summary", releaseDate, author, "Content ...");
    documentCollectionCell = new DocumentCollectionCell(document);
  }

  @Test
  public void toString_hasCorrectFormat() {
    assertEquals("<DocumentCollectionCell document=<Document title=Title lang=en author=<Author firstName=Jonas lastName=Hübotter>>>", documentCollectionCell.toString());
  }

  @Test
  public void equals_detectsEquality() {
    final Document anotherDocument = new Document("Title", "en", "Summary", releaseDate, author, "Content ...");
    final DocumentCollectionCell anotherDocumentCollectionCell = new DocumentCollectionCell(anotherDocument);

    assertTrue(documentCollectionCell.equals(anotherDocumentCollectionCell));
  }

  @Test
  public void equals_detectsInequality() {
    final Document anotherDocument = new Document("Another Title", "de", "Another summary", releaseDate, author, "More ...");
    final DocumentCollectionCell anotherDocumentCollectionCell = new DocumentCollectionCell(anotherDocument);

    assertFalse(documentCollectionCell.equals(anotherDocumentCollectionCell));
  }

  @Test
  public void equals_detectsNestedInequality() {
    final Document document1 = new Document("Title", "en", "Summary", releaseDate, author, "Content ...");
    final Document document2 = new Document("Another Title", "de", "Another summary", releaseDate, author, "More ...");
    final DocumentCollectionCell documentCollectionCell1 = new DocumentCollectionCell(document1);
    final DocumentCollectionCell documentCollectionCell2 = new DocumentCollectionCell(document2);
    final DocumentCollectionCell anotherDocumentCollectionCell = new DocumentCollectionCell(document, documentCollectionCell2);

    documentCollectionCell.setNext(documentCollectionCell1);

    assertFalse(documentCollectionCell.equals(anotherDocumentCollectionCell));
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    assertFalse(documentCollectionCell.equals(null));
  }

  @Test
  public void getDocument() {
    assertEquals(document, documentCollectionCell.getDocument());
  }

  @Test
  public void setDocument() {
    final Document document = new Document("Another Title", "de", "Another summary", releaseDate, author, "More ...");
    documentCollectionCell.setDocument(document);

    assertEquals(document, documentCollectionCell.getDocument());
  }

  @Test
  public void getNext() {
    assertNull(documentCollectionCell.getNext());
  }

  @Test
  public void setNext() {
    final DocumentCollectionCell anotherDocumentCollectionCell = new DocumentCollectionCell(document);
    documentCollectionCell.setNext(anotherDocumentCollectionCell);

    assertEquals(anotherDocumentCollectionCell, documentCollectionCell.getNext());
  }

  @Test
  public void setNext_doesNotSetPreviousWhenNull() {
    documentCollectionCell.setNext(null);

    assertNull(documentCollectionCell.getPrevious());
  }

  @Test
  public void setNext_setsPreviousWhenNotNull() {
    DocumentCollectionCell anotherDocumentCollectionCell = new DocumentCollectionCell(document);
    documentCollectionCell.setNext(anotherDocumentCollectionCell);

    assertEquals(documentCollectionCell, anotherDocumentCollectionCell.getPrevious());
  }

  @Test
  public void getPrevious() {
    assertNull(documentCollectionCell.getPrevious());
  }

  @Test
  public void setPrevious() {
    final DocumentCollectionCell anotherDocumentCollectionCell = new DocumentCollectionCell(document);
    documentCollectionCell.setPrevious(anotherDocumentCollectionCell);

    assertEquals(anotherDocumentCollectionCell, documentCollectionCell.getPrevious());
  }

  @Test
  public void setPrevious_doesNotSetNextWhenNull() {
    documentCollectionCell.setPrevious(null);

    assertNull(documentCollectionCell.getNext());
  }

  @Test
  public void setPrevious_setsNextWhenNotNull() {
    DocumentCollectionCell anotherDocumentCollectionCell = new DocumentCollectionCell(document);
    documentCollectionCell.setPrevious(anotherDocumentCollectionCell);

    assertEquals(documentCollectionCell, anotherDocumentCollectionCell.getNext());
  }

  @Test
  public void getSimilarity() {
    assertEquals(0.0, documentCollectionCell.getSimilarity(), 0);
  }

  @Test
  public void setSimilarity() {
    final double similarity = 0.5;
    documentCollectionCell.setSimilarity(similarity);

    assertEquals(similarity, documentCollectionCell.getSimilarity(), 0);
  }

  @Test
  public void getRelevance() {
    assertEquals(0.0, documentCollectionCell.getRelevance(), 0);
  }

  @Test
  public void setRelevance() {
    final double relevance = 0.5;
    documentCollectionCell.setRelevance(relevance);

    assertEquals(relevance, documentCollectionCell.getRelevance(), 0);
  }
}
