package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.example.dto.NewStudent;
import org.example.model.Student;
import org.example.model.StudentStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
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

    private static final ObjectMapper MAPPER = new ObjectMapper();

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

        // Does not tell the user whether the deletion was successful.
//        return ResponseEntity.ok(id);

        // This would make more sense.
        if(success) {
            return ResponseEntity.ok(id);
        } else {
            // NOT FOUND (404) specifically to not expose anything about the password.
            return ResponseEntity.notFound().build();
        }
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

        // This is fine, but generally with a PUT/PATCH request
        // we don't return anything.
//        return ResponseEntity.ok(existingUser);

        // This makes more sense.
        return ResponseEntity.noContent().build();
    }

    // create any number of students using POST /student
    @PostMapping("")
    public ResponseEntity<?> createUser(
            @RequestBody List<NewStudent> studentList
    ) throws JsonProcessingException {
        final List<Student> students = new ArrayList<>();

        // This is great.
        for (NewStudent stu: studentList)
        {
            final Student student = MAPPER.readValue(MAPPER.writeValueAsString(stu), Student.class);

            studentStore.addUser(student);

            students.add(student);
        }

        // This causes a problem.
        // If the API caller sends an empty array,
        // the size of `studentList` is 0,
        // therefore, `studentList.get(0)` will throw an exception.
//        return ResponseEntity.created(URI.create("/user/" + studentList.get(0).getId())).build();

        // Also, it wouldn't make sense to return only the first URL.
        // The API caller could have specified more-than-one user.
        // What would make more sense would be to return the generated IDs
        // or even the entire list of created students.

        final List<Long> newIds = new ArrayList<>();

        for(final Student student : students) {
            newIds.add(student.getId());
        }

        return ResponseEntity.created(null)
            .body(newIds);
    }


}
