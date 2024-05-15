package pds.comasy.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @Column(name = "cpf_titular")
    private String cpf_titular;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_authentication_id", referencedColumnName = "id")
    private UserAuthentication userAuthentication;
}
