package br.com.siab.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="condicoesmembros")
public class CondicaoDoenca {

	@EmbeddedId
	private CondicaoDoencaID id;

	public CondicaoDoenca() {
		// TODO Auto-generated constructor stub
	}

	public CondicaoDoencaID getId() {
		return id;
	}

	public void setId(CondicaoDoencaID id) {
		this.id = id;
	}


}
