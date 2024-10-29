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

    public String getValue(ParentescoEnum parentescoEnum) {
        return parentesco;
    }

    public static ParentescoEnum fromString(String value) {
        switch (value) {
            case "Padre":
                return PADRE;
            case "Madre":
                return MADRE;
            case "Hijo":
                return HIJO;
            case "Hija":
                return HIJA;
            case "Conyuge":
                return CONYUGE;
            case "Otro":
                return OTRO;
        }
        throw new IllegalArgumentException("No enum constant " + ParentescoEnum.class.getCanonicalName() + "." + value);
    }
}
