package data;

import constant.NivelEstudioEnum;
import domain.InformacionAcademica;
import util.DataBaseInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InformacionAcademicaDao {
    private static final String GET_INFORMACION_ACADEMICA = "SELECT * FROM informacion_academica";
    private static final String GET_INFORMACION_ACADEMICA_BY_ID = "SELECT * FROM informacion_academica WHERE id_estudio = ?";
    private static final String GET_INFORMACION_ACADEMICA_BY_ID_FUNCIONARIO = "SELECT * FROM informacion_academica WHERE id_funcionario = ?";
    private static final String CREATE_INFORMACION_ACADEMICA = "INSERT INTO informacion_academica (id_funcionario, universidad, nivel_estudio, titulo) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_INFORMACION_ACADEMICA = "UPDATE informacion_academica SET id_funcionario = ?, universidad = ?, nivel_estudio = ?, titulo = ? WHERE id_estudio = ?";
    private static final String DELETE_INFORMACION_ACADEMICA = "DELETE FROM informacion_academica WHERE id_estudio = ?";

    public List<InformacionAcademica> obtenerInformacionAcademica() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<InformacionAcademica> informacionAcademica = new ArrayList<>();
        try {
            connection = DataBaseInfo.getConnection();
            preparedStatement = connection.prepareStatement(GET_INFORMACION_ACADEMICA);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                InformacionAcademica informacion = new InformacionAcademica();
                informacion.setIdEstudio(resultSet.getInt("id_estudio"));
                informacion.setIdFuncionario(resultSet.getInt("id_funcionario"));
                informacion.setUniversidad(resultSet.getString("universidad"));
                informacion.setNivelEstudio(NivelEstudioEnum.fromString(resultSet.getString("nivel_estudio")));
                informacion.setTitulo(resultSet.getString("titulo"));
                informacionAcademica.add(informacion);
            }
            return informacionAcademica;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public InformacionAcademica obtenerInformacionAcademicaPorId(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DataBaseInfo.getConnection();
            preparedStatement = connection.prepareStatement(GET_INFORMACION_ACADEMICA_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                InformacionAcademica informacion = new InformacionAcademica();
                informacion.setIdEstudio(resultSet.getInt("id_estudio"));
                informacion.setIdFuncionario(resultSet.getInt("id_funcionario"));
                informacion.setUniversidad(resultSet.getString("universidad"));
                informacion.setNivelEstudio(NivelEstudioEnum.fromString(resultSet.getString("nivel_estudio")));
                informacion.setTitulo(resultSet.getString("titulo"));
                return informacion;
            }
            return null;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public List<InformacionAcademica> obtenerInformacionAcademicaPorIdFuncionario(int idFuncionario) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<InformacionAcademica> informacionAcademica = new ArrayList<>();
        try {
            connection = DataBaseInfo.getConnection();
            preparedStatement = connection.prepareStatement(GET_INFORMACION_ACADEMICA_BY_ID_FUNCIONARIO);
            preparedStatement.setInt(1, idFuncionario);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                InformacionAcademica informacion = new InformacionAcademica();
                informacion.setIdEstudio(resultSet.getInt("id_estudio"));
                informacion.setIdFuncionario(resultSet.getInt("id_funcionario"));
                informacion.setUniversidad(resultSet.getString("universidad"));
                informacion.setNivelEstudio(NivelEstudioEnum.fromString(resultSet.getString("nivel_estudio")));
                informacion.setTitulo(resultSet.getString("titulo"));
                informacionAcademica.add(informacion);
            }
            return informacionAcademica;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public InformacionAcademica crearInformacionAcademica(InformacionAcademica informacion) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DataBaseInfo.getConnection();
            preparedStatement = connection.prepareCall(CREATE_INFORMACION_ACADEMICA);
            preparedStatement.setInt(1, informacion.getIdFuncionario());
            preparedStatement.setString(2, informacion.getUniversidad());
            preparedStatement.setString(3, informacion.getNivelEstudio().toString());
            preparedStatement.setString(4, informacion.getTitulo());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("SELECT MAX(id_estudio) FROM informacion_academica");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                informacion.setIdEstudio(resultSet.getInt(1));
            }
            return informacion;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public InformacionAcademica actualizarInformacionAcademica(int id, InformacionAcademica informacion) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataBaseInfo.getConnection();
            preparedStatement = connection.prepareCall(UPDATE_INFORMACION_ACADEMICA);
            preparedStatement.setInt(1, informacion.getIdFuncionario());
            preparedStatement.setString(2, informacion.getUniversidad());
            preparedStatement.setString(3, informacion.getNivelEstudio().toString());
            preparedStatement.setString(4, informacion.getTitulo());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            return informacion;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void eliminarInformacionAcademica(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataBaseInfo.getConnection();
            preparedStatement = connection.prepareCall(DELETE_INFORMACION_ACADEMICA);
            preparedStatement.setInt(1, id);
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
