package se.lexicon.ma.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.ma.registration.entity.Student;
import se.lexicon.ma.registration.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAll(){
        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().iterator().forEachRemaining(studentList::add);
        return ok(studentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") String id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent())
            return ResponseEntity.ok(optionalStudent.get());
        else
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build(); //200 ok
        }
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //204 data not found
    }

    @PostMapping("/")
    public ResponseEntity<Student> save(@RequestBody Student student){
        Student result = studentRepository.save(student);
        if (result.isStatus())
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/") //only one request with put mapping
    public ResponseEntity<Student> update(@RequestBody Student student){
        Student result = studentRepository.save(student);
        if (result.isStatus())
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
