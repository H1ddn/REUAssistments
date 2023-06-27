//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Component
@Scope("singleton")
public final class StudentStore {
    private final List<Student> students = new ArrayList();

    public StudentStore() {
    }

    private Long getUnusedID() {
        Long prev = 0L;
        for (Student stu: students)
        {
            if(stu.getId() - prev > 1L) {
                return prev + 1L;
            }
            prev = stu.getId();
        }
        return prev + 1L;
    }

    public void addUser(Student student) {
        long nextId;
        if (!this.students.isEmpty()) {
            nextId = getUnusedID();
        } else {
            nextId = 1L;
        }

        student.setId(nextId);
        this.students.add(student);
    }

    /**
     *
     * @param id: ID of the student you are trying to remove
     * @param password: Password required to actually remove student
     * @return True if student was removed, False if student wasn't removed
     */
    public boolean removeStudent(Long id, String password){
        Iterator<Student> i = students.iterator();
        while (i.hasNext()){
            Student stu = i.next();
            if(stu.getId().equals(id) && stu.getPw().equals(password)){
                i.remove();
                return true;
            }
        }
        return false;
    }
    public void removeUser(Student student) {
        this.students.remove(student);
    }

    public List<Student> getUsers() {
        return Collections.unmodifiableList(this.students);
    }
}
