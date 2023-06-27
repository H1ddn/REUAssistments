package org.example.model;

/**
 *  Student Object. Used to create and read students
 *  based off of a unique ID
 *
 * @author Ethan Cruz, Daniel Krupp
 */
public class Student {

    // Unique Student ID
    private Long id;

    // Student's ID
    private String name;

    // Student GPA (X.XX)
    private Double gpa;

    // Student password
    private String pw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(final String pw) {
        this.pw = pw;
    }

}
