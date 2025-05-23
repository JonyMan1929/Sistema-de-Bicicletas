
package dao;

import modelo.Bicicleta;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que simula el acceso a datos para objetos {@link Bicicleta}.
 * Utiliza una lista en memoria como fuente de almacenamiento.
 */
public class BicicletaDAO {
    private List<Bicicleta> bicicletas = new ArrayList<>();

    /**
     * Agrega una nueva bicicleta al sistema.
     *
     * @param bicicleta La instancia de {@link Bicicleta} que se desea agregar.
     */
    public void agregarBicicleta(Bicicleta bicicleta) {
        bicicletas.add(bicicleta);
    }

    /**
     * Busca una bicicleta por su ID único.
     *
     * @param id El identificador único de la bicicleta.
     * @return La bicicleta encontrada o {@code null} si no existe.
     */
    public Bicicleta buscarPorId(String id) {
        for (Bicicleta b : bicicletas) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Obtiene todas las bicicletas registradas en el sistema.
     *
     * @return Una lista con todas las bicicletas.
     */
    public List<Bicicleta> obtenerTodas() {
        return new ArrayList<>(bicicletas);
    }

    /**
     * Filtra y devuelve solo las bicicletas que están disponibles para préstamo.
     *
     * @return Una lista con las bicicletas disponibles.
     */
    public List<Bicicleta> obtenerDisponibles() {
        List<Bicicleta> disponibles = new ArrayList<>();
        for (Bicicleta b : bicicletas) {
            if (b.isDisponible()) {
                disponibles.add(b);
            }
        }
        return disponibles;
    }
}