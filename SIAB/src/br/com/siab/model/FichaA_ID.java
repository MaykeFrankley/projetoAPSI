package br.com.siab.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class FichaA_ID implements Serializable{

	@Column
	private int municipio;

	@Column
	private int area;

	@Column
	private int microarea;

	@Column
	private int codFamilia;

	public FichaA_ID() {
		// TODO Auto-generated constructor stub
	}

	public FichaA_ID(int municipio, int area, int microarea, int codFamilia) {
		this.municipio = municipio;
		this.area = area;
		this.microarea = microarea;
		this.codFamilia = codFamilia;
	}

	public int getMunicipio() {
		return municipio;
	}

	public int getArea() {
		return area;
	}

	public int getMicroarea() {
		return microarea;
	}

	public int getCodFamilia() {
		return codFamilia;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof FichaA_ID)) return false;
		FichaA_ID that = (FichaA_ID) obj;
		return Objects.equals(getArea(), that.getArea()) && Objects.equals(getCodFamilia(), that.getCodFamilia()) &&
				Objects.equals(getMicroarea(), that.getMicroarea()) && Objects.equals(getMunicipio(), that.getMunicipio());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getArea(), getCodFamilia(), getMicroarea(), getMunicipio());
	}

}
