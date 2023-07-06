package org.example.dto;

public class NewStudentDTO {

  private String name;

  private Double gpa;

  private String pw;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Double getGpa() {
    return gpa;
  }

  public void setGpa(final Double gpa) {
    this.gpa = gpa;
  }

  public String getPw() {
    return pw;
  }

  public void setPw(final String pw) {
    this.pw = pw;
  }

}
