package br.com.siab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.siab.app.Main;
import br.com.siab.model.Acompanhamento_H;
import br.com.siab.model.Acompanhamento_ID;
import br.com.siab.model.FichaA_ID;
import br.com.siab.model.FichaB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaoFichaB {

	private EntityManager entityMn;

	public void createEM() {
		this.entityMn = Main.factory.createEntityManager();
	}

	public void addFichaB(FichaB b) {
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.persist(b);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();

		} catch (PersistenceException e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
	}

	public void updateFichaB(FichaB b) {
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.merge(b);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();

		} catch (PersistenceException e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
	}


	public FichaB getFichaB(FichaA_ID id){
		createEM();
		FichaB b = entityMn.find(FichaB.class, id);
		entityMn.close();
		return b;
	}

	public void addAcompanhamento_H(Acompanhamento_H a) {
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.persist(a);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();

		} catch (PersistenceException e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
	}

	public void updateAcompanhamento_H(Acompanhamento_H a) {
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.merge(a);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();

		} catch (PersistenceException e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
	}

	public Acompanhamento_H getFichaB(Acompanhamento_ID id){
		createEM();
		Acompanhamento_H a = entityMn.find(Acompanhamento_H.class, id);
		entityMn.close();
		return a;
	}

	@SuppressWarnings("unchecked")
	public ObservableList<Acompanhamento_H> getAcompanhamento_H(int codMembro) {
		try {
			createEM();
			List<Acompanhamento_H> list = entityMn.createQuery("from Acompanhamento_H where codMembro = :cm order by "
					+ "FIELD(mes,'Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro')")
					.setParameter("cm", codMembro)
					.getResultList();
			ObservableList<Acompanhamento_H> oblist = FXCollections.observableList(list);

			return oblist;
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			entityMn.close();
		}
		return null;

	}

}
