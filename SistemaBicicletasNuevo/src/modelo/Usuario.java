
package modelo;

/**
 * Representa a un usuario dentro del sistema de préstamos de bicicletas.
 * Cada usuario tiene un identificador único, un nombre y un estado que
 * indica si actualmente tiene un préstamo activo.
 */
public class Usuario {
    private String id;
    private String nombre;
    private boolean tienePrestamo;

    /**
     * Crea un nuevo usuario con el ID y nombre proporcionados.
     * Por defecto, el usuario no tiene un préstamo activo.
     *
     * @param id     Identificador único del usuario.
     * @param nombre Nombre del usuario.
     */
    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.tienePrestamo = false;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return Identificador único del usuario.
     */
    public String getId() { return id; }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getNombre() { return nombre; }

    /**
     * Indica si el usuario tiene un préstamo activo.
     *
     * @return {@code true} si tiene un préstamo activo, de lo contrario {@code false}.
     */
    public boolean tienePrestamo() { return tienePrestamo; }

    /**
     * Establece el estado del préstamo del usuario.
     *
     * @param tienePrestamo {@code true} si el usuario tiene un préstamo activo, {@code false} si no.
     */
    public void setTienePrestamo(boolean tienePrestamo) { this.tienePrestamo = tienePrestamo; }
}