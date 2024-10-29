package controller;

import domain.InformacionAcademica;
import data.InformacionAcademicaDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InformacionAcademicaController {
    private final List<InformacionAcademica> informacionAcademica = new ArrayList<>();
    private final InformacionAcademicaDao informacionAcademicaDao = new InformacionAcademicaDao();

    public InformacionAcademica crearInformacionAcademica(InformacionAcademica informacionAcademica) throws SQLException {
        InformacionAcademica informacionAcademicaCreada = informacionAcademicaDao.crearInformacionAcademica(informacionAcademica);
        informacionAcademica.setIdEstudio(informacionAcademicaCreada.getIdEstudio());
        return informacionAcademica;
    }

    public InformacionAcademica actualizarInformacionAcademica(int id, InformacionAcademica informacionAcademicaActualizada) throws SQLException {
        InformacionAcademica actualizarInformacionAcademica = informacionAcademicaDao.actualizarInformacionAcademica(id, informacionAcademicaActualizada);
        for (int i = 0; i < informacionAcademica.size(); i++) {
            if (informacionAcademica.get(i).getIdEstudio() == id) {
                informacionAcademica.set(i, actualizarInformacionAcademica);
                return informacionAcademicaActualizada;
            }
        }
        return null;
    }

    public List<InformacionAcademica> listarInformacionAcademica() throws SQLException {
        if (informacionAcademica.isEmpty()) {
            informacionAcademica.addAll(informacionAcademicaDao.obtenerInformacionAcademica());
        }
        return informacionAcademica;
    }

    public InformacionAcademica obtenerInformacionAcademicaPorId(int id) throws SQLException {
        if (informacionAcademica.isEmpty()) {
            informacionAcademica.addAll(informacionAcademicaDao.obtenerInformacionAcademica());
        }
        Optional<InformacionAcademica> newList = informacionAcademica.stream()
                .filter(f -> f.getIdEstudio() == id)
                .findFirst();
        return newList.orElse(null);
    }

    public List<InformacionAcademica> obtenerInformacionAcademicaPorIdFuncionario(int idFuncionario) throws SQLException {
        return informacionAcademicaDao.obtenerInformacionAcademicaPorIdFuncionario(idFuncionario);
    }

    public boolean eliminarInformacionAcademica(int id) throws SQLException {
        informacionAcademicaDao.eliminarInformacionAcademica(id);
        return informacionAcademica.removeIf(f -> f.getIdEstudio() == id);
    }
}
