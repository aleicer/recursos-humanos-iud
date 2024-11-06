package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import constant.EstadoCivilEnum;
import constant.TipoIdentificacionEnum;
import controller.FuncionarioController;
import domain.Funcionario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PresentacionUI {
    private JPanel mainContainer;
    private JTabbedPane opciones;
    private JPanel funcionarioPanel;
    private JPanel grupoFamiliarPanel;
    private JPanel infoAcademicaPanel;
    private JTextField fechaNacimiento;
    private JTextField telefono;
    private JTextField direccion;
    private JTextField numeroIdentificacion;
    private JTextField apellidos;
    private JTextField nombres;
    private JComboBox tipoIdentificacion;
    private JComboBox estadoCivil;
    private JComboBox sexo;
    private JTable tablaUsuarios;
    private JButton crearFuncionario;
    private JButton cancelar;
    private JTextField identificadorBusqueda;
    private JComboBox tipoDeBusqueda;
    private JButton buscar;
    private JButton eliminar;
    private JLabel usuarioSeleccionado;
    private JTextField idUsuario;

    private FuncionarioController funcionarioController;

    public PresentacionUI() {
        this.funcionarioController = new FuncionarioController();
        cargarDatosTabla();
        crearFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Crear")) {
                    crearFuncionario();
                } else {
                    actualizarFuncionario();
                }
            }
        });
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarFuncionario();
            }
        });
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarFuncionario();
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("PresentacionUI");
        frame.setContentPane(new PresentacionUI().mainContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1000, 700);
        frame.setVisible(true);

    }

    private void createUIComponents() {
        cargarDatosTabla();
    }

    private void cargarDatosTabla() {
    try {
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        if (funcionarios.isEmpty()) {
            return;
        }
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8; // Hacer editable solo la columna del botón
            }
        };
        model.addColumn("ID");
        model.addColumn("Nombres");
        model.addColumn("Apellidos");
        model.addColumn("Documento");
        model.addColumn("Sexo");
        model.addColumn("Dirección");
        model.addColumn("Teléfono");
        model.addColumn("Fecha de Nacimiento");
        model.setRowCount(0);
        for (Funcionario funcionario : funcionarios) {
            model.addRow(new Object[]{
                    funcionario.getIdFuncionario(),
                    funcionario.getNombres(),
                    funcionario.getApellidos(),
                    funcionario.getNumeroIdentificacion(),
                    funcionario.getSexo(),
                    funcionario.getDireccion(),
                    funcionario.getTelefono(),
                    funcionario.getFechaNacimiento(),
            });
        }
        tablaUsuarios.setModel(model);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private void crearFuncionario () {
        String sexoFormato = this.sexo.getSelectedItem().toString().equals("Masculino") ? "M" : "F";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNacimientoFormato;
        try {
            fechaNacimientoFormato = LocalDate.parse(fechaNacimiento.getText(), formatter);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no es válida");
            return;
        }
        if (fechaNacimientoFormato.isAfter(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede ser mayor a la fecha actual");
            return;
        }
        String documento = numeroIdentificacion.getText();
        String nombres = this.nombres.getText();
        String apellidos = this.apellidos.getText();
        String direccion = this.direccion.getText();
        String telefono = this.telefono.getText();
        if (documento.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            return;
        }
        Funcionario funcionario = new Funcionario();
        funcionario.setTipoIdentificacion(TipoIdentificacionEnum.fromStringWord(tipoIdentificacion.getSelectedItem().toString()));
        funcionario.setNumeroIdentificacion(documento);
        funcionario.setNombres(nombres);
        funcionario.setApellidos(apellidos);
        funcionario.setEstadoCivil(EstadoCivilEnum.fromString(estadoCivil.getSelectedItem().toString()));
        funcionario.setSexo(sexoFormato);
        funcionario.setDireccion(direccion);
        funcionario.setTelefono(telefono);
        funcionario.setFechaNacimiento(fechaNacimientoFormato);
        try {
            funcionarioController.crearFuncionario(funcionario);
            limpiarCampos();
            usuarioSeleccionado.setText("Usuario Creado");
            usuarioSeleccionado.setForeground(new java.awt.Color(33, 187, 16));
            cargarDatosTabla();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void buscarFuncionario() {
        String tipoBusqueda = tipoDeBusqueda.getSelectedItem().toString();
        String identificador = identificadorBusqueda.getText();
        if (identificador.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de búsqueda no puede estar vacío");
            return;
        }
        try {
            Funcionario funcionario = null;
            if (tipoBusqueda.equals("ID")) {
                funcionario = funcionarioController.obtenerFuncionarioPorId(Integer.parseInt(identificador));
            } else if (tipoBusqueda.equals("CC")) {
                funcionario = funcionarioController.obtenerFuncionarioPorDocumento(identificador);
            }
            if (funcionario == null) {
                JOptionPane.showMessageDialog(null, "No se encontró ningún funcionario con el identificador ingresado");
                return;
            }
            numeroIdentificacion.setText(funcionario.getNumeroIdentificacion());
            nombres.setText(funcionario.getNombres());
            apellidos.setText(funcionario.getApellidos());
            direccion.setText(funcionario.getDireccion());
            telefono.setText(funcionario.getTelefono());
            fechaNacimiento.setText(funcionario.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            tipoIdentificacion.setSelectedItem(funcionario.getTipoIdentificacion().getValue());
            estadoCivil.setSelectedItem(funcionario.getEstadoCivil().getValue());
            sexo.setSelectedItem(funcionario.getSexo().equals("M") ? "Masculino" : "Femenino");
            usuarioSeleccionado.setText("Usuario: " + funcionario.getNombres() + " " + funcionario.getApellidos());
            usuarioSeleccionado.setForeground(new java.awt.Color(46, 183, 135));
            idUsuario.setText(String.valueOf(funcionario.getIdFuncionario()));
            crearFuncionario.setText("Actualizar");
            crearFuncionario.setActionCommand("Actualizar");
            crearFuncionario.setBackground(new java.awt.Color(255, 255, 0));
            crearFuncionario.setForeground(new java.awt.Color(0, 0, 0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void actualizarFuncionario () {
        String sexoFormato = this.sexo.getSelectedItem().toString().equals("Masculino") ? "M" : "F";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNacimientoFormato;
        try {
            fechaNacimientoFormato = LocalDate.parse(fechaNacimiento.getText(), formatter);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no es válida");
            return;
        }
        if (fechaNacimientoFormato.isAfter(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede ser mayor a la fecha actual");
            return;
        }
        String documento = numeroIdentificacion.getText();
        String nombres = this.nombres.getText();
        String apellidos = this.apellidos.getText();
        String direccion = this.direccion.getText();
        String telefono = this.telefono.getText();
        int id = Integer.parseInt(idUsuario.getText());
        if (documento.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            return;
        }
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(id);
        funcionario.setTipoIdentificacion(TipoIdentificacionEnum.fromStringWord(tipoIdentificacion.getSelectedItem().toString()));
        funcionario.setNumeroIdentificacion(documento);
        funcionario.setNombres(nombres);
        funcionario.setApellidos(apellidos);
        funcionario.setEstadoCivil(EstadoCivilEnum.fromString(estadoCivil.getSelectedItem().toString()));
        funcionario.setSexo(sexoFormato);
        funcionario.setDireccion(direccion);
        funcionario.setTelefono(telefono);
        funcionario.setFechaNacimiento(fechaNacimientoFormato);
        try {
            funcionarioController.actualizarFuncionario(id, funcionario);
            limpiarCampos();
            usuarioSeleccionado.setText("Usuario Actualizado");
            usuarioSeleccionado.setForeground(new java.awt.Color(255, 255, 0));
            cargarDatosTabla();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        numeroIdentificacion.setText("");
        nombres.setText("");
        apellidos.setText("");
        direccion.setText("");
        telefono.setText("");
        fechaNacimiento.setText("");
        tipoIdentificacion.setSelectedIndex(0);
        estadoCivil.setSelectedIndex(0);
        sexo.setSelectedIndex(0);
        idUsuario.setText("");
        crearFuncionario.setText("Crear");
        crearFuncionario.setActionCommand("Crear");
        crearFuncionario.setBackground(new java.awt.Color(33, 187, 16));
    }

    private void eliminarFuncionario () {
        int id = Integer.parseInt(idUsuario.getText());
        try {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este registro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                funcionarioController.eliminarFuncionario(id);
                limpiarCampos();
                usuarioSeleccionado.setText("Usuario Eliminado");
                usuarioSeleccionado.setForeground(new java.awt.Color(225, 59, 59));
            }
            cargarDatosTabla();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
