package br.com.contratos.cheque;

import java.util.List;

import br.com.contratos.conta.Conta;

public interface ChequeDAO {
	public void salvar(Cheque cheque);

	public void excluir(Cheque cheque);

	public Cheque carregar(ChequeID chequeId);

	public List<Cheque> lista(Conta conta);
}
