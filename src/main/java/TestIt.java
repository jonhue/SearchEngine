import utils.Terminal;

public class TestIt {
  public static void main(String[] args) {
    DocumentCollection documentCollection = new DocumentCollection();

    while (true) {
      String command = Terminal.askString("> ");

      if (command.equals("exit")) {
        break;
      } else if (command.substring(0, 3).equals("add")) {
        int dividerPos = command.indexOf(':');
        String title = command.substring(4, dividerPos);
        String content = command.substring(dividerPos + 1);

        documentCollection.appendDocument(new Document(title, null, null, null, null, content));
      } else if (command.equals("list")) {
        for (int i = 0; i < documentCollection.numDocuments(); ++i)
          System.out.println(documentCollection.get(i).getTitle());
      } else if (command.substring(0, 5).equals("count")) {
        String word = command.substring(6);

        for (int i = 0; i < documentCollection.numDocuments(); ++i) {
          Document document = documentCollection.get(i);
          WordCountsArray wordCountsArray = document.getWordCounts();
          int index = wordCountsArray.getIndexOfWord(word);

          if (index == -1)
            System.out.println(document.getTitle() + ": " + 0);
          else
            System.out.println(document.getTitle() + ": " + wordCountsArray.getCount(index));
        }
      } else if (command.substring(0, 5).equals("query")) {
        String query = command.substring(6);

        documentCollection.match(query);

        for (int i = 0; i < documentCollection.numDocuments(); ++i)
          System.out.println((i + 1) + ". " + documentCollection.get(i).getTitle() + ": " + documentCollection.getQuerySimilarity(i));
      }
    }
  }
}
