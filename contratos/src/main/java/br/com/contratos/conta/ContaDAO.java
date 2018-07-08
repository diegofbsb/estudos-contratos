package br.com.contratos.conta;

import java.util.List;

import br.com.contratos.usuario.Usuario;

public interface ContaDAO {
	public void salvar(Conta conta);

	public void excluir(Conta conta);

	public Conta carregar(int codigo);

	public List<Conta> listar(Usuario usuario);

	public Conta buscarFavorita(Usuario usuario);
}
