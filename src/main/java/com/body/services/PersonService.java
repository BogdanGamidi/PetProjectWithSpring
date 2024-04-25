package com.body.services;

import com.body.dto.PersonDto;
import com.body.forms.PersonForm;
import com.body.models.Person;
import com.body.repositories.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final Logger logger = LogManager.getLogger(getClass());
    private final PersonRepository personRepository;

    public PersonService(@Autowired PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonRepository getRepository() {
        return personRepository;
    }

    // admin call
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    //user call
    public List<PersonDto> getAllPersonsDto() {
        return personRepository.getAllPersonsDto();
    }

    //user call
    public PersonDto getPersonDtoById(String id) {
        PersonDto personDto = null;
        try {
            personDto = personRepository.getPersonDtoById(id);
        } catch (Exception e) {
            logger.error("Person with id {} not found", id);
        }
        return personDto;
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(String id, PersonForm personForm) {
        Optional<Person> person = personRepository.findById(id);
        if (personForm.firstName != null) person.get().setFirstName(personForm.firstName);
        if (personForm.lastName != null) person.get().setLastName(personForm.lastName);
        if (personForm.login != null) person.get().setLogin(personForm.login);
        if (personForm.password != null) person.get().setPassword(personForm.password);
        return personRepository.save(person.get());
    }

    public void deletePerson(String id) {
        personRepository.deleteById(id);
    }
}