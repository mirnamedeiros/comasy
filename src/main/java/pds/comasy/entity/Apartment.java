package pds.comasy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    private Long id;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private String block;

    @Column(nullable = false)
    private String residentOwnerCpf;
}