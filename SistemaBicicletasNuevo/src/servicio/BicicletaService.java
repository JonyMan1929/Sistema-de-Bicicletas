
package servicio;

import dao.BicicletaDAO;
import modelo.Bicicleta;
import java.util.List;

/**
 * Servicio encargado de gestionar las operaciones relacionadas
 * con las bicicletas del sistema, tales como el registro,
 * búsqueda y consulta de disponibilidad.
 */
public class BicicletaService {
    private BicicletaDAO bicicletaDAO;

    /**
     * Constructor que recibe la instancia del DAO de bicicletas.
     *
     * @param bicicletaDAO DAO responsable del acceso a datos de bicicletas.
     */
    public BicicletaService(BicicletaDAO bicicletaDAO) {
        this.bicicletaDAO = bicicletaDAO;
    }

    /**
     * Registra una nueva bicicleta en el sistema.
     *
     * @param id     Identificador único de la bicicleta.
     * @param modelo Modelo o descripción de la bicicleta.
     */
    public void registrarBicicleta(String id, String modelo) {
        Bicicleta bicicleta = new Bicicleta(id, modelo);
        bicicletaDAO.agregarBicicleta(bicicleta);
    }

    /**
     * Busca una bicicleta por su identificador único.
     *
     * @param id ID de la bicicleta a buscar.
     * @return La bicicleta correspondiente al ID, o null si no existe.
     */
    public Bicicleta buscarBicicleta(String id) {
        return bicicletaDAO.buscarPorId(id);
    }

    /**
     * Obtiene una lista de todas las bicicletas que están disponibles
     * para préstamo en el sistema.
     *
     * @return Lista de bicicletas disponibles.
     */
    public List<Bicicleta> obtenerBicicletasDisponibles() {
        return bicicletaDAO.obtenerDisponibles();
    }

    /**
     * Retorna todas las bicicletas registradas, estén o no disponibles.
     *
     * @return Lista completa de bicicletas en el sistema.
     */
    public List<Bicicleta> obtenerTodasLasBicicletas() {
        return bicicletaDAO.obtenerTodas();
    }
}