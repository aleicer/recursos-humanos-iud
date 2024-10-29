package domain;

import constant.NivelEstudioEnum;

public class InformacionAcademica {
    private int idEstudio;
    private int idFuncionario;
    private String universidad;
    private NivelEstudioEnum nivelEstudio;
    private String titulo;

    public InformacionAcademica(int idInformacionAcademica, int idFuncionario, String universidad, NivelEstudioEnum nivelEstudio, String titulo) {
        this.idEstudio = idInformacionAcademica;
        this.idFuncionario = idFuncionario;
        this.universidad = universidad;
        this.nivelEstudio = nivelEstudio;
        this.titulo = titulo;
    }

    public InformacionAcademica() {
    }

    public int getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(int idInformacionAcademica) {
        this.idEstudio = idInformacionAcademica;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int funcionario) {
        this.idFuncionario = funcionario;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public NivelEstudioEnum getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(NivelEstudioEnum nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
