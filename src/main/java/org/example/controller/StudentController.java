package org.example.controller;

import org.example.model.Student;
import org.example.model.StudentStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 *  Student Controller. Edits student objects using API calls
 *
 * @author Ethan Cruz
 * @author Daniel Krupp
 *
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentStore studentStore;

    @Autowired
    public StudentController(StudentStore studentStore) {
        this.studentStore = studentStore;
    }


    // Get all students when calling GET /student
    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(this.studentStore.getUsers());
    }

    // get a specific student using their ID when calling GET /student/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(
            @PathVariable("id") final Long id
    ) {
        final Student student = studentStore.getUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    // removed student with their ID equal to {id}. Checks for password.
    @DeleteMapping("/{id}/{pw}")
    public ResponseEntity<Long> getStudent(
            @PathVariable("id") final Long id,
            @PathVariable("pw") final String pw
    ) {
        boolean success = studentStore.removeStudent(id,pw);

        System.out.println(success);
//        if(success == false) {
//            return ResponseEntity.notFound().build();
//        }

        return ResponseEntity.ok(id);
    }

    // add a student when calling PUT /student/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Student> changeStudent(
            @PathVariable("id") final Long id,
            @RequestBody final Student student
    ) {
        final Student existingUser = studentStore.getUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        if(student.getName() != null) existingUser.setName(student.getName());
        if(student.getGpa() != null) existingUser.setGpa(student.getGpa());
        if(student.getPw() != null) existingUser.setPw(student.getPw());
        if(student.getId() != null) existingUser.setId(student.getId());

        return ResponseEntity.ok(existingUser);
    }

    // create any number of students using POST /student
    @PostMapping("")
    public ResponseEntity<?> createUser(
            @RequestBody List<Student> studentList
    ) {
        for (Student stu: studentList)
        {
            studentStore.addUser(stu);
        }
        return ResponseEntity.created(URI.create("/user/" + studentList.get(0).getId())).build();
    }


}
