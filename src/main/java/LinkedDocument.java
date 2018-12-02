import utils.Terminal;

public class LinkedDocument extends Document {
  private String ID;
  private String[] outgoingIDs;
  private LinkedDocumentCollection outgoingLinks;
  private LinkedDocumentCollection incomingLinks;

  public LinkedDocument(String ID, String title, String lang, String summary, Date releaseDate, Author author, String content) throws IllegalArgumentException {
    super(title, lang, summary, releaseDate, author, content);
    setLinkCountZero();

    setID(ID);
    setOutgoingIDs(content);
    incomingLinks = new LinkedDocumentCollection();
  }

  public static LinkedDocument createLinkedDocumentFromFile(String fileName) {
    String[] fileContent = Terminal.readFile(fileName);
    if (fileContent == null || fileContent.length < 2) return null;

    String ID = fileName.substring(fileName.lastIndexOf("/") + 1);
    String title = fileContent[0];
    String lang = "";
    String summary = "";
    Date releaseDate = new Date();
    Author author = new Author("Foo", "Bar", new Date(), "Springfield", "me@jonhue.me");
    String content = fileContent[1];
    return new LinkedDocument(ID, title, lang, summary, releaseDate, author, content);
  }

  public String toString() {
    return "<LinkedDocument id=" + ID + ">";
  }

  public boolean equals(Document document) {
    if (document == null) return false;
    if (document instanceof LinkedDocument && ((LinkedDocument) document).ID.equals(ID)) return true;

    return super.equals(document);
  }

  public String getID() {
    return ID;
  }

  private void setID(String ID) {
    this.ID = ID;
  }

  public void addIncomingLink(LinkedDocument linkedDocument) {
    if (linkedDocument == null || equals(linkedDocument)) return;

    incomingLinks.appendDocument(linkedDocument);
  }

  public LinkedDocumentCollection getOutgoingLinks() {
    if (outgoingLinks == null) createOutgoingDocumentCollection();

    return outgoingLinks;
  }

  public LinkedDocumentCollection getIncomingLinks() {
    return incomingLinks;
  }

  private void setOutgoingIDs(String content) {
    outgoingIDs = findOutgoingIDs(content);
  }

  private String[] findOutgoingIDs(String content) {
    String[] outgoingIDs = new String[content.split("link:").length - 1];

    for (int i = 0; i < outgoingIDs.length; ++i) {
      int startPos = content.indexOf("link:");
      int endPos = content.indexOf(" ", startPos);
      if (endPos < 0) {
        outgoingIDs[i] = content.substring(startPos + 5);
      } else {
        outgoingIDs[i] = content.substring(startPos + 5, endPos);
        content = content.substring(endPos);
      }
    }

    return outgoingIDs;
  }

  private void setLinkCountZero() {
    for (int i = 0; i < getWordCounts().size(); ++i)
      if (getWordCounts().getWord(i).contains("link:"))
        getWordCounts().setCount(i, 0);
  }

  private void createOutgoingDocumentCollection() {
    outgoingLinks = new LinkedDocumentCollection();

    for (String ID : outgoingIDs) {
      LinkedDocument linkedDocument = createLinkedDocumentFromFile(ID);
      if (!equals(linkedDocument))
        outgoingLinks.appendDocument(linkedDocument);
    }
  }
}
