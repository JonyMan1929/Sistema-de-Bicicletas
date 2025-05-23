
package modelo;

/**
 * Representa una bicicleta que puede ser prestada dentro del sistema.
 * Contiene información sobre su identificador, modelo y estado de disponibilidad.
 */
public class Bicicleta {
    private String id;
    private String modelo;
    private boolean disponible;

    /**
     * Crea una nueva bicicleta con el identificador y modelo especificados.
     * Por defecto, la bicicleta se marca como disponible.
     *
     * @param id      Identificador único de la bicicleta.
     * @param modelo  Modelo o descripción de la bicicleta.
     */
    public Bicicleta(String id, String modelo) {
        this.id = id;
        this.modelo = modelo;
        this.disponible = true;
    }

    /**
     * Obtiene el identificador único de la bicicleta.
     *
     * @return ID de la bicicleta.
     */
    public String getId() { return id; }

    /**
     * Obtiene el modelo o descripción de la bicicleta.
     *
     * @return Modelo de la bicicleta.
     */
    public String getModelo() { return modelo; }

    /**
     * Indica si la bicicleta está actualmente disponible para préstamo.
     *
     * @return {@code true} si está disponible, {@code false} en caso contrario.
     */
    public boolean isDisponible() { return disponible; }

    /**
     * Establece el estado de disponibilidad de la bicicleta.
     *
     * @param disponible {@code true} para marcar como disponible, {@code false} para no disponible.
     */
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}