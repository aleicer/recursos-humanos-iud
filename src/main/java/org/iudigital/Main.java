package org.iudigital;

import constant.EstadoCivilEnum;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        EstadoCivilEnum estadoCivil = EstadoCivilEnum.SOLTERO;
        System.out.println(estadoCivil.getEstadoCivil());
    }
}