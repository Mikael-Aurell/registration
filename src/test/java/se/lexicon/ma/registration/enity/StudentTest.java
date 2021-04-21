package se.lexicon.ma.registration.enity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.ma.registration.entity.Student;

import java.time.LocalDateTime;

@SpringBootTest
public class StudentTest {

    Student objectTest;

    @BeforeEach
    public void setup(){
        objectTest = new Student();
        objectTest.setFirstName("Mikael");
        objectTest.setLastName("Aurell");
        objectTest.setAge(42);
        objectTest.setGender("Male");
        objectTest.setEmail("aurell.mikael@gmail.com");
        objectTest.setPhoneNumber("076-3173768");
        objectTest.setRegisterDate(LocalDateTime.of(1978,04,30,15,03));
        objectTest.setStatus(true);
    }

    @Test
    @DisplayName("Test1 Create Student")
    public void test_create_student(){
        Assertions.assertEquals(42, objectTest.getAge());
        Assertions.assertEquals("aurell.mikael@gmail.com", objectTest.getEmail());
        Assertions.assertEquals(04, objectTest.getRegisterDate().getMonthValue());
    }

    @Test
    @DisplayName("Test2 Equal")
    public void test_equal(){
        Student expected = new Student();
        expected.setFirstName("Mikael");
        expected.setLastName("Aurell");
        expected.setAge(42);
        expected.setGender("Male");
        expected.setEmail("aurell.mikael@gmail.com");
        expected.setPhoneNumber("076-3173768");
        expected.setRegisterDate(LocalDateTime.of(1978,04,30,15,03));
        expected.setStatus(true);
        Assertions.assertTrue(objectTest.equals(expected));
    }

    @Test
    @DisplayName("Test3 HashCode")
    public void test_hashcode(){
        Student expected = new Student();
        expected.setFirstName("Mikael");
        expected.setLastName("Aurell");
        expected.setAge(42);
        expected.setGender("Male");
        expected.setEmail("aurell.mikael@gmail.com");
        expected.setPhoneNumber("076-3173768");
        expected.setRegisterDate(LocalDateTime.of(1978,04,30,15,03));
        expected.setStatus(true);
        Assertions.assertEquals(expected.hashCode(),objectTest.hashCode());
    }
}
