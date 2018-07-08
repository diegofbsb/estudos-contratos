package br.com.contratosDao;

import java.util.List;

import br.com.contratos.Contratos;
import br.com.contratos.usuario.Usuario;

public interface ContratosDao {
	
	public void salvar(Contratos contratos);
	
	public void update(Contratos contratos);

	public void excluir(Contratos contratos);

	public Contratos carregar(Contratos contratos);

	public List<Contratos> listar();
}
