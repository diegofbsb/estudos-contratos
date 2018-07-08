package br.com.contratos.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.contratos.entidade.Entidade;
import br.com.contratos.entidade.EntidadeRN;
import br.com.contratos.web.util.ContextoUtil;

@ManagedBean(name = "entidadeBean")
@RequestScoped
public class EntidadeBean {
	private Entidade entidade = new Entidade();
	private List<Entidade> lista;

	public void novo() {
		this.entidade = new Entidade();
	}
	
	public void salvar() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.entidade.setUsuario(contextoBean.getUsuarioLogado());
		EntidadeRN entidadeRN = new EntidadeRN();
		entidadeRN.salvar(this.entidade);
		this.entidade = new Entidade();
		this.lista = null;
	}

	public void editar() { }
	
	public void excluir() {
		EntidadeRN entidadeRN = new EntidadeRN();
		entidadeRN.excluir(this.entidade);
		this.entidade = new Entidade();
		this.lista = null;
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

	public List<Entidade> getLista() {
		if (this.lista == null) {
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			EntidadeRN entidadeRN = new EntidadeRN();
			this.lista = entidadeRN.listar(contextoBean.getUsuarioLogado());
		}
		return this.lista;
	}

	public List<Entidade> listaFiltrada(String consulta) {
		List<Entidade> listaFiltrada = new ArrayList<Entidade>();
		for (Entidade entidade : this.getLista()) {
			if (entidade.getNome().toLowerCase().startsWith(consulta)) {
				listaFiltrada.add(entidade);
			}
		}
		return listaFiltrada;
	}
}
