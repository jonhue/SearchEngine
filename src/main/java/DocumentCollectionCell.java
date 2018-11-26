public class DocumentCollectionCell {
  private Document document;
  private DocumentCollectionCell next;
  private DocumentCollectionCell previous;
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
    if (documentCollectionCell == null) return false;
    if (documentCollectionCell.next == null && next != null || documentCollectionCell.next != null && next == null) return false;
    if (documentCollectionCell.previous == null && previous != null || documentCollectionCell.previous != null && previous == null) return false;

    boolean documentEqual = documentCollectionCell.document == null && document == null || documentCollectionCell.document.equals(document);
    boolean nextEqual = documentCollectionCell.next == null && next == null || documentCollectionCell.next.equals(next);

    return documentEqual && nextEqual && documentCollectionCell.similarity == similarity;
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

    if (next != null) next.previous = this;
  }

  public DocumentCollectionCell getPrevious() {
    return previous;
  }

  public void setPrevious(DocumentCollectionCell previous) {
    this.previous = previous;

    if (previous != null) previous.next = this;
  }

  public double getSimilarity() {
    return similarity;
  }

  public void setSimilarity(double similarity) {
    this.similarity = similarity;
  }
}
