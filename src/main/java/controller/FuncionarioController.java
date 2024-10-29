package controller;
import data.FuncionarioDao;
import domain.Funcionario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioController {
    private final List<Funcionario> funcionarios = new ArrayList<>();
    private final FuncionarioDao funcionarioDao = new FuncionarioDao();

    public Funcionario crearFuncionario(Funcionario funcionario) throws SQLException {
        Funcionario funcionarioCreado = funcionarioDao.guardarFuncionario(funcionario);
        funcionarios.add(funcionarioCreado);
        return funcionario;
    }

    public Funcionario actualizarFuncionario(int id, Funcionario funcionarioActualizado) throws SQLException {
        Funcionario actualizarFuncionario = funcionarioDao.actualizarFuncionario(id, funcionarioActualizado);
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getIdFuncionario() == id) {
                funcionarios.set(i, actualizarFuncionario);
                return funcionarioActualizado;
            }
        }
        return null;
    }

    public List<Funcionario> listarFuncionarios() throws SQLException {
        if (funcionarios.isEmpty()) {
            funcionarios.addAll(funcionarioDao.obtenerFuncionarios());
        }
        return funcionarios;
    }

    public Funcionario obtenerFuncionarioPorId(int id) throws SQLException {
        if (funcionarios.isEmpty()) {
            funcionarios.addAll(funcionarioDao.obtenerFuncionarios());
        }
        Optional<Funcionario> funcionario = funcionarios.stream()
                .filter(f -> f.getIdFuncionario() == id)
                .findFirst();
        return funcionario.orElse(null);
    }

    public boolean eliminarFuncionario(int id) throws SQLException {
        funcionarioDao.eliminarFuncionario(id);
        return funcionarios.removeIf(f -> f.getIdFuncionario() == id);
    }
}