package org.example._do;

import org.example.model.Student;

/**
 * Say we have a table in a UI that displays a list of students.
 * We want to select one-or-more students.
 * Obviously, when we select a student in the table,
 * we don't want to update the database.
 * Instead, we want to track the selected students in the UI.
 */
public class StudentForTableDO {

  private Student student;

  private boolean selected;

  public Student getStudent() {
    return student;
  }

  public void setStudent(final Student student) {
    this.student = student;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(final boolean selected) {
    this.selected = selected;
  }

}
