package search_engine;

public class Main {
  public static void main(String[] args) throws Exception {
    Date birthday = new Date(2000, 2, 4);
    Date earlier = new Date(2017, 11, 25);
    Date date = new Date(2018, 10, 26);
    Author author = new Author("Jonas", "Hübotter", birthday, "München", "jonas.huebotter@gmail.com");
    Document document = new Document("Title", "en", "Summary ...", earlier, author, "...");
    Review review = new Review(author, document, "en", earlier, 10, "...");
    System.out.println(author.getName());
    System.out.println(date.toString());
    System.out.println(author.toString());
    System.out.println(document.toString());
    System.out.println(review.toString());
    System.out.println(author.getContactInformation());
    System.out.println(author.getBirthday().getAgeInDaysAt(date));
    System.out.println(author.getBirthday().getAgeInYearsAt(date));
    System.out.println(author.getAgeAt(date));
    System.out.println(document.getAgeAt(date));
    System.out.println(review.getAgeAt(date));
  }
}
