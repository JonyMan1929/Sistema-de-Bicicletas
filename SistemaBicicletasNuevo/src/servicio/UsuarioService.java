
package servicio;

import dao.UsuarioDAO;
import modelo.Usuario;
import java.util.List;

/**
 * Servicio que gestiona la lógica relacionada con los usuarios
 * del sistema de gestión de bicicletas comunitarias.
 * 
 * Esta clase actúa como intermediario entre la interfaz de usuario (UI)
 * y el acceso a los datos (DAO).
 */
public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    /**
     * Constructor de UsuarioService.
     * 
     * @param usuarioDAO El DAO encargado del acceso a los datos de usuarios.
     */
    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * 
     * @param id     El identificador único del usuario.
     * @param nombre El nombre completo del usuario.
     */
    public void registrarUsuario(String id, String nombre) {
        Usuario usuario = new Usuario(id, nombre);
        usuarioDAO.agregarUsuario(usuario);
    }

    /**
     * Busca un usuario por su identificador.
     * 
     * @param id El ID del usuario a buscar.
     * @return El objeto Usuario correspondiente si se encuentra, o null si no existe.
     */
    public Usuario buscarUsuario(String id) {
        return usuarioDAO.buscarPorId(id);
    }

    /**
     * Obtiene una lista con todos los usuarios registrados en el sistema.
     * 
     * @return Una lista de objetos Usuario.
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.obtenerTodos();
    }
}