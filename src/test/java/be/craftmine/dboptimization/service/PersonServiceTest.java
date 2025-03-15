package be.craftmine.dboptimization.service;

import be.craftmine.dboptimization.entity.Address;
import be.craftmine.dboptimization.entity.Person;
import be.craftmine.dboptimization.repository.AddressRepository;
import be.craftmine.dboptimization.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    @Commit
    @Transactional(propagation = Propagation.NOT_SUPPORTED) // Évite que Spring rollback automatiquement après le test
    void initData() {
        for (int i = 0; i < 1000; i++) {
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
        }
    }

    @Test
    void testFindByNameWithAddresses() {
        List<Person> persons = personRepository.findByNameWithAddresses("Person 500");
        assertThat(persons.getFirst().getAddresses()).hasSize(5);
    }
}