package be.craftmine.dboptimization.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Child> children;
}
