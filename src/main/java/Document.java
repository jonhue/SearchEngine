public class Document {
  private String title;
  private String lang;
  private String summary;
  private Date releaseDate;
  private Author author;
  private String content;

  public Document(String title, String lang, String summary, Date releaseDate, Author author, String content) {
    setTitle(title);
    setLang(lang);
    setSummary(summary);
    setReleaseDate(releaseDate);
    setAuthor(author);
    setContent(content);
  }

  public String toString() {
    return "<Document title=" + title + " lang=" + lang + " author=" + author.toString() + ">";
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public int getAgeAt(Date date) {
    return releaseDate.getAgeInDaysAt(date);
  }
}
