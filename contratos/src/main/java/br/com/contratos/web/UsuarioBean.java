package br.com.contratos.web;

import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.util.DigestUtils;

import br.com.contratos.conta.Conta;
import br.com.contratos.conta.ContaRN;
import br.com.contratos.usuario.Usuario;
import br.com.contratos.usuario.UsuarioRN;
import br.com.contratos.util.RNException;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destinoSalvar;
	private String senhaCriptografada;
	private Conta conta = new Conta();

	public String novo() {
		this.destinoSalvar = "usuarioSucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "usuario";
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String senha = this.usuario.getSenha();
		if (senha != null && senha.trim().length() > 0 && !senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;
		}
		if (senha != null && senha.trim().length() == 0) {
			this.usuario.setSenha(this.senhaCriptografada);
		} else {
			String senhaCripto = DigestUtils.md5DigestAsHex(senha.getBytes());
			this.usuario.setSenha(senhaCripto);
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		if (this.conta.getDescricao() != null) {
			this.conta.setUsuario(this.usuario);
			this.conta.setFavorita(true);
			ContaRN contaRN = new ContaRN();
			contaRN.salvar(this.conta);
		}
		if (this.destinoSalvar.equals("usuarioSucesso")) {
			try {
				Usuario auxiliar = this.usuario;
				auxiliar.setSenha(senha);
				usuarioRN.enviarEmailPosCadastramento(this.usuario);
			} catch (RNException e) {
				FacesMessage facesMessage = new FacesMessage(
						"Não foi possível enviar o e-mail de cadastro do usuário. Erro: " + e.getMessage());
				context.addMessage(null, facesMessage);
				return null;
			}
		}
		return this.destinoSalvar;
	}

	public String editar() {
		this.senhaCriptografada = this.usuario.getSenha();
		return "/publico/usuario";
	}

	public String ativar() {
		if (this.usuario.isAtivo()) {
			this.usuario.setAtivo(false);
		} else {
			this.usuario.setAtivo(true);
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		return null;
	}

	public String excluir() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista = null;
		return null;
	}

	public String atribuirPermissao(Usuario usuario, String permissao) {
		this.usuario = usuario;
		Set<String> permissoes = this.usuario.getPermissao();
		if (permissoes.contains(permissao)) {
			permissoes.remove(permissao);
		} else {
			permissoes.add(permissao);
		}
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public List<Usuario> getLista() {
		if (this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public String getSenhaCriptografada() {
		return senhaCriptografada;
	}

	public void setSenhaCriptografada(String senhaCriptografada) {
		this.senhaCriptografada = senhaCriptografada;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
}
