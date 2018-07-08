package br.com.contratos.util;

import br.com.contratos.bolsa.acao.AcaoDAO;
import br.com.contratos.bolsa.acao.AcaoDAOHibernate;
import br.com.contratos.categoria.CategoriaDAO;
import br.com.contratos.categoria.CategoriaDAOHibernate;
import br.com.contratos.cheque.ChequeDAO;
import br.com.contratos.cheque.ChequeDAOHibernate;
import br.com.contratos.conta.ContaDAO;
import br.com.contratos.conta.ContaDAOHibernate;
import br.com.contratos.entidade.EntidadeDAO;
import br.com.contratos.entidade.EntidadeDAOHibernate;
import br.com.contratos.lancamento.LancamentoDAO;
import br.com.contratos.lancamento.LancamentoDAOHibernate;
import br.com.contratos.usuario.UsuarioDAO;
import br.com.contratos.usuario.UsuarioDAOHibernate;
import br.com.contratosDao.ContratosDao;
import br.com.contratosDao.ContratosDaoHibernate;

public class DAOFactory {
	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	
	public static ContaDAO criarContaDAO() {
		ContaDAOHibernate contaDAO = new ContaDAOHibernate();
		contaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return contaDAO;
	}
	
	public static ContratosDao criarContratoDAO() {
		ContratosDaoHibernate contratoDAO = new ContratosDaoHibernate();
		contratoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return (ContratosDao) contratoDAO;
	}

	public static CategoriaDAO criarCategoriaDAO() {
		CategoriaDAOHibernate categoriaDAO = new CategoriaDAOHibernate();
		categoriaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return categoriaDAO;
	}

	public static EntidadeDAO criarEntidadeDAO() {
		EntidadeDAOHibernate entidadeDAO = new EntidadeDAOHibernate();
		entidadeDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return entidadeDAO;
	}

	public static LancamentoDAO criarLancamentoDAO() {
		LancamentoDAOHibernate lancamentoDAO = new LancamentoDAOHibernate();
		lancamentoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return lancamentoDAO;
	}

	public static ChequeDAO criarChequeDAO() {
		ChequeDAOHibernate chequeDAO = new ChequeDAOHibernate();
		chequeDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return chequeDAO;
	}

	public static AcaoDAO criarAcaoDAO() {
		AcaoDAOHibernate acaoDAO = new AcaoDAOHibernate();
		acaoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return acaoDAO;
	}
}
