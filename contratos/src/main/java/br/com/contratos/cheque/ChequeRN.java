package br.com.contratos.cheque;

import java.util.Date;
import java.util.List;

import br.com.contratos.conta.Conta;
import br.com.contratos.lancamento.Lancamento;
import br.com.contratos.util.DAOFactory;
import br.com.contratos.util.RNException;

public class ChequeRN {
	private ChequeDAO chequeDAO;

	public ChequeRN() {
		this.chequeDAO = DAOFactory.criarChequeDAO();
	}

	public void salvar(Cheque cheque) {
		this.chequeDAO.salvar(cheque);
	}

	public int salvarSequencia(Conta conta, Integer chequeInicial, Integer chequeFinal) {
		Cheque cheque = null;
		Cheque chequeVerifica = null;
		ChequeID chequeId = null;
		int contaTotal = 0;
		for (int i = chequeInicial; i <= chequeFinal; i++) {
			chequeId = new ChequeID();
			chequeId.setCheque(i);
			chequeId.setConta(conta.getConta().intValue());
			chequeVerifica = this.carregar(chequeId);
			if (chequeVerifica == null) {
				cheque = new Cheque();
				cheque.setChequeId(chequeId);
				cheque.setSituacao(Cheque.SITUACAO_CHEQUE_NAO_EMITIDO);
				cheque.setDataCadastro(new Date(System.currentTimeMillis()));
				this.salvar(cheque);
				contaTotal++;
			}
		}
		return contaTotal;
	}

	public void excluir(Cheque cheque) throws RNException {
		if (cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_NAO_EMITIDO) {
			this.chequeDAO.excluir(cheque);
		} else {
			throw new RNException("Não é possível excluir cheque, status não permitido para operação.");
		}
	}

	public Cheque carregar(ChequeID chequeId) {
		return this.chequeDAO.carregar(chequeId);
	}

	public List<Cheque> listar(Conta conta) {
		return this.chequeDAO.lista(conta);
	}

	public void cancelarCheque(Cheque cheque) throws RNException {
		if (cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_NAO_EMITIDO
				|| cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_CANCELADO) {
			cheque.setSituacao(Cheque.SITUACAO_CHEQUE_CANCELADO);
			this.chequeDAO.salvar(cheque);
		} else {
			throw new RNException("Não é possível cancelar cheque, status não permitido para operação.");
		}
	}

	public void baixarCheque(ChequeID chequeId, Lancamento lancamento) {
		Cheque cheque = this.carregar(chequeId);
		if (cheque != null) {
			cheque.setSituacao(Cheque.SITUACAO_CHEQUE_BAIXADO);
			cheque.setLancamento(lancamento);
			this.chequeDAO.salvar(cheque);
		}
	}

	public void desvinculaLancamento(Conta conta, Integer numeroCheque) throws RNException {
		ChequeID chequeId = new ChequeID();
		chequeId.setCheque(numeroCheque);
		chequeId.setConta(conta.getConta().intValue());
		Cheque cheque = this.carregar(chequeId);
		if (cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_CANCELADO) {
			throw new RNException("Não é possível usar cheque cancelado.");
		} else {
			cheque.setSituacao(Cheque.SITUACAO_CHEQUE_NAO_EMITIDO);
			cheque.setLancamento(null);
			this.salvar(cheque);
		}
	}
}
