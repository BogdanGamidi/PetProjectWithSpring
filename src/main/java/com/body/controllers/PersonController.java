package com.body.controllers;

import com.body.dto.PersonDto;
import com.body.forms.PersonForm;
import com.body.models.Person;
import com.body.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(@Autowired PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/admin")
    public List<Person> findAllPersons() {
        return personService.findAllPersons();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable String id) {
        Optional<Person> person = personService.getRepository().findById(id);
        if (person.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person.get(), HttpStatus.OK);
    }

    @GetMapping
    public List<PersonDto> getAllPersons() {
        return personService.getAllPersonsDto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPersonDtoById(@PathVariable String id) {
        PersonDto personDto = personService.getPersonDtoById(id);
        if (personDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> createUser(@RequestBody Person person) {
        try {
            return new ResponseEntity<>(personService.createPerson(person), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updateUser(@PathVariable String id, @RequestBody PersonForm personForm) {
        try {
            return new ResponseEntity<>(personService.updatePerson(id, personForm), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        try {
            personService.deletePerson(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}