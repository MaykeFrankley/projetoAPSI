package br.com.siab.model;

import java.sql.Date;
import java.util.List;

public class FichaB_HA {
	
	private Date dt_Cadastro;

	private String nome;
	private boolean fumante;
	private int idade;
	private String sexo;
	
	private FichaA fichaA;
	private FichaA_ID fichaA_id;
	
	private InfoFamilia infoFamilia;
	
	private List<Acompanhamento> acompanhamentos;

	public FichaA getFichaA() {
		return fichaA;
	}

	public void setFichaA(FichaA fichaA) {
		this.fichaA = fichaA;
	}

	public FichaA_ID getFichaA_id() {
		return fichaA_id;
	}

	public void setFichaA_id(FichaA_ID fichaA_id) {
		this.fichaA_id = fichaA_id;
	}

	public List<Acompanhamento> getAcompanhamentos() {
		return acompanhamentos;
	}

	public void setAcompanhamentos(List<Acompanhamento> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
	}
	
	
	
}
