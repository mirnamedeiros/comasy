package pds.comasy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resident")
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_id")
    private Long id;

    @Column(name = "apartment_number")
    private Integer apartmentNumber;

    @OneToOne
    @JoinColumn(name = "person_cpf")
    private Person person;

    @OneToMany
    @JoinColumn(name = "resident_id")
    private List<Person> dependents;

    @Column(name = "role_id")
    private Integer roleId;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
