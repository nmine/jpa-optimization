package be.craftmine.dboptimization.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @BatchSize(size = 10)
    private Set<Address> addresses;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
//    @BatchSize(size = 10)
    private Set<Child> children;
}
