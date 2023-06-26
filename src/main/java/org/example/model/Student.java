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
    private double gpa;

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

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

}
