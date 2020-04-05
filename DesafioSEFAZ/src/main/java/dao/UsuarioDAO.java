package dao;

import java.util.List;

import entidade.Usuario;

/**
 * @author Nadjane
 * 
 *         Interface de compartilhamento da chamada dos métodos. Implementados
 *         na classe UsuarioDAOImpl.
 */

public interface UsuarioDAO {

	public boolean inserir(Usuario usuario);

	public void alterar(Usuario usuario);

	public void remover(Usuario usuario);

	public Usuario pesquisar(String email);

	public List<Usuario> listarTodos();

}
