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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
//    @Commit
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void initData() {
        for (int i = 0; i < 10000; i++) {
            Person person = new Person();
            person.setName("Person " + i);
            personRepository.save(person);

            for (int j = 0; j < 5; j++) {
                Address address = new Address();
                address.setStreet("Street " + j);
                address.setCity("City " + j);
                address.setPerson(person);
                addressRepository.save(address);
            }

            for (int j = 0; j < 5; j++) {
                Child child = new Child();
                child.setName("Child " + j);
                child.setPerson(person);
                childRepository.save(child);
            }
        }
    }

    @Test
    void deleteAllPersonsAndAddresses() {
        personRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    void nPlusOneQueries() {
        List<Person> persons = personRepository.findByName("Person 1000");
        List<Address> addresses = persons.get(0).getAddresses();
        List<Child> children = persons.get(0).getChildren();
        assertThat(addresses).hasSize(5);
        assertThat(children).hasSize(5);
    }

}