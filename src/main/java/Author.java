import utils.Terminal;

public class Author {
  private String firstName;
  private String lastName;
  private Date birthday;
  private String residence;
  private String email;

  public Author(String firstName, String lastName, Date birthday, String residence, String email) {
    setFirstName(firstName);
    setLastName(lastName);
    setBirthday(birthday);
    setResidence(residence);
    setEmail(email);
  }

  public String toString() {
    return "<Author firstName=" + firstName + " lastName=" + lastName + ">";
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

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return firstName + " " + lastName;
  }

  public String getContactInformation() {
    return getName() + Terminal.NEWLINE + getEmail() + Terminal.NEWLINE + getResidence();
  }

  public Integer getAgeAt(Date date) {
    return birthday.getAgeInYearsAt(date);
  }
}
