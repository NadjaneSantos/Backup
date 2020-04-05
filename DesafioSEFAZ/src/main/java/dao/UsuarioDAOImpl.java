package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidade.Usuario;

/**
 * @author Nadjane Implementação da interface UsuarioDAO.
 */

public class UsuarioDAOImpl implements UsuarioDAO {

	private EntityManager em;

	public UsuarioDAOImpl(EntityManager em) {
		this.em = em;
	}

	// Método inserir: Recebe o usuário e salva-o no banco.

	public boolean inserir(Usuario usuario) {

		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(usuario);
		et.commit();

		return true;

	}

	// Método alterar: Recebe o usuário e salva-o no banco alterando o usuário existente.

	public void alterar(Usuario usuario) {

		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(usuario);
		et.commit();

	}

	// Método remover: Remove o usuário.

	public void remover(Usuario usuario) {

		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(usuario);
		et.commit();

	}

	// Método pesquisar: Pesquisa o usuário pela chave primária (email).

	public Usuario pesquisar(String email) {

		Usuario usuario = em.find(Usuario.class, email);

		return usuario;

	}

	// Método ListarTodos: Consulta o usuário e retorna uma lista de usuários.

	public List<Usuario> listarTodos() {

		Query query = em.createQuery("from Usuario u");

		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = query.getResultList();

		return usuarios;

	}

}
