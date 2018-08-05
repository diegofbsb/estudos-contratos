package br.com.usuarios;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "usuarios")
public class Usuarios {

	private Long id;
	
	private String nome;
	
	private String email;
	
	private Integer telefone;
	
	private String endereco;
		
}
