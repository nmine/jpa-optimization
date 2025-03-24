package be.craftmine.dboptimization.service;

import be.craftmine.dboptimization.entity.Address;
import be.craftmine.dboptimization.entity.Child;
import be.craftmine.dboptimization.entity.Person;
import be.craftmine.dboptimization.repository.AddressRepository;
import be.craftmine.dboptimization.repository.ChildRepository;
import be.craftmine.dboptimization.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonServiceTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ChildRepository childRepository;

    @Test
    @Commit
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void initData() {
        for (int i = 0; i < 100000; i++) {
            Person person = savePerson(i);

            for (int j = 0; j < 5; j++) {
                saveAdress(j, person);
            }

            for (int j = 0; j < 5; j++) {
                saveChild(j, person);
            }
        }
        Person person = savePerson(500);
        saveAdress(500, person);
        saveChild(500, person);
    }

    private void saveChild(int j, Person person) {
        Child child = new Child();
        child.setName("Child " + j);
        child.setPerson(person);
        childRepository.save(child);
    }

    private void saveAdress(int j, Person person) {
        Address address = new Address();
        address.setStreet("Street " + j);
        address.setCity("City " + j);
        address.setPerson(person);
        addressRepository.save(address);
    }

    private Person savePerson(int i) {
        Person person = new Person();
        person.setName("Person " + i);
        personRepository.save(person);
        return person;
    }

    @Test
    void deleteAllPersonsAndAddresses() {
        personRepository.deleteAll();
        addressRepository.deleteAll();
        childRepository.deleteAll();
    }

    @Test
    void nPlusOneQueries() {
        List<Person> persons = personRepository.findByName("Person 500");
//        Set<Address> addresses = persons.getFirst().getAddresses();
//        Set<Child> children = persons.get(0).getChildren();
//        assertThat(addresses).hasSize(5);
//        assertThat(children).hasSize(5);
    }

}