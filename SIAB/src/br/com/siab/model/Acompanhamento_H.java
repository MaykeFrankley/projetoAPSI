package br.com.siab.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Acompanhamento_H {

	@EmbeddedId
	private Acompanhamento_ID id;

	@Column
	private int dia_visita;

	@Column
	private String faz_dieta;

	@Column
	private String toma_medicacao;

	@Column(name="faz_exercicios_fisicos")
	private String exercicio;

	@Column
	private String pressao_arterial;

	public Acompanhamento_H() {
		// TODO Auto-generated constructor stub
	}

	public Acompanhamento_ID getId() {
		return id;
	}

	public void setId(Acompanhamento_ID id) {
		this.id = id;
	}

	public int getDia_visita() {
		return dia_visita;
	}

	public void setDia_visita(int dia_visita) {
		this.dia_visita = dia_visita;
	}

	public String getFaz_dieta() {
		return faz_dieta;
	}

	public void setFaz_dieta(String faz_dieta) {
		this.faz_dieta = faz_dieta;
	}



	public String getToma_medicacao() {
		return toma_medicacao;
	}

	public void setToma_medicacao(String toma_medicacao) {
		this.toma_medicacao = toma_medicacao;
	}

	public String getExercicio() {
		return exercicio;
	}

	public void setExercicio(String exercicio) {
		this.exercicio = exercicio;
	}

	public String getPressao_arterial() {
		return pressao_arterial;
	}

	public void setPressao_arterial(String pressao_arterial) {
		this.pressao_arterial = pressao_arterial;
	}

}
