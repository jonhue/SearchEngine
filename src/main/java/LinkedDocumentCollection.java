public class LinkedDocumentCollection extends DocumentCollection {
  public String toString() {
    return "<LinkedDocumentCollection size=" + numDocuments() + ">";
  }

  public void prependDocument(Document document) {
    if (!(document instanceof LinkedDocument) || contains(document)) return;

    super.prependDocument(document);
  }

  public void appendDocument(Document document) {
    if (!(document instanceof LinkedDocument) || contains(document)) return;

    super.appendDocument(document);
  }

  public void calculateIncomingLinks() {
    for (int i = 0; i < numDocuments(); ++i) {
      LinkedDocumentCollection outgoingLinks = ((LinkedDocument) get(i)).getOutgoingLinks();

      for (int j = 0; j < outgoingLinks.numDocuments(); ++j) {
        LinkedDocument linkedDocument = ((LinkedDocument) get(indexOf(outgoingLinks.get(j))));
        if (linkedDocument != null)
          linkedDocument.addIncomingLink(((LinkedDocument) get(i)));
      }
    }
  }

  public LinkedDocumentCollection crawl() {
    LinkedDocumentCollection resultCollection = new LinkedDocumentCollection();
    for (int i = 0; i < numDocuments(); ++i)
      resultCollection.appendDocument(get(i));

    crawl(resultCollection);

    return resultCollection;
  }

  private void crawl(LinkedDocumentCollection resultCollection) {
    boolean unknownDocuments = false;
    for (int i = resultCollection.numDocuments() - 1; i >= 0; --i) {
      LinkedDocumentCollection outgoingLinks = ((LinkedDocument) resultCollection.get(i)).getOutgoingLinks();

      for (int j = 0; j < outgoingLinks.numDocuments(); ++j) {
        if (resultCollection.indexOf(outgoingLinks.get(j)) == -1) {
          unknownDocuments = true;
          resultCollection.appendDocument(outgoingLinks.get(j));
        }
      }
    }

    if (unknownDocuments) crawl(resultCollection);
  }
}
