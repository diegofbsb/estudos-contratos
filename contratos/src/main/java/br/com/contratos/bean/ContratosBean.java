package br.com.contratos.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.contratos.Contratos;
import br.com.contratos.rn.ContratosRn;
import br.com.contratos.web.ContextoBean;
import br.com.contratos.web.util.ContextoUtil;

@ManagedBean(name = "ContratosBean")
@RequestScoped
public class ContratosBean {

	private List<Contratos> listacontratos;

	private Contratos selecionada = new Contratos();

	public Contratos contratostela = new Contratos();

	public String novo() {
		this.contratostela = new Contratos();
		return "contatos_novo";
	}

	@PostConstruct
	public List<Contratos> getLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		ContratosRn contratosRn = new ContratosRn();
		try {
			if (this.listacontratos == null) {
				this.listacontratos = contratosRn.listar();
			}
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return this.listacontratos;
	}

	public void salvar() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.contratostela.setUsuario(contextoBean.getUsuarioLogado());
		ContratosRn contratosRn = new ContratosRn();
		contratosRn.salvar(contratostela);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("contratos_novo.jsf");
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Campos obrigatórios!"));
			e.printStackTrace();
		}
	}

	public void update() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.selecionada.setUsuario(contextoBean.getUsuarioLogado());
		selecionada.setId(selecionada.getId());
		ContratosRn contratosRn = new ContratosRn();
		contratosRn.update(selecionada);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("contratos_lista_e_atualiza.jsf");
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Campos obrigatórios!"));
			e.printStackTrace();
		}
	}

	public void excluir() throws IOException {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.selecionada.setUsuario(contextoBean.getUsuarioLogado());
		ContratosRn contratosRn = new ContratosRn();
		contratosRn.excluir(this.selecionada);
		this.selecionada = new Contratos();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("contratos_lista_e_atualiza.jsf");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Dados não encontrados!"));
			e.printStackTrace();
		}
	}

	public void selecionar() throws IOException {
		try {
			ContratosRn contratosRn = new ContratosRn();
			contratosRn.carregar(this.selecionada);
			// System.out.println(selecionada.getNumero_contrato());
			// System.out.println(selecionada.getData_contrato());
			// System.out.println(selecionada.getValor_contrato());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("contratos_lista_e_atualiza.jsf");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Dados não encontrados!"));
			e.printStackTrace();
		}
	}

	public Contratos getContratostela() {
		return contratostela;
	}

	public void setContratostela(Contratos contratostela) {
		this.contratostela = contratostela;
	}

	public Contratos getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Contratos selecionada) {
		this.selecionada = selecionada;
	}

}
