public class DocumentCollectionCell {
  private Document document;
  private DocumentCollectionCell next;
  private double similarity;

  public DocumentCollectionCell(Document document) {
    setDocument(document);
  }

  public DocumentCollectionCell(Document document, DocumentCollectionCell next) {
    setDocument(document);
    setNext(next);
  }

  public String toString() {
    return "<DocumentCollectionCell document=" + document.toString() + ">";
  }

  public boolean equals(DocumentCollectionCell documentCollectionCell) {
    if (documentCollectionCell == null || documentCollectionCell.next == null && next != null || documentCollectionCell.next != null && next == null) return false;

    return (documentCollectionCell.document == null && document == null || documentCollectionCell.document.equals(document)) && (documentCollectionCell.next == null && next == null || documentCollectionCell.next.equals(next));
  }

  public Document getDocument() {
    return document;
  }

  public void setDocument(Document document) {
    this.document = document;
  }

  public DocumentCollectionCell getNext() {
    return next;
  }

  public void setNext(DocumentCollectionCell next) {
    this.next = next;
  }

  public double getSimilarity() {
    return similarity;
  }

  public void setSimilarity(double similarity) {
    this.similarity = similarity;
  }
}
