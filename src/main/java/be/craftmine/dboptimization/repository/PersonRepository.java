package be.craftmine.dboptimization.repository;

import be.craftmine.dboptimization.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT DISTINCT p FROM Person p LEFT JOIN FETCH p.addresses LEFT JOIN FETCH p.children WHERE p.name = :name")
    List<Person> findByName(String name);
}
