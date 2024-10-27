package controller;
import domain.Funcionario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioController {
    private final List<Funcionario> funcionarios = new ArrayList<>();

    public Funcionario crearFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        return funcionario;
    }

    public Funcionario actualizarFuncionario(int id, Funcionario funcionarioActualizado) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getIdFuncionario() == id) {
                funcionarios.set(i, funcionarioActualizado);
                return funcionarioActualizado;
            }
        }
        return null;
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarios;
    }

    public Funcionario obtenerFuncionarioPorId(int id) {
        Optional<Funcionario> funcionario = funcionarios.stream()
                .filter(f -> f.getIdFuncionario() == id)
                .findFirst();
        return funcionario.orElse(null);
    }

    public boolean eliminarFuncionario(int id) {
        return funcionarios.removeIf(f -> f.getIdFuncionario() == id);
    }
}