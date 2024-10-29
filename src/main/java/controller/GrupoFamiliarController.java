package controller;

import domain.GrupoFamiliar;
import data.GrupoFamiliarDao;

import java.util.ArrayList;
import java.util.List;

public class GrupoFamiliarController {
    private final GrupoFamiliarDao grupoFamiliarDao = new GrupoFamiliarDao();
    private final List<GrupoFamiliar> grupoFamiliarLista = new ArrayList<>();

    public GrupoFamiliar crearGrupoFamiliar(GrupoFamiliar grupoFamiliar) throws Exception {
        GrupoFamiliar grupoFamiliarCreado = grupoFamiliarDao.crearGrupoFamiliar(grupoFamiliar);
        this.grupoFamiliarLista.add(grupoFamiliarCreado);
        return grupoFamiliar;
    }

    public GrupoFamiliar actualizarGrupoFamiliar(int id, GrupoFamiliar grupoFamiliar) throws Exception {
        GrupoFamiliar grupoFamiliarActualizado = grupoFamiliarDao.actualizarGrupoFamiliar(id, grupoFamiliar);
        for (int i = 0; i < grupoFamiliarLista.size(); i++) {
            if (grupoFamiliarLista.get(i).getIdFamiliar() == id) {
                grupoFamiliarLista.set(i, grupoFamiliarActualizado);
                return grupoFamiliarActualizado;
            }
        }
        return null;
    }

    public List<GrupoFamiliar> listarGruposFamiliares() throws Exception {
        if (grupoFamiliarLista.isEmpty()) {
            grupoFamiliarLista.addAll(grupoFamiliarDao.obtenerGruposFamiliares());
        }
        return grupoFamiliarLista;
    }

    public GrupoFamiliar obtenerGrupoFamiliarPorIdFamiliar(int id) throws Exception {
        if (grupoFamiliarLista.isEmpty()) {
            grupoFamiliarLista.addAll(grupoFamiliarDao.obtenerGruposFamiliares());
        }
        return grupoFamiliarLista.stream()
                .filter(f -> f.getIdFamiliar() == id)
                .findFirst()
                .orElse(null);
    }

    public List<GrupoFamiliar> obtenerGrupoFamiliarPorIdFuncionario(int id) throws Exception {
        return grupoFamiliarDao.obtenerGrupoFamiliarPorIdFuncionario(id);
    }

    public boolean eliminarGrupoFamiliar(int id) throws Exception {
        grupoFamiliarDao.eliminarGrupoFamiliar(id);
        return grupoFamiliarLista.removeIf(f -> f.getIdFamiliar() == id);
    }
}
