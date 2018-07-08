package br.com.contratos.cheque;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.contratos.conta.Conta;

public class ChequeDAOHibernate implements ChequeDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Cheque cheque) {
		this.session.saveOrUpdate(cheque);
	}

	@Override
	public void excluir(Cheque cheque) {
		this.session.delete(cheque);
	}

	@Override
	public Cheque carregar(ChequeID chequeId) {
		return (Cheque) this.session.get(Cheque.class, chequeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cheque> lista(Conta conta) {
		Criteria criteria = this.session.createCriteria(Cheque.class);
		criteria.add(Restrictions.eq("conta", conta));
		return criteria.list();
	}
}
