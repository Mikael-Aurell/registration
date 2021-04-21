package se.lexicon.ma.registration.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.ma.registration.entity.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StudentRepositoryTest {

    StudentRepository testObject;
    Student studentTest;
    List<Student> students;

    @Autowired
    public void setTestObject(StudentRepository testObject) {
        this.testObject = testObject;
    }

    @BeforeEach
    public void setup(){
        studentTest = new Student();
        studentTest.setFirstName("Mikael");
        studentTest.setLastName("Aurell");
        studentTest.setAge(42);
        studentTest.setGender("Male");
        studentTest.setEmail("aurell.mikael@gmail.com");
        studentTest.setPhoneNumber("076-3173768");
        studentTest.setRegisterDate(LocalDateTime.of(1978,04,30,15,03));
        studentTest.setStatus(true);

        testObject.save(studentTest); //saving to db
    }

    @Test
    @DisplayName("Test1 FindAll")
    public void test_findAll(){
        students = new ArrayList<>();
        testObject.findAll().iterator().forEachRemaining(students::add);
        Assertions.assertEquals("Male", students.get(0).getGender());
    }

    @Test
    @DisplayName("Test2 FindById")
    public void test_find_by_id(){
        students = new ArrayList<>();
        testObject.findAll().iterator().forEachRemaining(students::add);
        String expected = students.get(0).getId();
        Assertions.assertEquals(42, testObject.findById(expected).get().getAge());
    }

    @Test
    @DisplayName("Test3 Delete")
    public void test_save(){
        students = new ArrayList<>();
        List<Student> expected = new ArrayList<>();
        testObject.delete(studentTest);
        testObject.findAll().iterator().forEachRemaining(students::add);
        Assertions.assertEquals(expected,students);
    }

    @Test
    @DisplayName("Test4 Find By Email")
    public void test_find_by_email(){
        Assertions.assertEquals("Mikael", testObject
                .findStudentByEmailIgnoreCase("aurell.mikael@gmail.com").get().getFirstName());
    }

    @Test
    @DisplayName("Test5 Find By FirstName and LastName")
    public void test_find_by_firstname_and_lastname(){
        Assertions.assertEquals("aurell.mikael@gmail.com", testObject
                .findStudentByFirstNameIgnoreCaseAndLastNameIgnoreCase("Mikael","Aurell").get(0).getEmail());
    }
}
