package com.body.repositories;

import com.body.dto.PersonDto;
import com.body.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    @Query(value = "select new com.body.dto.PersonDto(id, firstName, lastName) from Person")
    List<PersonDto> getAllPersonsDto();

    @Query(value = "select new com.body.dto.PersonDto(id, firstName, lastName) from Person where id = :id")
    PersonDto getPersonDtoById(String id);
}
