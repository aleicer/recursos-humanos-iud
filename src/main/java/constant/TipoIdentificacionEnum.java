package constant;

public enum TipoIdentificacionEnum {
    CEDULA("CC"),
    TARJETA_IDENTIDAD("TI"),
    CEDULA_EXTRANJERIA("CE"),
    NIT("NIT"),
    PASAPORTE("PAS");

    private final String tipoIdentificacion;

    private TipoIdentificacionEnum(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getValue() {
        return tipoIdentificacion;
    }

    public static TipoIdentificacionEnum fromString(String value) {
        switch (value) {
            case "CC":
                return CEDULA;
            case "TI":
                return TARJETA_IDENTIDAD;
            case "CE":
                return CEDULA_EXTRANJERIA;
            case "NIT":
                return NIT;
            case "PAS":
                return PASAPORTE;
        }
        throw new IllegalArgumentException("No enum constant " + TipoIdentificacionEnum.class.getCanonicalName() + "." + value);
    }
}
