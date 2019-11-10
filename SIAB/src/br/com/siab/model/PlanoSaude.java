package br.com.siab.model;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Table(name="planosSaude")
public class PlanoSaude {

	@EmbeddedId
	private FichaA_ID id;

	@Column(name="nomePlanoDeSaude")
	private String nomePlano;

	@Column
	private int numeroPessoa;

	public PlanoSaude() {
		// TODO Auto-generated constructor stub
	}

	public FichaA_ID getId() {
		return id;
	}

	public void setId(FichaA_ID id) {
		this.id = id;
	}

	public String getNomePlano() {
		return nomePlano;
	}

	public void setNomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
	}

	public int getNumeroPessoa() {
		return numeroPessoa;
	}

	public void setNumeroPessoa(int numeroPessoa) {
		this.numeroPessoa = numeroPessoa;
	}



}
