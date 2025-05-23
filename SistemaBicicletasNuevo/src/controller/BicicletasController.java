
package controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import dao.BicicletaDAO;
import dao.PrestamoDAO;
import dao.UsuarioDAO;
import modelo.Bicicleta;
import modelo.Prestamo;
import modelo.Usuario;
import servicio.BicicletaService;
import servicio.PrestamoService;
import servicio.UsuarioService;
import ui.AppBicicletas;

/**
 * Controlador principal de la aplicación de préstamo de bicicletas.
 * Maneja la interacción entre la interfaz gráfica (vista) y la lógica de negocio (servicios).
 * 
 * Se encarga de:
 * 
 * - Registrar bicicletas y usuarios
 * - Consultar información
 * - Realizar y registrar préstamos y devoluciones
 * - Mostrar historial de préstamos
 * 
 * @author 
 */
public class BicicletasController {
    // Servicios que manejan la lógica de negocio
    private BicicletaService bicicletaService;
    private UsuarioService usuarioService;
    private PrestamoService prestamoService;

    private AppBicicletas view = new AppBicicletas();
    
    /**
     * Constructor principal que inicializa servicios, DAOs, vista y listeners.
     */
    public BicicletasController(){
        view.setVisible(true);
        // Inicializar DAOs y servicios
        BicicletaDAO bicicletaDAO = new BicicletaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        
        bicicletaService = new BicicletaService(bicicletaDAO);
        usuarioService = new UsuarioService(usuarioDAO);
        prestamoService = new PrestamoService(prestamoDAO, usuarioDAO, bicicletaDAO);

        view.btnConsultarBici.addActionListener(e -> consultarBicicleta());
        view.btnBicisDisponibles.addActionListener(e -> mostrarBicicletasDisponibles());
        view.btnConsultarUsr.addActionListener(e -> consultarUsuario());
        view.btnHistorial.addActionListener(e -> mostrarHistorialUsuario());

        for(int i = 0; i<view.boton.length; i++){
            switch (i) {
                case 0:
                    view.boton[i].addActionListener(e -> registrarBicicleta());
                    break;
                case 1:
                    view.boton[i].addActionListener(e -> registrarUsuario());
                    break;
                case 2:
                    view.boton[i].addActionListener(e -> realizarPrestamo());
                    break;
                case 3:
                    view.boton[i].addActionListener(e -> registrarDevolucion());
                    break;
            }
        }

        cargarDatosEjemplo();
    }

    /**
     * Carga un conjunto de bicicletas y usuarios de ejemplo al iniciar la aplicación.
     */
    private void cargarDatosEjemplo() {
        // Bicicletas de ejemplo
        bicicletaService.registrarBicicleta("B001", "Montaña");
        bicicletaService.registrarBicicleta("B002", "Urbana");
        bicicletaService.registrarBicicleta("B003", "Carrera");
        
        // Usuarios de ejemplo
        usuarioService.registrarUsuario("2225851", "Jonathan Morales");
        usuarioService.registrarUsuario("U002", "Erick Vargas");
        usuarioService.registrarUsuario("U003", "Angel Reyes");
    }
    
    /*
     * Consulta los datos de una bicicleta con base en el ID ingresado
     * y los muestra en los campos de texto correspondientes.
     */
    private void consultarBicicleta() {
        String id = view.txtIdBicicleta.getText().trim();
        
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                "Por favor ingrese un ID de bicicleta", 
                "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Bicicleta bici = bicicletaService.buscarBicicleta(id);
        
        if (bici != null) {
            view.txtModeloBicicleta.setText(bici.getModelo());
            view.txtEstadoBicicleta.setText(bici.isDisponible() ? "Disponible" : "Prestada");
        } else {
            JOptionPane.showMessageDialog(view, 
                "Bicicleta no encontrada", 
                "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCamposBicicleta();
        }
    }
    
    /**
     * Muestra en un área de texto todas las bicicletas que están disponibles actualmente.
     */
    private void mostrarBicicletasDisponibles() {
        List<Bicicleta> disponibles = bicicletaService.obtenerBicicletasDisponibles();
        StringBuilder sb = new StringBuilder("BICICLETAS DISPONIBLES:\n");
        
        if (disponibles.isEmpty()) {
            sb.append("No hay bicicletas disponibles en este momento.");
        } else {
            for (Bicicleta b : disponibles) {
                sb.append("- ID: ").append(b.getId()).append(", Modelo: ").append(b.getModelo()).append("\n");
            }
        }
        
        view.txtHistorial.setText(sb.toString());
    }

    /**
     * Consulta los datos de un usuario, su estado de préstamo y la bicicleta actual, si aplica.
     */
    private void consultarUsuario() {
        String id = view.txtIdUsuario.getText().trim();
        
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                "Por favor ingrese un ID de usuario", 
                "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Usuario usuario = usuarioService.buscarUsuario(id);
        
        if (usuario != null) {
            view.txtNombreUsuario.setText(usuario.getNombre());
            view.txtEstadoUsuario.setText(usuario.tienePrestamo() ? "Con préstamo" : "Sin préstamo");
            
            if (usuario.tienePrestamo()) {
                Prestamo prestamo = prestamoService.buscarPrestamoActivo(id);
                if (prestamo != null) {
                    Bicicleta bici = bicicletaService.buscarBicicleta(prestamo.getIdBicicleta());
                    view.txtBicicletaUsuario.setText(bici != null ? bici.getId() + " - " + bici.getModelo() : "No encontrada");
                }
            } else {
                view.txtBicicletaUsuario.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(view, 
                "Usuario no encontrado", 
                "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCamposUsuario();
        }
    }
    
    /*
     * Muestra el historial de préstamos realizados por un usuario,
     * incluyendo fechas y datos de la bicicleta prestada.
     */
    private void mostrarHistorialUsuario() {
        String idUsuario = view.txtIdUsuario.getText().trim();
        
        if (idUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                "Por favor ingrese un ID de usuario", 
                "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);
        if (usuario == null) {
            JOptionPane.showMessageDialog(view, 
                "Usuario no encontrado", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        List<Prestamo> historial = prestamoService.obtenerHistorialUsuario(idUsuario);
        
        if (historial.isEmpty()) {
            view.txtHistorial.setText("El usuario no tiene préstamos registrados.");
            return;
        }
        
        StringBuilder sb = new StringBuilder("HISTORIAL DE PRÉSTAMOS PARA " + usuario.getNombre() + ":\n\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        for (Prestamo p : historial) {
            Bicicleta bici = bicicletaService.buscarBicicleta(p.getIdBicicleta());
            
            sb.append("ID Préstamo: ").append(p.getId()).append("\n");
            sb.append("Bicicleta: ").append(bici.getModelo()).append(" (ID: ").append(bici.getId()).append(")\n");
            sb.append("Fecha préstamo: ").append(p.getFechaPrestamo().format(formatter)).append("\n");
            
            if (p.getFechaDevolucion() != null) {
                sb.append("Fecha devolución: ").append(p.getFechaDevolucion().format(formatter)).append("\n");
            } else {
                sb.append("(Préstamo activo)\n");
            }
            
            sb.append("----------------------------------------\n");
        }
        
        view.txtHistorial.setText(sb.toString());
    }

    /**
     * Solicita al usuario el ID y modelo de una nueva bicicleta y la registra.
     */
    private void registrarBicicleta() {
        String id = JOptionPane.showInputDialog(view, "Ingrese ID de la bicicleta:");
        if (id == null || id.trim().isEmpty()) return;
        
        String modelo = JOptionPane.showInputDialog(view, "Ingrese modelo de la bicicleta:");
        if (modelo == null || modelo.trim().isEmpty()) return;
        
        bicicletaService.registrarBicicleta(id, modelo);
        JOptionPane.showMessageDialog(view, "Bicicleta registrada exitosamente");
    }
    
    /**
     * Solicita al usuario el ID y nombre de un nuevo usuario y lo registra.
     */
    private void registrarUsuario() {
        String id = JOptionPane.showInputDialog(view, "Ingrese ID del usuario:");
        if (id == null || id.trim().isEmpty()) return;
        
        String nombre = JOptionPane.showInputDialog(view, "Ingrese nombre del usuario:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        
        usuarioService.registrarUsuario(id, nombre);
        JOptionPane.showMessageDialog(view, "Usuario registrado exitosamente");
    }
    
    /**
     * Realiza un préstamo de bicicleta para un usuario si cumple las condiciones.
     */
    private void realizarPrestamo() {
        String idUsuario = JOptionPane.showInputDialog(view, "Ingrese ID del usuario:");
        if (idUsuario == null || idUsuario.trim().isEmpty()) return;
        
        String idBicicleta = JOptionPane.showInputDialog(view, "Ingrese ID de la bicicleta:");
        if (idBicicleta == null || idBicicleta.trim().isEmpty()) return;
        
        String idPrestamo = "P" + (1000 + (int)(Math.random() * 9000));
        
        boolean exito = prestamoService.realizarPrestamo(idPrestamo, idUsuario, idBicicleta);
        
        if (exito) {
            JOptionPane.showMessageDialog(view, 
                "Préstamo realizado exitosamente\n" +
                "ID de préstamo: " + idPrestamo, 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, 
                "No se pudo realizar el préstamo. Verifique:\n" +
                "- Que el usuario existe y no tiene préstamos activos\n" +
                "- Que la bicicleta existe y está disponible", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Registra la devolución de una bicicleta si el préstamo está activo.
     */
    private void registrarDevolucion() {
        String idPrestamo = JOptionPane.showInputDialog(view, "Ingrese ID del préstamo:");
        if (idPrestamo == null || idPrestamo.trim().isEmpty()) return;
        
        boolean exito = prestamoService.registrarDevolucion(idPrestamo);
        
        if (exito) {
            JOptionPane.showMessageDialog(view, 
                "Devolución registrada exitosamente", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, 
                "No se pudo registrar la devolución. Verifique:\n" +
                "- Que el ID del préstamo es correcto\n" +
                "- Que el préstamo no ha sido devuelto previamente", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Limpia los campos de texto relacionados con una bicicleta.
     */
    private void limpiarCamposBicicleta() {
        view.txtModeloBicicleta.setText("");
        view.txtEstadoBicicleta.setText("");
    }
    
    /**
     * Limpia los campos de texto relacionados con un usuario.
     */
    private void limpiarCamposUsuario() {
        view.txtNombreUsuario.setText("");
        view.txtEstadoUsuario.setText("");
        view.txtBicicletaUsuario.setText("");
    }
}
