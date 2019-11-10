package br.com.siab.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MembrosFamilia")
public class Membro {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codPessoa;

	@Column(name="municipio")
	private int codMunicipio;

	@Column
	private int area;

	@Column
	private int microarea;

	@Column
	private int codFamilia;

	@Column
	private String nome;

	@Column
	private Date dt_nascimento;

	@Column
	private int idade;

	@Column
	private String sexo;

	@Column
	private String situacaoEscolar;

	@Column
	private String ocupacao;

	public Membro() {
		// TODO Auto-generated constructor stub
	}

	public int getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(int codPessoa) {
		this.codPessoa = codPessoa;
	}

	public int getCodMunicipio() {
		return codMunicipio;
	}

	public void setCodMunicipio(int codMunicipio) {
		this.codMunicipio = codMunicipio;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSituacaoEscolar() {
		return situacaoEscolar;
	}

	public void setSituacaoEscolar(String situacaoEscolar) {
		this.situacaoEscolar = situacaoEscolar;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}





}
