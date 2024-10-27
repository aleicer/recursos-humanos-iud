package constant;

public enum NivelEstudioEnum {
    TECNICO("Tecnico"),
    TECNOLOGO("Tecnologo"),
    PROFESIONAL("Profesional"),
    ESPECIALIZACION("Especializacion"),
    MAESTRIA("Maestria"),
    DOCTORADO("Doctorado");

    private final String nivelEstudio;

    private NivelEstudioEnum(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }
}
