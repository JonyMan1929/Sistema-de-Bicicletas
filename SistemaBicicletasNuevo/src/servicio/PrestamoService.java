
package servicio;

import dao.BicicletaDAO;
import dao.PrestamoDAO;
import dao.UsuarioDAO;
import modelo.Prestamo;
import modelo.Bicicleta;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio encargado de gestionar los préstamos y devoluciones
 * de bicicletas en el sistema.
 *
 * Esta clase centraliza la lógica de negocio relacionada con los préstamos,
 * validando usuarios, bicicletas y el estado de cada transacción.
 */
public class PrestamoService {
    private PrestamoDAO prestamoDAO;
    private UsuarioDAO usuarioDAO;
    private BicicletaDAO bicicletaDAO;

    /**
     * Constructor que inicializa las dependencias de acceso a datos.
     *
     * @param prestamoDAO   DAO para acceder y modificar préstamos.
     * @param usuarioDAO    DAO para consultar o actualizar usuarios.
     * @param bicicletaDAO  DAO para consultar o actualizar bicicletas.
     */
    public PrestamoService(PrestamoDAO prestamoDAO, UsuarioDAO usuarioDAO, BicicletaDAO bicicletaDAO) {
        this.prestamoDAO = prestamoDAO;
        this.usuarioDAO = usuarioDAO;
        this.bicicletaDAO = bicicletaDAO;
    }

    /**
     * Realiza un nuevo préstamo si se cumplen todas las condiciones:
     * - El usuario existe.
     * - La bicicleta existe y está disponible.
     * - El usuario no tiene un préstamo activo.
     *
     * @param idPrestamo  ID único para el préstamo.
     * @param idUsuario   ID del usuario que solicita el préstamo.
     * @param idBicicleta ID de la bicicleta a prestar.
     * @return true si el préstamo se realizó correctamente, false en caso contrario.
     */
    public boolean realizarPrestamo(String idPrestamo, String idUsuario, String idBicicleta) {
        // Validar que el usuario existe
        if (usuarioDAO.buscarPorId(idUsuario) == null) {
            return false;
        }
        
        // Validar que la bicicleta existe y está disponible
        Bicicleta bicicleta = bicicletaDAO.buscarPorId(idBicicleta);
        if (bicicleta == null || !bicicleta.isDisponible()) {
            return false;
        }
        
        // Validar que el usuario no tiene préstamo activo
        if (prestamoDAO.buscarPrestamoActivo(idUsuario) != null) {
            return false;
        }
        
        // Realizar el préstamo
        Prestamo prestamo = new Prestamo(idPrestamo, idUsuario, idBicicleta);
        prestamoDAO.agregarPrestamo(prestamo);
        
        // Actualizar estados
        bicicleta.setDisponible(false);
        usuarioDAO.buscarPorId(idUsuario).setTienePrestamo(true);
        
        return true;
    }

    /**
     * Registra la devolución de una bicicleta previamente prestada.
     * Si el préstamo no existe o ya fue devuelto, no realiza cambios.
     *
     * @param idPrestamo ID del préstamo a cerrar.
     * @return true si la devolución se registró con éxito, false si no aplica.
     */
    public boolean registrarDevolucion(String idPrestamo) {
        Prestamo prestamo = prestamoDAO.buscarPorId(idPrestamo);
        if (prestamo == null || prestamo.getFechaDevolucion() != null) {
            return false;
        }
        
        // Registrar devolución
        prestamo.setFechaDevolucion(LocalDateTime.now());
        
        // Actualizar estados
        bicicletaDAO.buscarPorId(prestamo.getIdBicicleta()).setDisponible(true);
        usuarioDAO.buscarPorId(prestamo.getIdUsuario()).setTienePrestamo(false);
        
        return true;
    }
    
    /**
     * Obtiene el historial de todos los préstamos realizados por un usuario.
     *
     * @param idUsuario ID del usuario a consultar.
     * @return Lista de objetos Prestamo asociados al usuario.
     */
    public List<Prestamo> obtenerHistorialUsuario(String idUsuario) {
        return prestamoDAO.obtenerPorUsuario(idUsuario);
    }

    /**
     * Busca el préstamo activo actual (si existe) de un usuario.
     *
     * @param idUsuario ID del usuario.
     * @return Objeto Prestamo si hay uno activo, o null si no lo hay.
     */
    public Prestamo buscarPrestamoActivo(String idUsuario) {
        for (Prestamo p : prestamoDAO.obtenerTodos()) {
            if (p.getIdUsuario().equals(idUsuario) && p.getFechaDevolucion() == null) {
                return p;
            }
        }
        return null;
    }
}