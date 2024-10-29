package domain;

import java.time.LocalDate;
import constant.ParentescoEnum;

public class GrupoFamiliar {
    private int idFamiliar;
    private int idFuncionario;
    private String nombre;
    private ParentescoEnum parentesco;
    private LocalDate fechaNacimiento;

    public GrupoFamiliar(int idFamiliar, int idFuncionario, String nombre, ParentescoEnum parentesco, LocalDate fechaNacimiento) {
        this.idFamiliar = idFamiliar;
        this.idFuncionario = idFuncionario;
        this.nombre = nombre;
        this.parentesco = parentesco;
        this.fechaNacimiento = fechaNacimiento;
    }

    public GrupoFamiliar() {
    }

    public int getIdFamiliar() {
        return idFamiliar;
    }

    public void setIdFamiliar(int idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int funcionario) {
        this.idFuncionario = funcionario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ParentescoEnum getParentesco() {
        return parentesco;
    }

    public void setParentesco(ParentescoEnum parentesco) {
        this.parentesco = parentesco;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
