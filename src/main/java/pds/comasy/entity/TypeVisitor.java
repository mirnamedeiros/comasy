package pds.comasy.entity;

import lombok.Getter;
import lombok.Setter;
import pds.comasy.enums.EnumTypeVisitor;

@Getter
@Setter
public class TypeVisitor {

    public static final int VISITOR_ID = 1;
    public static final int DELIVERY_ID = 2;

    private int id;
    private String name;

    public EnumTypeVisitor getTypeVisitor() {
        return switch (this.id) {
            case VISITOR_ID -> EnumTypeVisitor.VISITOR;
            case DELIVERY_ID -> EnumTypeVisitor.DELIVERY;
            default -> null;
        };
    }
}
