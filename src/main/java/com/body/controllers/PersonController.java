package com.body.controllers;

import com.body.forms.PersonForm;
import com.body.models.Person;
import com.body.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class PersonController {
    private final PersonService personService;

    public PersonController(@Autowired PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllUsers() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getUserById(@PathVariable Long id) {
        try {
            Person person = personService.getPersonById(id);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Person createUser(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updateUser(@PathVariable Long id, @RequestBody PersonForm personForm) {
        try {
            Person person = personService.updatePerson(id, personForm);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (personService.getPersonById(id) == null) {
            return "User not found";
        }
        personService.deletePerson(id);
        return "User was deleted";
    }
}
