package be.craftmine.dboptimization.service;

import be.craftmine.dboptimization.entity.Person;
import be.craftmine.dboptimization.repository.PersonRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersonsByName(String name) {
        return personRepository.findByNameWithAddresses(name); // Optimisé avec JOIN FETCH
    }
}
