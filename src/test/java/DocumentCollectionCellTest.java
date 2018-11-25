import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

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
    final String expected = "<DocumentCollectionCell document=<Document title=Title lang=en author=<Author firstName=Jonas lastName=Hübotter>>>";
    final String actual = documentCollectionCell.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsEquality() {
    final boolean expected = true;
    final Document document = new Document("Title", "en", "Summary", releaseDate, author, "Content ...");
    final boolean actual = documentCollectionCell.equals(new DocumentCollectionCell(document));

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsInequality() {
    final boolean expected = false;
    final Document document = new Document("Another Title", "de", "Another summary", releaseDate, author, "More ...");
    final boolean actual = documentCollectionCell.equals(new DocumentCollectionCell(document));

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsNestedInequality() {
    final boolean expected = false;
    final Document document1 = new Document("Title", "en", "Summary", releaseDate, author, "Content ...");
    final Document document2 = new Document("Another Title", "de", "Another summary", releaseDate, author, "More ...");
    final DocumentCollectionCell documentCollectionCell1 = new DocumentCollectionCell(document1);
    final DocumentCollectionCell documentCollectionCell2 = new DocumentCollectionCell(document2);
    documentCollectionCell.setNext(documentCollectionCell1);
    final boolean actual = documentCollectionCell.equals(new DocumentCollectionCell(document, documentCollectionCell2));

    assertEquals(expected, actual);
  }

  @Test
  public void equals_detectsInequalityWhenNull() {
    final boolean expected = false;
    final boolean actual = documentCollectionCell.equals(null);

    assertEquals(expected, actual);
  }

  @Test
  public void getDocument() {
    final Document actual = documentCollectionCell.getDocument();

    assertEquals(document, actual);
  }

  @Test
  public void setDocument() {
    final Document expected = new Document("Another Title", "de", "Another summary", releaseDate, author, "More ...");
    documentCollectionCell.setDocument(expected);
    final Document actual = documentCollectionCell.getDocument();

    assertEquals(expected, actual);
  }

  @Test
  public void getNext() {
    final DocumentCollectionCell actual = documentCollectionCell.getNext();

    assertNull(actual);
  }

  @Test
  public void setNext() {
    final DocumentCollectionCell expected = new DocumentCollectionCell(document);
    documentCollectionCell.setNext(expected);
    final DocumentCollectionCell actual = documentCollectionCell.getNext();

    assertEquals(expected, actual);
  }
}
