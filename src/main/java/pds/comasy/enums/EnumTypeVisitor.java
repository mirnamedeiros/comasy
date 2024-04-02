package pds.comasy.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum EnumTypeVisitor {
    VISITOR("Visitor") {
        @Override
        public Integer getId() {
            return 1;
        }
    },
    DELIVERY("Delivery") {
        @Override
        public Integer getId() {
            return 2;
        }
    };

    private final String code;

    EnumTypeVisitor(String code) {
        this.code = code;
    }

    public abstract Integer getId();
}
