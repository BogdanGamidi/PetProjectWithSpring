package com.body.repositories;

import com.body.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    @Query(value = "select person.id, person.first_name, person.last_name from person", nativeQuery = true)
    List<Object> getAllPersons(); // <id, firstName, lastName>

    @Query(value = "select person.id, person.first_name, person.last_name from person where person.first_name = ?1", nativeQuery = true)
    List<Object> findByFirstName(String firstName);

    @Query(value = "select person.id, person.first_name, person.last_name from person where person.first_name = ?1 and person.last_name = ?2", nativeQuery = true)
    List<Object> findByFirstNameAndLastName(String firstName, String lastName);
}
