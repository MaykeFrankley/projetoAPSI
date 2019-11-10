package br.com.siab.model;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Table
public class SituacaoFamilia {

	@EmbeddedId
	private FichaA_ID id;

	@Column
	private String tipoCasa;

	@Column
	private int num_comodos;

	@Column
	private String destino_lixo;

	@Column
	private String tratamento_agua;

	@Column
	private String abastecimento_agua;

	@Column
	private String destino_fezesUrina;

	@Column
	private int energiaEletrica;

	public SituacaoFamilia() {
		// TODO Auto-generated constructor stub
	}

	public FichaA_ID getId() {
		return id;
	}

	public void setId(FichaA_ID id) {
		this.id = id;
	}

	public String getTipoCasa() {
		return tipoCasa;
	}

	public void setTipoCasa(String tipoCasa) {
		this.tipoCasa = tipoCasa;
	}

	public int getNum_comodos() {
		return num_comodos;
	}

	public void setNum_comodos(int num_comodos) {
		this.num_comodos = num_comodos;
	}

	public String getDestino_lixo() {
		return destino_lixo;
	}

	public void setDestino_lixo(String destino_lixo) {
		this.destino_lixo = destino_lixo;
	}

	public String getTratamento_agua() {
		return tratamento_agua;
	}

	public void setTratamento_agua(String tratamento_agua) {
		this.tratamento_agua = tratamento_agua;
	}

	public String getAbastecimento_agua() {
		return abastecimento_agua;
	}

	public void setAbastecimento_agua(String abastecimento_agua) {
		this.abastecimento_agua = abastecimento_agua;
	}

	public String getDestino_fezesUrina() {
		return destino_fezesUrina;
	}

	public void setDestino_fezesUrina(String destino_fezesUrina) {
		this.destino_fezesUrina = destino_fezesUrina;
	}

	public int getEnergiaEletrica() {
		return energiaEletrica;
	}

	public void setEnergiaEletrica(int energiaEletrica) {
		this.energiaEletrica = energiaEletrica;
	}



}
