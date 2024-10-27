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

    public String getEstadoCivil() {
        return estadoCivil;
    }
}
