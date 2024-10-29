package org.iudigital;
import constant.EstadoCivilEnum;
import constant.NivelEstudioEnum;
import constant.TipoIdentificacionEnum;
import controller.FuncionarioController;
import controller.*;
import domain.*;

import java.sql.SQLException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        InformacionAcademicaController informacionAcademicaController = new InformacionAcademicaController();
        InformacionAcademica informacionAcademica = informacionAcademicaController.obtenerInformacionAcademicaPorId(1, "idFuncionario");
        System.out.println(informacionAcademica.getTitulo());
    }
}