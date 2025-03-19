package be.craftmine.dboptimization.repository;

import be.craftmine.dboptimization.entity.Address;
import be.craftmine.dboptimization.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
}
