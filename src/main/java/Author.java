import utils.Terminal;

public class Author {
  private String firstName;
  private String lastName;
  private Date birthday;
  private String residence;
  private String email;

  public Author(String firstName, String lastName, Date birthday, String residence, String email) throws IllegalArgumentException {
    setFirstName(firstName);
    setLastName(lastName);
    setBirthday(birthday);
    setResidence(residence);
    setEmail(email);
  }

  public String toString() {
    return "<Author firstName=" + firstName + " lastName=" + lastName + ">";
  }

  public boolean equals(Author author) {
    if (author == null) return false;

    return author.firstName.equals(firstName) && author.lastName.equals(lastName) && author.birthday.equals(birthday) && author.residence.equals(residence) && author.email.equals(email);
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getResidence() {
    return residence;
  }

  public void setResidence(String residence) {
    this.residence = residence;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) throws IllegalArgumentException {
    if (email.indexOf('@') == -1)
      throw new IllegalArgumentException("Invalid email address. Must contain @.");
    else if (email.indexOf('@') == 0)
      throw new IllegalArgumentException("Invalid email address. @ must not be the first character.");
    else if (email.length() < 6)
      throw new IllegalArgumentException("Invalid email address. Length must not be shorter than 6."); // Shortest email: a@b.ce

    this.email = email;
  }

  public String getName() {
    return firstName + " " + lastName;
  }

  public String getContactInformation() {
    return getName() + Terminal.NEWLINE + getEmail() + Terminal.NEWLINE + getResidence();
  }

  public int getAgeAt(Date date) {
    return birthday.getAgeInYearsAt(date);
  }
}
