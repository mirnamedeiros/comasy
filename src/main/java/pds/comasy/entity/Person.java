package pds.comasy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CollectionTable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String name;

    @Column
    private Date birthday;

    @ElementCollection
    @CollectionTable(name = "phone_numbers", joinColumns = @JoinColumn(name = "person_cpf"))
    @Column(name = "phone_number")
    private List<String> phoneNumber;

    @Column
    private String cnh;

    public Person(String cpf, String name, Date birthday, List<String> phoneNumber, String cnh) {
        this.cpf = cpf;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.cnh = cnh;
    }

    public Person() {}
}
