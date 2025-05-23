
package main;

import javax.swing.SwingUtilities;

import controller.BicicletasController;

/**
 * Clase principal que inicia la aplicación de préstamo de bicicletas.
 * 
 * Esta clase contiene el método `main`, que es el punto de entrada de la aplicación.
 * Se encarga de crear una instancia del controlador {@link BicicletasController},
 * lo que a su vez inicializa la interfaz gráfica y los servicios necesarios.
 * 
 * @author 
 */
public class Main {

    /**
     * Método principal que lanza la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BicicletasController();
            }
        });
    }
}
