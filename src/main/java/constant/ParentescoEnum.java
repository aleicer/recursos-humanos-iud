package constant;

public enum ParentescoEnum {
    PADRE("Padre"),
    MADRE("Madre"),
    HIJO("Hijo"),
    HIJA("Hija"),
    CONYUGE("Conyuge"),
    OTRO("Otro");

    private final String parentesco;

    private ParentescoEnum(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getParentesco() {
        return parentesco;
    }
}
