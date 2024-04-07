package com.body.repositories;

import com.body.dto.PersonDto;
import com.body.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    @Query("select new com.body.dto.PersonDto(person.id, person.firstName, person.lastName) from Person person")
    List<PersonDto> getAllPersons();

    @Query("select new com.body.dto.PersonDto(person.id, person.firstName, person.lastName) from Person person where person.id = :id")
    PersonDto findPersonById(String id);
}
