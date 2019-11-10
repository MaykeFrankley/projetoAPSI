package br.com.siab.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CondicaoDoencaID implements Serializable{

	@Column
	private int codPessoa;

	@Column
	private String condicaoOuDoenca;

	public CondicaoDoencaID() {
		// TODO Auto-generated constructor stub
	}

	public CondicaoDoencaID(int codPessoa, String condicaoOuDoenca) {
		this.codPessoa = codPessoa;
		this.condicaoOuDoenca = condicaoOuDoenca;
	}


	public int getCodPessoa() {
		return codPessoa;
	}

	public String getCondicaoOuDoenca() {
		return condicaoOuDoenca;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof CondicaoDoencaID)) return false;
		CondicaoDoencaID that = (CondicaoDoencaID) obj;
		return Objects.equals(getCondicaoOuDoenca(), that.getCondicaoOuDoenca()) && Objects.equals(getCodPessoa(), that.getCodPessoa());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCodPessoa(), getCondicaoOuDoenca());
	}
}
