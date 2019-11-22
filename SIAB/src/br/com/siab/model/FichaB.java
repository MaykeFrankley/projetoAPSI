package br.com.siab.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class FichaB {

	@EmbeddedId
	private FichaA_ID id;

	@Column
	private int anoCorrente;

	@Column
	private String nomeAgente;

	public FichaB() {
		// TODO Auto-generated constructor stub
	}

	public FichaA_ID getId() {
		return id;
	}

	public void setId(FichaA_ID id) {
		this.id = id;
	}

	public int getAnoCorrente() {
		return anoCorrente;
	}

	public void setAnoCorrente(int anoCorrente) {
		this.anoCorrente = anoCorrente;
	}

	public String getNomeAgente() {
		return nomeAgente;
	}

	public void setNomeAgente(String nomeAgente) {
		this.nomeAgente = nomeAgente;
	}



}
