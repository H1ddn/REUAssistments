//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope("singleton")
public final class StudentStore {
    private final List<Student> students = new ArrayList();

    public StudentStore() {
    }

    public void addUser(Student student) {
        long nextId;
        if (!this.students.isEmpty()) {
            nextId = ((Student)this.students.get(this.students.size() - 1)).getId() + 1L;
        } else {
            nextId = 1L;
        }

        student.setId(nextId);
        this.students.add(student);
    }

    public void removeUser(Student student) {
        this.students.remove(student);
    }

    public List<Student> getUsers() {
        return Collections.unmodifiableList(this.students);
    }
}
