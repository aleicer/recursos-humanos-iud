package constant;

public enum NivelEstudioEnum {
    TECNICO("Técnico"),
    TECNOLOGO("Tecnológico"),
    PROFESIONAL("Profesional"),
    ESPECIALIZACION("Especialización"),
    MAESTRIA("Maestría"),
    DOCTORADO("Doctorado");

    private final String nivelEstudio;

    private NivelEstudioEnum(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getvalue() {
        return nivelEstudio;
    }

    public static NivelEstudioEnum fromString(String value) {
        switch (value) {
            case "Técnico":
                return TECNICO;
            case "Tecnológico":
                return TECNOLOGO;
            case "Profesional":
                return PROFESIONAL;
            case "Especialización":
                return ESPECIALIZACION;
            case "Maestría":
                return MAESTRIA;
            case "Doctorado":
                return DOCTORADO;
        }
        throw new IllegalArgumentException("No enum constant " + NivelEstudioEnum.class.getCanonicalName() + "." + value);
    }
}
