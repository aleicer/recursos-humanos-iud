package data;

import constant.ParentescoEnum;
import domain.GrupoFamiliar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import util.DataBaseInfo;

public class GrupoFamiliarDao {
    private static final String GET_GRUPO_FAMILIAR = "select * from grupo_familiar";
    private static final String CREATE_GRUPO_FAMILIAR = "insert into grupo_familiar (id_funcionario, nombre, parentesco, fecha_nacimiento) values (?, ?, ?, ?)";
    private static final String GET_GRUPO_FAMILIAR_BY_ID = "select * from grupo_familiar where id_familiar = ? ";
    private static final String GET_GRUPO_FAMILIAR_BY_ID_FUNCIONARIO = "select * from grupo_familiar where id_funcionario = ? ";
    private static final String UPDATE_GRUPO_FAMILIAR = "update grupo_familiar set id_funcionario = ?, set nombre = ?, set parentesco = ?, set fecha_nacimiento = ? where id_familiar = ?";
    private static final String DELETE_GRUPO_FAMILIAR = "delete from grupo_familiar where id_familiar = ? ";

    public List<GrupoFamiliar> obtenerGruposFamiliares() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<GrupoFamiliar> grupoFamiliarList = new ArrayList<>();

        try {
            connection = DataBaseInfo.getConnection();
            preparedStatement = connection.prepareStatement(GET_GRUPO_FAMILIAR);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GrupoFamiliar grupoFamiliar = new GrupoFamiliar();
                grupoFamiliar.setIdFamiliar(resultSet.getInt("id_familiar"));
                grupoFamiliar.setIdFuncionario(resultSet.getInt("id_funcionario"));
                grupoFamiliar.setNombre(resultSet.getString("nombre"));
                grupoFamiliar.setParentesco(ParentescoEnum.fromString(resultSet.getString("parentesco")));
                grupoFamiliar.setFechaNacimiento(LocalDate.parse(resultSet.getString("fecha_nacimiento")));
                grupoFamiliarList.add(grupoFamiliar);
            }
            return grupoFamiliarList;

        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        }
    }

    public GrupoFamiliar crearGrupoFamiliar(GrupoFamiliar grupoFamiliar) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DataBaseInfo.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_GRUPO_FAMILIAR);
            preparedStatement.setInt(1, grupoFamiliar.getIdFuncionario());
            preparedStatement.setString(2, grupoFamiliar.getNombre());
            preparedStatement.setString(3, grupoFamiliar.getParentesco().toString());
            preparedStatement.setString(4, grupoFamiliar.getFechaNacimiento().toString());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                grupoFamiliar.setIdFamiliar(resultSet.getInt(1));
            }
            return grupoFamiliar;
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        }
    }

    public GrupoFamiliar obtenerGrupoFamiliarPorId(int idFamiliar) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        GrupoFamiliar grupoFamiliar = null;

        try {
            connection = DataBaseInfo.getConnection();
            preparedStatement = connection.prepareStatement(GET_GRUPO_FAMILIAR_BY_ID);
            preparedStatement.setInt(1, idFamiliar);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                grupoFamiliar = new GrupoFamiliar();
                grupoFamiliar.setIdFamiliar(resultSet.getInt("id_familiar"));
                grupoFamiliar.setIdFuncionario(resultSet.getInt("id_funcionario"));
                grupoFamiliar.setNombre(resultSet.getString("nombre"));
                grupoFamiliar.setParentesco(ParentescoEnum.fromString(resultSet.getString("parentesco")));
                grupoFamiliar.setFechaNacimiento(LocalDate.parse(resultSet.getString("fecha_nacimiento")));
            }
            return grupoFamiliar;
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        }
    }

    public List<GrupoFamiliar> obtenerGrupoFamiliarPorIdFuncionario(int idFuncionario) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<GrupoFamiliar> grupoFamiliarList = new ArrayList<>();

        try {
            connection = DataBaseInfo.getConnection();
            preparedStatement = connection.prepareStatement(GET_GRUPO_FAMILIAR_BY_ID_FUNCIONARIO);
            preparedStatement.setInt(1, idFuncionario);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GrupoFamiliar grupoFamiliar = new GrupoFamiliar();
                grupoFamiliar.setIdFamiliar(resultSet.getInt("id_familiar"));
                grupoFamiliar.setIdFuncionario(resultSet.getInt("id_funcionario"));
                grupoFamiliar.setNombre(resultSet.getString("nombre"));
                grupoFamiliar.setParentesco(ParentescoEnum.fromString(resultSet.getString("parentesco")));
                grupoFamiliar.setFechaNacimiento(LocalDate.parse(resultSet.getString("fecha_nacimiento")));
                grupoFamiliarList.add(grupoFamiliar);
            }
            return grupoFamiliarList;
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        }
    }

    public GrupoFamiliar actualizarGrupoFamiliar(int id, GrupoFamiliar grupoFamiliar) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DataBaseInfo.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_GRUPO_FAMILIAR);
            preparedStatement.setInt(1, grupoFamiliar.getIdFuncionario());
            preparedStatement.setString(2, grupoFamiliar.getNombre());
            preparedStatement.setString(3, grupoFamiliar.getParentesco().toString());
            preparedStatement.setString(4, grupoFamiliar.getFechaNacimiento().toString());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            return grupoFamiliar;
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        }
    }

    public void eliminarGrupoFamiliar(int idFamiliar) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataBaseInfo.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_GRUPO_FAMILIAR);
            preparedStatement.setInt(1, idFamiliar);
            preparedStatement.executeUpdate();
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        }
    }
}
