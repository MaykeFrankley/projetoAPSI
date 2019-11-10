package br.com.siab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="condicoesmembros")
public class CondicaoDoenca {

	@Id
	@Column
	private int codPessoa;

	@Column
	private String condicaoOuDoenca;

	public CondicaoDoenca() {
		// TODO Auto-generated constructor stub
	}

	public int getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(int codPessoa) {
		this.codPessoa = codPessoa;
	}

	public String getCondicaoOuDoenca() {
		return condicaoOuDoenca;
	}

	public void setCondicaoOuDoenca(String condicaoOuDoenca) {
		this.condicaoOuDoenca = condicaoOuDoenca;
	}



}
