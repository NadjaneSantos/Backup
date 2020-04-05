package controle;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entidade.Usuario;
import util.JPAUtil;

/**
 * @author Nadjane LoginBean: Classe responsável pelo gerenciamento do login.
 */

@ManagedBean(name = "LoginBean")
@RequestScoped
public class LoginBean {

	private String usarioAdmin = "admin";
	private String senhaAdmin = "admin";

	private String usuarioInpt;
	private String senhaInpt;

	private static final String PESQUISAR = "paginas/usuario/pesquisarUsuario.xhtml";
	private UsuarioDAO usuarioDAO;
	private String mensagem;

	public LoginBean() {
		this.usuarioDAO = new UsuarioDAOImpl(JPAUtil.getEntityManager());
	}

	// Método entrar: Verifica as credenciais do usuário no sistema.
	// Usuario e senha Admin para quando o banco estiver vazio.

	public void entrar() throws IOException {

		if (this.usuarioInpt.equals(this.usarioAdmin) && this.senhaInpt.equals(this.senhaAdmin)) {

			FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);

		} else {

			Usuario usuarioPesquisa = this.usuarioDAO.pesquisar(this.usuarioInpt);

			if (usuarioPesquisa != null) {

				if (usuarioPesquisa.getSenha().equals(this.senhaInpt)) {

					FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);

				} else {

					this.mensagem = "Usuário ou senha inválido.";

				}

			}
		}
	}

	public String getUsuarioInpt() {
		return usuarioInpt;
	}

	public void setUsuarioInpt(String usuarioInpt) {
		this.usuarioInpt = usuarioInpt;
	}

	public String getSenhaInpt() {
		return senhaInpt;
	}

	public void setSenhaInpt(String senhaInpt) {
		this.senhaInpt = senhaInpt;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getUsarioAdmin() {
		return usarioAdmin;
	}

	public void setUsarioAdmin(String usarioAdmin) {
		this.usarioAdmin = usarioAdmin;
	}

	public String getSenhaAdmin() {
		return senhaAdmin;
	}

	public void setSenhaAdmin(String senhaAdmin) {
		this.senhaAdmin = senhaAdmin;
	}

}
