package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entidade.Telefone;
import entidade.Usuario;
import util.JPAUtil;

/**
 * @author Nadjane LoginBean: Classe respons�vel pelo gerenciamento do usu�rio
 *         (Inserir, remover, editar e listar todos).
 * 
 */

@ManagedBean(name = "UsuarioBean")
@SessionScoped
public class UsuarioBean {

	private Usuario usuario;
	private Telefone telefone;
	private List<Usuario> listaUsuario;
	private String emailSelecionado;

	private UsuarioDAO usuarioDAO;

	private static final String MANTER = "manterUsuario.xhtml";
	private static final String PESQUISAR = "pesquisarUsuario.xhtml";

	public UsuarioBean() {

		this.usuario = new Usuario();
		this.usuario.setTelefones(new ArrayList<Telefone>());

		this.telefone = new Telefone();
		this.listaUsuario = new ArrayList<Usuario>();

		this.usuarioDAO = new UsuarioDAOImpl(JPAUtil.getEntityManager());

		this.listaUsuario = this.usuarioDAO.listarTodos();

		System.out.println(this.listaUsuario);
	}

	// M�todo listarUsuarios: Recupera a lista de todos os usu�rios.

	public void listarUsuarios() {

		this.listaUsuario = this.usuarioDAO.listarTodos();

	}

	// M�todo salvar: Inseri um usu�rio.

	public void salvar() throws IOException {

		if (this.usuarioDAO.inserir(this.usuario)) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usu�rio cadastrado com sucesso."));

			abrirPerquisarUsuario();

		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao inserir."));
		}

	}

	// M�todo adicionarTelefone: adiciona telefone na lista de telefones do usu�rio.

	public void adicionarTelefone() {

		if (!this.existeTelefone(telefone)) {

			Telefone telefoneNovo = new Telefone();

			telefoneNovo.setDdd(this.telefone.getDdd());
			telefoneNovo.setNumero(this.telefone.getNumero());
			telefoneNovo.setTipo(this.telefone.getTipo());
			telefoneNovo.setUsuario(this.usuario);

			this.usuario.getTelefones().add(telefoneNovo);

			this.telefone = new Telefone();

		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Telefone j� existe."));

		}

	}

	// M�todo existeTelefone: Verifica se o telefone j� existe.

	private boolean existeTelefone(Telefone telefone) {

		boolean retorno = false;

		for (Telefone telLista : this.usuario.getTelefones()) {

			if (telLista.getDdd() == telefone.getDdd() && telLista.getNumero().equals(telefone.getNumero())) {

				retorno = true;
			}
		}

		return retorno;
	}

	// M�todo abrirManterUsuario: Redirecinamento para a p�gina de manter usu�rio.

	public void abrirManterUsuario() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext().redirect(MANTER);
	}

	// M�todo abrirPerquisarUsuario: Redirecinamento para a p�gina de pesquisar
	// usu�rio.

	public void abrirPerquisarUsuario() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);
	}

	// M�todo editar: Altera dado(s) de um usu�rio existe.

	public void editar() throws IOException {

		Usuario usuarioEdicao = this.usuarioDAO.pesquisar(emailSelecionado);
		this.usuario = usuarioEdicao;

		abrirManterUsuario();

	}

	// M�todo remover: Deleta um usu�rio.

	public String remover() {

		Usuario usuarioRemocao = this.usuarioDAO.pesquisar(emailSelecionado);
		this.usuarioDAO.remover(usuarioRemocao);
		this.listaUsuario = this.usuarioDAO.listarTodos();

		return "";
	}

	// M�todo limpar: Limpa todos os campos do formul�rio.

	public void limpar() {

		this.listaUsuario = new ArrayList<Usuario>();
		this.usuario = new Usuario();
		this.usuario.setTelefones(new ArrayList<Telefone>());
		this.telefone = new Telefone();

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String getEmailSelecionado() {
		return emailSelecionado;
	}

	public void setEmailSelecionado(String emailSelecionado) {
		this.emailSelecionado = emailSelecionado;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
