
package dao;

import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que simula el acceso a datos para objetos de tipo {@link Usuario}.
 * Utiliza una lista en memoria como fuente de almacenamiento.
 */
public class UsuarioDAO {
    private List<Usuario> usuarios = new ArrayList<>();

    /**
     * Agrega un nuevo usuario a la lista.
     *
     * @param usuario El objeto {@link Usuario} que se desea agregar.
     */
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Busca un usuario en la lista según su identificador único.
     *
     * @param id El ID del usuario a buscar.
     * @return El objeto {@link Usuario} si se encuentra, o {@code null} si no existe.
     */
    public Usuario buscarPorId(String id) {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Obtiene una lista con todos los usuarios registrados.
     *
     * @return Una nueva lista que contiene todos los usuarios.
     */
    public List<Usuario> obtenerTodos() {
        return new ArrayList<>(usuarios);
    }
}