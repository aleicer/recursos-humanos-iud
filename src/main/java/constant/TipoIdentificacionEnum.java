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

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }
}
