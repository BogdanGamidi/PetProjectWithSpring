package com.body.services;

import com.body.forms.PersonForm;
import com.body.models.Person;
import com.body.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository userRepository;

    public PersonService(@Autowired PersonRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Person> getAllPersons() {
        return userRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
    }

    public Person createPerson(Person person) {
        return userRepository.save(person);
    }

    public Person updatePerson(Long id, PersonForm personForm) {
        Person person = getPersonById(id);
        if (personForm.firstName != null) person.setFirstName(personForm.firstName);
        if (personForm.lastName != null) person.setLastName(personForm.lastName);
        if (personForm.login != null) person.setLogin(personForm.login);
        if (personForm.password != null) person.setPassword(personForm.password);
        return userRepository.save(person);
    }


    public void deletePerson(Long id) {
        userRepository.deleteById(id);
    }
}
