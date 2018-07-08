package br.com.contratos.categoria;

import java.util.List;

import br.com.contratos.usuario.Usuario;

public interface CategoriaDAO {
	public Categoria salvar(Categoria categoria);

	public void excluir(Categoria categoria);

	public Categoria carregar(int categoria);

	public List<Categoria> listar(Usuario usuario);
}
