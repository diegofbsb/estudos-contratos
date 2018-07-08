package br.com.contratos.bolsa.acao;

import java.util.List;

import br.com.contratos.usuario.Usuario;

public interface AcaoDAO {
	public void salvar(Acao acao);

	public void excluir(Acao acao);

	public Acao carregar(String codigo);

	public List<Acao> listar(Usuario codigo);
}
