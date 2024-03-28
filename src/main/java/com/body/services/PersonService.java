package com.body.services;

import com.body.forms.PersonForm;
import com.body.models.Person;
import com.body.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository userRepository;

    public PersonService(@Autowired PersonRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PersonRepository getRepository() {
        return userRepository;
    }

    public List<Person> getAllPersons() {
        return userRepository.findAll();
    }

    public Person createPerson(Person person) {
        return userRepository.save(person);
    }

    public Person updatePerson(Long id, PersonForm personForm) {
        Optional<Person> person = userRepository.findById(id);
        if (personForm.firstName != null) person.get().setFirstName(personForm.firstName);
        if (personForm.lastName != null) person.get().setLastName(personForm.lastName);
        if (personForm.login != null) person.get().setLogin(personForm.login);
        if (personForm.password != null) person.get().setPassword(personForm.password);
        return userRepository.save(person.get());
    }

    public void deletePerson(Long id) {
        userRepository.deleteById(id);
    }
}