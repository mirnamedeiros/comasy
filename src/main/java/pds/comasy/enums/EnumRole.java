package pds.comasy.enums;

import lombok.Getter;
import pds.comasy.entity.Role;

@Getter
public enum EnumRole {
    MANAGER("MANAGER") {
        @Override
        public Integer getId() {
            return 1;
        }
    },
    DOORMAN("DOORMAN") {
        @Override
        public Integer getId() {
            return 2;
        }
    },
    JANITOR("JANITOR") {
        @Override
        public Integer getId() {
            return 3;
        }
    },
    RESIDENT("RESIDENT") {
        @Override
        public Integer getId() {
            return 4;
        }
    };

    private final String code;

    EnumRole(String code) {
        this.code = code;
    }

    public abstract Integer getId();

    public Integer getRole() {
        return switch (this) {
            case MANAGER -> Role.MANAGER;
            case DOORMAN -> Role.DOORMAN;
            case JANITOR -> Role.JANITOR;
            case RESIDENT -> Role.RESIDENT;
        };
    }
}