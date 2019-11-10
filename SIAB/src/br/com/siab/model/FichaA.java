package br.com.siab.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class FichaA {

	@EmbeddedId
	private FichaA_ID id;

	@Column
	private int segmento;

	@Column
	private Date dt_cadastro;

	@Column
	private String endereco;

	@Column
	private int numero;

	@Column
	private String bairro;

	@Column
	private int cep;

	public FichaA() {
		// TODO Auto-generated constructor stub
	}

	public FichaA_ID getId() {
		return id;
	}

	public void setId(FichaA_ID id) {
		this.id = id;
	}

	public int getSegmento() {
		return segmento;
	}

	public void setSegmento(int segmento) {
		this.segmento = segmento;
	}

	public Date getDt_cadastro() {
		return dt_cadastro;
	}

	public void setDt_cadastro(Date dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}



}
