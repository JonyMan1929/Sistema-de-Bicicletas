
package modelo;

import java.time.LocalDateTime;

/**
 * Representa un préstamo de una bicicleta por parte de un usuario.
 * Contiene información sobre el usuario, la bicicleta prestada,
 * la fecha del préstamo y, opcionalmente, la fecha de devolución.
 */
public class Prestamo {
    private String id;
    private String idUsuario;
    private String idBicicleta;
    private LocalDateTime fechaPrestamo;
    private LocalDateTime fechaDevolucion;

    /**
     * Crea un nuevo préstamo con la información del usuario y la bicicleta prestada.
     * La fecha del préstamo se asigna automáticamente con la hora actual.
     *
     * @param id           Identificador único del préstamo.
     * @param idUsuario    ID del usuario que realiza el préstamo.
     * @param idBicicleta  ID de la bicicleta prestada.
     */
    public Prestamo(String id, String idUsuario, String idBicicleta) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idBicicleta = idBicicleta;
        this.fechaPrestamo = LocalDateTime.now();
    }

    /**
     * Obtiene el identificador del préstamo.
     *
     * @return ID del préstamo.
     */
    public String getId() { return id; }

    /**
     * Obtiene el ID del usuario que realizó el préstamo.
     *
     * @return ID del usuario.
     */
    public String getIdUsuario() { return idUsuario; }

    /**
     * Obtiene el ID de la bicicleta prestada.
     *
     * @return ID de la bicicleta.
     */
    public String getIdBicicleta() { return idBicicleta; }

    /**
     * Obtiene la fecha y hora en que se realizó el préstamo.
     *
     * @return Fecha del préstamo.
     */
    public LocalDateTime getFechaPrestamo() { return fechaPrestamo; }

    /**
     * Obtiene la fecha y hora en que se devolvió la bicicleta.
     * Si aún no se ha devuelto, devuelve {@code null}.
     *
     * @return Fecha de devolución o {@code null} si no se ha devuelto.
     */
    public LocalDateTime getFechaDevolucion() { return fechaDevolucion; }

    /**
     * Establece la fecha y hora de devolución de la bicicleta.
     *
     * @param fechaDevolucion Fecha de devolución a establecer.
     */
    public void setFechaDevolucion(LocalDateTime fechaDevolucion) { 
        this.fechaDevolucion = fechaDevolucion; 
    }
}