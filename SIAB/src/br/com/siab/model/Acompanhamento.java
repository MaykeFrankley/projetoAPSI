package br.com.siab.model;

import java.sql.Date;

public class Acompanhamento {

	private FichaB_HA fichaB_HA;
	
	private boolean dieta;
	private boolean medicamento;
	private boolean exercicio;
	private Date dtVisita;
	private float prArterial;
	
	public boolean isDieta() {
		return dieta;
	}
	public void setDieta(boolean dieta) {
		this.dieta = dieta;
	}
	public boolean isMedicamento() {
		return medicamento;
	}
	public void setMedicamento(boolean medicamento) {
		this.medicamento = medicamento;
	}
	public boolean isExercicio() {
		return exercicio;
	}
	public void setExercicio(boolean exercicio) {
		this.exercicio = exercicio;
	}
	public Date getDtVisita() {
		return dtVisita;
	}
	public void setDtVisita(Date dtVisita) {
		this.dtVisita = dtVisita;
	}
	public float getPrArterial() {
		return prArterial;
	}
	public void setPrArterial(float prArterial) {
		this.prArterial = prArterial;
	}
	
	
}
