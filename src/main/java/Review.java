import java.lang.IllegalArgumentException;

public class Review {
  private String content;
  private Author author;
  private Document document;
  private String lang;
  private Date releaseDate;
  private int rating;

  public Review(Author author, Document document, String lang, Date releaseDate, int rating, String content) throws IllegalArgumentException {
    setAuthor(author);
    setDocument(document);
    setLang(lang);
    setReleaseDate(releaseDate);
    setRating(rating);
    setContent(content);
  }

  public String toString() {
    return "<Review rating=" + rating + " document=" + document.toString() + " author=" + author.toString() + ">";
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public Document getDocument() {
    return document;
  }

  public void setDocument(Document document) {
    this.document = document;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) throws IllegalArgumentException {
    if (rating > 10) {
      throw new IllegalArgumentException("Rating cannot be higher than 10.");
    } else if (rating < 0) {
      throw new IllegalArgumentException("Rating cannot be lower than 0.");
    }

    this.rating = rating;
  }

  public int getAgeAt(Date date) {
    return releaseDate.getAgeInDaysAt(date);
  }
}
