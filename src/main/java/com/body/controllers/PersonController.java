package com.body.controllers;

import com.body.forms.PersonForm;
import com.body.models.Person;
import com.body.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Person> getUserById(@PathVariable Long id) {
        if (personService.getPersonById(id) == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(personService.getPersonById(id));
    }

    @PostMapping
    public Person createUser(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PutMapping("/{id}")
    public Optional<Person> updateUser(@PathVariable Long id, @RequestBody PersonForm personForm) {
        if (personService.getPersonById(id) == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(personService.updatePerson(id, personForm));
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
