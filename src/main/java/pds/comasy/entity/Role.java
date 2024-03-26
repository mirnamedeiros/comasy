package pds.comasy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pds.comasy.enums.EnumRole;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

    public static final int MANAGER = 1;

    public static final int DOORMAN = 2;

    public static final int JANITOR = 3;

    public static final int RESIDENT = 4;

    /**
     * ID da entidade
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARGO")
    @SequenceGenerator(name = "SEQ_ROLE", sequenceName = "public.id_seq_role", allocationSize = 1)
    @Column(name = "id_role")
    private Integer id;

    private String nome;

    public EnumRole getRole() {
        return switch (this.id) {
            case MANAGER -> EnumRole.MANAGER;
            case DOORMAN -> EnumRole.DOORMAN;
            case JANITOR -> EnumRole.JANITOR;
            default -> EnumRole.RESIDENT;
        };
    }
}
