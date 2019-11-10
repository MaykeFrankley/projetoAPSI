package br.com.siab.dao;

import javax.persistence.EntityManager;

import br.com.siab.app.Main;
import br.com.siab.model.Municipio;
import br.com.siab.util.Util;

public class DaoMunicipio {

	private EntityManager entityMn;

	public void createEM() {
		this.entityMn = Main.factory.createEntityManager();
	}

	public void addMunicipio(Municipio municipio){
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.persist(municipio);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();

	    	Util.Alert("Entidade cadastrada com sucesso!");
		} catch (Exception e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
	}

}
