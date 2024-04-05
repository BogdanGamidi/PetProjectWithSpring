package com.body.controllers;

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
    public ResponseEntity<Person> getUserById(@PathVariable String id) {
        Optional <Person> person = personService.getRepository().findById(id);
        if(person.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person.get(), HttpStatus.OK);
    }

    @GetMapping
    public List<Object> getAllPerson() {
        return personService.getRepository().getAllPersons();
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<Object> getPersonByFirstName(@PathVariable String firstName) {
        Optional <List<Object>> person = Optional.ofNullable(personService.getPersonByFirstName(firstName));
        if(person.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person.get(), HttpStatus.OK);
    }

    @GetMapping("/{firstName}/{lastName}")
    public ResponseEntity<Object> getPersonByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        Optional <List<Object>> person = Optional.ofNullable(personService.getPersonByFirstAndLastName(firstName, lastName));
        if(person.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person.get(), HttpStatus.OK);
    }

    @PostMapping
    public Person createUser(@RequestBody Person person) {
        return personService.createPerson(person);
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