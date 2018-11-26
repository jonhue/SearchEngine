public class DocumentCollection {
  private DocumentCollectionCell head;
  private DocumentCollectionCell tail;

  public DocumentCollection() {}

  public String toString() {
    return "<DocumentCollection size=" + numDocuments() + ">";
  }

  public boolean equals(DocumentCollection documentCollection) {
    if (documentCollection == null) return false;
    if (documentCollection.numDocuments() != numDocuments()) return false;

    boolean isEqual = true;
    for (int i = 0; i < numDocuments() && isEqual; ++i)
      if (!documentCollection.get(i).equals(get(i)))
        isEqual = false;

    return isEqual;
  }

  public void prependDocument(Document document) {
    if (document == null) return;

    head = new DocumentCollectionCell(document, head);
    if (head.getNext() == null)
      tail = head;
  }

  public void appendDocument(Document document) {
    if (document == null) return;

    if (isEmpty()) {
      head = tail = new DocumentCollectionCell(document);
    } else {
      tail.setNext(new DocumentCollectionCell(document));
      tail = tail.getNext();
    }
  }

  public boolean isEmpty() {
    return head == null && tail == null;
  }

  public int numDocuments() {
    if (isEmpty()) return 0;

    int num = 0;
    for (DocumentCollectionCell documentCollectionCell = head; documentCollectionCell != null; documentCollectionCell = documentCollectionCell.getNext())
      ++num;

    return num;
  }

  public Document getFirstDocument() {
    if (isEmpty()) return null;

    return get(0);
  }

  public Document getLastDocument() {
    if (isEmpty()) return null;

    return get(numDocuments() - 1);
  }

  public Document get(int index) {
    DocumentCollectionCell documentCollectionCell = getCell(index);
    if (documentCollectionCell == null) return null;

    return documentCollectionCell.getDocument();
  }

  public void removeFirstDocument() {
    remove(0);
  }

  public void removeLastDocument() {
    remove(numDocuments() - 1);
  }

  public boolean remove(int index) {
    if (index == 0) {
      if (head == null) return false;

      head = head.getNext();
      if (head == null)
        tail = null;
    } else {
      DocumentCollectionCell documentCollectionCellTail = getCell(index - 1);
      DocumentCollectionCell documentCollectionCellHead = getCell(index + 1);
      if (documentCollectionCellTail == null) return false;

      documentCollectionCellTail.setNext(documentCollectionCellHead);

      if (documentCollectionCellHead == null)
        tail = documentCollectionCellTail;
    }

    return true;
  }

  public int indexOf(Document document) {
    if (document == null) return -1;
    if (isEmpty()) return -1;

    int index = 0;
    for (DocumentCollectionCell documentCollectionCell = head; documentCollectionCell != null && !documentCollectionCell.getDocument().equals(document); documentCollectionCell = documentCollectionCell.getNext())
      ++index;

    if (index == numDocuments())
      return -1;
    else
      return index;
  }

  private DocumentCollectionCell getCell(int index) {
    if (index < 0 || index >= numDocuments()) return null;

    DocumentCollectionCell documentCollectionCell = head;
    for (int i = 0; i < index; ++i)
      documentCollectionCell = documentCollectionCell.getNext();

    return documentCollectionCell;
  }
}
