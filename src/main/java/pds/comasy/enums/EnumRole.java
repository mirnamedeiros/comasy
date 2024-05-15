package pds.comasy.enums;

public enum EnumRole {

    ADMIN("admin"),
    MANAGER("manager"),
    RESIDENT("resident"),
    DOORMAN("doorman");

    private String role;

    EnumRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}