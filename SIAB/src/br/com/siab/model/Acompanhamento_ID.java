package br.com.siab.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class Acompanhamento_ID implements Serializable{

	@Column
	private int municipio;

	@Column
	private int area;

	@Column
	private int microarea;

	@Column
	private int codFamilia;

	@Column
	private int codMembro;

	@Column
	private String mes;

	public Acompanhamento_ID() {
		// TODO Auto-generated constructor stub
	}

	public Acompanhamento_ID(int municipio, int area, int microarea, int codFamilia, int codMembro, String mes) {
		this.municipio = municipio;
		this.area = area;
		this.microarea = microarea;
		this.codFamilia = codFamilia;
		this.codMembro = codMembro;
		this.mes = mes;
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

	public int getCodMembro() {
		return codMembro;
	}

	public String getMes() {
		return mes;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof Acompanhamento_ID)) return false;
		Acompanhamento_ID that = (Acompanhamento_ID) obj;
		return Objects.equals(getArea(), that.getArea()) && Objects.equals(getCodFamilia(), that.getCodFamilia()) &&
				Objects.equals(getMicroarea(), that.getMicroarea()) && Objects.equals(getMunicipio(), that.getMunicipio()) &&
				Objects.equals(getMes(), that.getMes()) && Objects.equals(getCodMembro(), that.getCodMembro());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getArea(), getCodFamilia(), getMicroarea(), getMunicipio(), getMes(), getCodMembro());
	}

}
