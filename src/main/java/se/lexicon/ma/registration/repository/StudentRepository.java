package se.lexicon.ma.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.ma.registration.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student,String> {

    Optional<Student> findStudentByEmailIgnoreCase(String email);

    List<Student> findStudentByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

}
