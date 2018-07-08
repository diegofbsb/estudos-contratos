package br.com.contratos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.sun.istack.NotNull;

import br.com.contratos.usuario.Usuario;

@Entity
@Table(name = "contratos")
public class Contratos implements Serializable {

	@Id
	@GeneratedValue
	Long id;

	@Column
	@NotNull
	Long numero_contrato;
	
	@Column
	@NotNull
	String tipo_servico_contrato;
	
	@Column
	@NotNull
	@Temporal(TemporalType.DATE)
	Date data_contrato;
	
	@Column
	@NotNull
	double valor_contrato;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "cod_usuario", nullable = false)
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero_contrato() {
		return numero_contrato;
	}

	public void setNumero_contrato(Long numero_contrato) {
		this.numero_contrato = numero_contrato;
	}

	public String getTipo_servico_contrato() {
		return tipo_servico_contrato;
	}

	public void setTipo_servico_contrato(String tipo_servico_contrato) {
		this.tipo_servico_contrato = tipo_servico_contrato;
	}

	public Date getData_contrato() {
		return data_contrato;
	}

	public void setData_contrato(Date data_contrato) {
		this.data_contrato = data_contrato;
	}

	public double getValor_contrato() {
		return valor_contrato;
	}

	public void setValor_contrato(double valor_contrato) {
		this.valor_contrato = valor_contrato;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



}
