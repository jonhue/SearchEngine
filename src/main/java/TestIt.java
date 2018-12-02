import utils.Terminal;

public class TestIt {
  public static void main(String[] args) {
    LinkedDocumentCollection linkedDocumentCollection = new LinkedDocumentCollection();

    while (true) {
      String command = Terminal.askString("> ");

      if (command.equals("exit")) {
        break;
      } else if (command.substring(0, 3).equals("add")) {
        int dividerPos = command.indexOf(':');
        String title = command.substring(4, dividerPos);
        String content = command.substring(dividerPos + 1);

        linkedDocumentCollection.appendDocument(new LinkedDocument(title, title, null, null, null, null, content));
      } else if (command.equals("list")) {
        for (int i = 0; i < linkedDocumentCollection.numDocuments(); ++i)
          System.out.println(linkedDocumentCollection.get(i).getTitle());
      } else if (command.substring(0, 5).equals("count")) {
        String word = command.substring(6);

        for (int i = 0; i < linkedDocumentCollection.numDocuments(); ++i) {
          Document document = linkedDocumentCollection.get(i);
          WordCountsArray wordCountsArray = document.getWordCounts();
          int index = wordCountsArray.getIndexOfWord(word);

          if (index == -1)
            System.out.println(document.getTitle() + ": " + 0);
          else
            System.out.println(document.getTitle() + ": " + wordCountsArray.getCount(index));
        }
      } else if (command.substring(0, 5).equals("query")) {
        String query = command.substring(6);

        linkedDocumentCollection.match(query);

        for (int i = 0; i < linkedDocumentCollection.numDocuments(); ++i)
          System.out.println((i + 1) + ". " + linkedDocumentCollection.get(i).getTitle() + ": " + linkedDocumentCollection.getQuerySimilarity(i));
      } else if (command.equals("crawl")) {
        linkedDocumentCollection = linkedDocumentCollection.crawl();
      }
    }
  }
}

/*
 * TESTS:
 *
 * Scenario 1:
 * ------
 * | ID                                        | ID2                            | ID3                           | ID4                   | empty |
 * | some content link:ID2 link:ID4 link:empty | some content link:ID3 link:ID4 | some content link:ID4 link:ID | some content link:ID2 |       |
 * ------
 * > add Start:ID3
 * > list
 * Start
 * > crawl
 * > list
 * Start
 * ID3
 * ID4
 * ID
 * ID2
 * > exit
 * ------
 *
 * Scenario 2:
 * ------
 * | A             | C      | D      | E      |
 * | link:B link:C | link:D | link:C | link:B |
 * ------
 * > add B:link:A link:E
 * > list
 * B
 * > crawl
 * Fehler beim Lesen der Datei! (B) (java.io.FileNotFoundException: B (The system cannot find the file specified))
 * Fehler beim Lesen der Datei! (B) (java.io.FileNotFoundException: B (The system cannot find the file specified))
 * > list
 * B
 * A
 * E
 * C
 * D
 * > exit
 * ------
 * */
