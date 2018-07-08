package br.com.contratos.rn;

import java.util.List;

import br.com.contratos.Contratos;
import br.com.contratos.usuario.Usuario;
import br.com.contratos.util.DAOFactory;
import br.com.contratosDao.ContratosDao;

public class ContratosRn {

	private ContratosDao contratosDao;

	public ContratosRn() {
		this.contratosDao = DAOFactory.criarContratoDAO();
	}

	public Contratos carregar(Contratos contratos) {
		return contratosDao.carregar(contratos);
	}

	public void salvar(Contratos contratos) {
		this.contratosDao.salvar(contratos);
	}

	public void update(Contratos contratos) {
		this.contratosDao.update(contratos);
	}

	public void excluir(Contratos contratos) {
		this.contratosDao.excluir(contratos);
	}

	public List<Contratos> listar() {
		return this.contratosDao.listar();
	}
	//
	// public Conta buscarFavorita(Usuario usuario) {
	// return this.contratosDao.buscarFavorita(usuario);
	// }
	//
	// public void tornarFavorita(Conta contaFavorita) {
	// Conta conta = this.buscarFavorita(contaFavorita.getUsuario());
	// if (conta != null) {
	// conta.setFavorita(false);
	// this.contratosDao.salvar(conta);
	// }
	// contaFavorita.setFavorita(true);
	// this.contratosDao.salvar(contaFavorita);
	// }
}
