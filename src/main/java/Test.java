import java.util.Arrays;

public class Test {
  public static void main(String[] args) {
    Date releaseDate = new Date(9, 11, 2017);
    Author author = new Author("Jonas", "Hübotter", releaseDate, "Munich", "jonas.huebotter@tum.de");
    Document document1 = new Document("Title", "de", "Summary", releaseDate, author,
            "am strand von santa monica stoppen bullige polizisten die revolution eine riesige blinkende " +
            "anzeigetafel warnt auf dem Weg am strand sind elektronische tretroller verboten hier fangen " +
            "polizisten viele ab die vom wenige kilometer entfernten venice beach angerast kommen einhundertneunzig " +
            "dollar müssen die fahrer zahlen noch einmal einhundertneunzig dollar wenn sie keinen helm tragen harte " +
            "strafen um die plage in den griff zu bekommen");
    Document document2 = new Document("Title", "de", "Summary", releaseDate, author,
            "die grünen haben ihre spitzenkandidaten für die europawahl bestimmt ein parteitag wählte die " +
                    "brandenburgische europaabgeordnete ska keller auf platz eins der kandidatenliste der deutschen grünen für " +
                    "die wahl im mai zweitausendneunzehn keller erhielt achtundachtzig prozent der stimmen in der leipziger " +
                    "tagungshalle die sechsunddreißig jährige sitzt seit zweitausendneun im straßburger parlament und ist seit " +
                    "zweitausendsechzehn dort chefin der fraktion der grünen zuvor war sie grünen landeschefin in brandenburg");
    Document document3 = new Document("Title", "de", "Summary", releaseDate, author, "");

    /* WordCountsArray */
    System.out.println("##### WORDCOUNTSARRAY #####\n");
    System.out.println("WordCountsArray wordCountsArray = new WordCountsArray(1)");
    WordCountsArray wordCountsArray = new WordCountsArray(1);
    System.out.println("WordCountsArray size(): " + wordCountsArray.size());
    System.out.println("wordCountsArray.add(\"Hello\", 1)");
    wordCountsArray.add("Hello", 1);
    System.out.println("WordCountsArray size(): " + wordCountsArray.size());
    System.out.println("WordCountsArray getWord(0): " + wordCountsArray.getWord(0));
    System.out.println("WordCountsArray getCount(0): " + wordCountsArray.getCount(0));
    System.out.println("WordCountsArray setCount(0, 2);");
    wordCountsArray.setCount(0, 2);
    System.out.println("WordCountsArray getCount(0): " + wordCountsArray.getCount(0));
    System.out.println("wordCountsArray.add(\"World\", 1)");
    wordCountsArray.add("World", 1);
    System.out.println("WordCountsArray size(): " + wordCountsArray.size());

    /* Document */
    System.out.println("\n\n##### DOCUMENT #####\n");
    System.out.println("Document SUFFICES: " + Arrays.toString(Document.SUFFICES));
    System.out.println("Document1 getWordCounts(): " + document1.getWordCounts().toString());
    System.out.println("Text: am strand von santa monica stoppen bullige polizisten die revolution eine riesige blinkende " +
            "anzeigetafel warnt auf dem Weg am strand sind elektronische tretroller verboten hier fangen " +
            "polizisten viele ab die vom wenige kilometer entfernten venice beach angerast kommen einhundertneunzig " +
            "dollar müssen die fahrer zahlen noch einmal einhundertneunzig dollar wenn sie keinen helm tragen harte " +
            "strafen um die plage in den griff zu bekommen");
    System.out.print("[");
    for (int i = 0; i < document1.getWordCounts().size(); ++i) {
      System.out.print(document1.getWordCounts().getWord(i));
      if (i != document1.getWordCounts().size() - 1)
        System.out.print(", ");
    }
    System.out.print("]\n");
    System.out.println("Document2 getWordCounts(): " + document2.getWordCounts().toString());
    System.out.println("Text: die grünen haben ihre spitzenkandidaten für die europawahl bestimmt ein parteitag wählte die " +
            "brandenburgische europaabgeordnete ska keller auf platz eins der kandidatenliste der deutschen grünen für " +
            "die wahl im mai zweitausendneunzehn keller erhielt achtundachtzig prozent der stimmen in der leipziger " +
            "tagungshalle die sechsunddreißig jährige sitzt seit zweitausendneun im straßburger parlament und ist seit " +
            "zweitausendsechzehn dort chefin der fraktion der grünen zuvor war sie grünen landeschefin in brandenburg");
    System.out.print("[");
    for (int i = 0; i < document2.getWordCounts().size(); ++i) {
      System.out.print(document2.getWordCounts().getWord(i));
      if (i != document2.getWordCounts().size() - 1)
        System.out.print(", ");
    }
    System.out.print("]\n");
    System.out.println("Document3 getWordCounts(): " + document3.getWordCounts().toString());
    System.out.println("Text: ");
    System.out.print("[");
    for (int i = 0; i < document3.getWordCounts().size(); ++i) {
      System.out.print(document3.getWordCounts().getWord(i));
      if (i != document3.getWordCounts().size() - 1)
        System.out.print(", ");
    }
    System.out.print("]\n");
  }
}
