package pds.comasy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "condominium")
public class Condominium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "condominium_id")
    private Long id;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private int telephoneNumber;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String streetAddress;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @OneToMany
    List<Apartment> apartmentList;
}