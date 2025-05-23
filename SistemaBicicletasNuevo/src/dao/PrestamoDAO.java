
package dao;

import modelo.Prestamo;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que simula el acceso a datos para objetos {@link Prestamo}.
 * Utiliza una lista en memoria como almacenamiento.
 */
public class PrestamoDAO {
    private List<Prestamo> prestamos = new ArrayList<>();

    /**
     * Agrega un nuevo préstamo a la lista.
     *
     * @param prestamo El objeto {@link Prestamo} que se desea registrar.
     */
    public void agregarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    /**
     * Busca un préstamo por su identificador único.
     *
     * @param id El ID del préstamo a buscar.
     * @return El objeto {@link Prestamo} si se encuentra, o {@code null} si no existe.
     */
    public Prestamo buscarPorId(String id) {
        for (Prestamo p : prestamos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Obtiene todos los préstamos realizados por un usuario específico.
     *
     * @param idUsuario El ID del usuario.
     * @return Una lista con los préstamos asociados al usuario.
     */
    public List<Prestamo> obtenerPorUsuario(String idUsuario) {
        List<Prestamo> prestamosUsuario = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.getIdUsuario().equals(idUsuario)) {
                prestamosUsuario.add(p);
            }
        }
        return prestamosUsuario;
    }

    /**
     * Busca si el usuario tiene un préstamo activo (sin devolución registrada).
     *
     * @param idUsuario El ID del usuario.
     * @return El préstamo activo si existe, o {@code null} si no hay uno.
     */
    public Prestamo buscarPrestamoActivo(String idUsuario) {
        for (Prestamo p : prestamos) {
            if (p.getIdUsuario().equals(idUsuario) && p.getFechaDevolucion() == null) {
                return p;
            }
        }
        return null;
    }

    /**
     * Devuelve una lista con todos los préstamos registrados.
     *
     * @return Una nueva lista con todos los préstamos almacenados.
     */
    public List<Prestamo> obtenerTodos() {
        return new ArrayList<>(prestamos);
    }
}