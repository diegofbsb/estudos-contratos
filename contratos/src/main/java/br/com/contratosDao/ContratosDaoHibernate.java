package br.com.contratosDao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.contratos.Contratos;

public class ContratosDaoHibernate implements ContratosDao{
	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Contratos contratos) {
		this.session.saveOrUpdate(contratos);
	}

	public void excluir(Contratos contratos) {
		this.session.delete(contratos);
	}

	@Override
	public void update(Contratos contratos) {
		this.session.update(contratos);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Contratos> listar() {
		Criteria criteria = this.session.createCriteria(Contratos.class);
		return criteria.list();
	}

	@Override
	public Contratos carregar(Contratos contratos) {
		return (Contratos) this.session.get(Contratos.class, contratos.getId());
	}



}
