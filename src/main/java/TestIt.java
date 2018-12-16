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
          System.out.println((i + 1) + ". " + linkedDocumentCollection.get(i).getTitle() + "; Relevanz: " + linkedDocumentCollection.getQueryRelevance(i));
      } else if (command.equals("crawl")) {
        linkedDocumentCollection = linkedDocumentCollection.crawl();
      } else if (command.equals("pageRank")) {
        double[] pageRank = linkedDocumentCollection.pageRank(0.85);

        for (int i = 0; i < linkedDocumentCollection.numDocuments(); ++i)
          System.out.println(linkedDocumentCollection.get(i).getTitle() + "; PageRank: " + pageRank[i]);
      }
    }
  }
}

/*
 * TESTS:
 *
 * Scenario 1:
 * ------
 * | a.txt                               | c.txt                       | d.txt                    | e.txt                      |
 * | es war einmal link:b.txt link:c.txt | once upon a time link:d.txt | erase una vez link:c.txt | c era una volta link:b.txt |
 * ------
 * > add b.txt:link:a.txt link:e.txt
 * > crawl
 * Fehler beim Lesen der Datei! (b.txt) (java.io.FileNotFoundException: b.txt (The system cannot find the file specified))
 * Fehler beim Lesen der Datei! (b.txt) (java.io.FileNotFoundException: b.txt (The system cannot find the file specified))
 * > pageRank
 * b.txt; PageRank: 0.049488617617948226
 * a.txt; PageRank: 0.07052128010557623
 * e.txt; PageRank: 0.07052128010557623
 * c.txt; PageRank: 0.5459352457043024
 * d.txt; PageRank: 0.5135335764666056
 * > query einmal
 * 1. a.txt; Relevanz: 0.3746186735560059
 * 2. c.txt; Relevanz: 0.21837409828172027
 * 3. d.txt; Relevanz: 0.20541343058664166
 * 4. e.txt; Relevanz: 0.0282085120422304
 * 5. b.txt; Relevanz: 0.01979544704717923
 * > exit
 * ------
 *
 * Scenario 2:
 * ------
 * | A                                   | B                   | C                         | D              |
 * | nothing of importance link:B link:C | more content link:D | some other content link:A | content link:B |
 * ------
 * > add E:more of the same content link:C
 * > crawl
 * > pageRank
 * E; PageRank: 0.03750000000000033
 * C; PageRank: 0.13356164383561758
 * A; PageRank: 0.1510273972602753
 * B; PageRank: 0.4813032210292524
 * D; PageRank: 0.446607737874865
 * > query content
 * 1. D; Relevanz: 0.7786430951499468
 * 2. B; Relevanz: 0.6167853571236303
 * 3. C; Relevanz: 0.39983481904802276
 * 4. E; Relevanz: 0.2833281572999749
 * 5. A; Relevanz: 0.06041095890411037
 * > exit
 * ------
 * */
