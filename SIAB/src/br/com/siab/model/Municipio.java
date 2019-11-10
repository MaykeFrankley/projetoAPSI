package br.com.siab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="municipios")
public class Municipio {

	@Id
	private int codMunicipio;

	@Column
	private String nome;

	@Column
	private String uf;

	public Municipio() {
		// TODO Auto-generated constructor stub
	}

	public int getCodMunicipio() {
		return codMunicipio;
	}

	public void setCodMunicipio(int codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}



}
