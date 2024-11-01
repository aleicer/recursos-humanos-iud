package constant;

public enum EstadoCivilEnum {
    SOLTERO("Soltero"),
    CASADO("Casado"),
    DIVORCIADO("Divorciado"),
    VIUDO("Viudo");

    private final String estadoCivil;

    private EstadoCivilEnum(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public static EstadoCivilEnum fromString(String value) {
        switch (value) {
            case "Soltero":
                return SOLTERO;
            case "Casado":
                return CASADO;
            case "Divorciado":
                return DIVORCIADO;
            case "Viudo":
                return VIUDO;
        }
        throw new IllegalArgumentException("No enum constant " + EstadoCivilEnum.class.getCanonicalName() + "." + value);
    }

//    public static EstadoCivilEnum fromStringWord(String value) {
//        switch (value) {
//            case "Soltero":
//                return SOLTERO;
//            case "Casado":
//                return CASADO;
//            case "Divorciado":
//                return DIVORCIADO;
//            case "Viudo":
//                return VIUDO;
//        }
//        throw new IllegalArgumentException("No enum constant " + EstadoCivilEnum.class.getCanonicalName() + "." + value);
//    }

    public String getValue() {
        return estadoCivil;
    }
}
