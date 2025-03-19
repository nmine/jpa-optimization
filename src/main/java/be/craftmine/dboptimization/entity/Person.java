package be.craftmine.dboptimization.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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
    private Set<Address> addresses;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<Child> children;
}
