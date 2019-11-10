package br.com.siab.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="informacoesFamilia")
public class InfoFamilia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codInformacao;

	@Column
	private int municipio;

	@Column
	private int area;

	@Column
	private int microarea;

	@Column
	private int codFamilia;

	@Column
	private String observacao;

	@Transient
	private ArrayList<String> casoDoenca = new ArrayList<>();

	@Transient
	private ArrayList<String> meiosComunicacao = new ArrayList<>();

	@Transient
	private ArrayList<String> gruposComunitarios = new ArrayList<>();

	@Transient
	private ArrayList<String> meiosTransporte = new ArrayList<>();

	public InfoFamilia() {
		// TODO Auto-generated constructor stub
	}

	public int getCodInformacao() {
		return codInformacao;
	}

	public void setCodInformacao(int codInformacao) {
		this.codInformacao = codInformacao;
	}

	public int getMunicipio() {
		return municipio;
	}

	public void setMunicipio(int municipio) {
		this.municipio = municipio;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getMicroarea() {
		return microarea;
	}

	public void setMicroarea(int microarea) {
		this.microarea = microarea;
	}

	public int getCodFamilia() {
		return codFamilia;
	}

	public void setCodFamilia(int codFamilia) {
		this.codFamilia = codFamilia;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ArrayList<String> getCasoDoenca() {
		return casoDoenca;
	}

	public void setCasoDoenca(ArrayList<String> casoDoenca) {
		this.casoDoenca = casoDoenca;
	}

	public ArrayList<String> getMeiosComunicacao() {
		return meiosComunicacao;
	}

	public void setMeiosComunicacao(ArrayList<String> meiosComunicacao) {
		this.meiosComunicacao = meiosComunicacao;
	}

	public ArrayList<String> getGruposComunitarios() {
		return gruposComunitarios;
	}

	public void setGruposComunitarios(ArrayList<String> gruposComunitarios) {
		this.gruposComunitarios = gruposComunitarios;
	}

	public ArrayList<String> getMeiosTransporte() {
		return meiosTransporte;
	}

	public void setMeiosTransporte(ArrayList<String> meiosTransporte) {
		this.meiosTransporte = meiosTransporte;
	}

}
