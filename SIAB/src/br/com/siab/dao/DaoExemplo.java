package br.com.siab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.siab.app.Main;
import br.com.siab.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaoExemplo {

	private EntityManager entityMn;

	public void createEM() {
		this.entityMn = Main.factory.createEntityManager();
	}

//	public void addEntidade(Object nomeDaEntidade) {
//		try {
//			createEM();
//			if(!entityMn.getTransaction().isActive())
//				entityMn.getTransaction().begin();
//			entityMn.persist(nomeDaEntidade);
//			entityMn.flush();
//			entityMn.clear();
//			entityMn.getTransaction().commit();
//
//	    	Util.Alert("Entidade cadastrada com sucesso!");
//		} catch (PersistenceException e) {
//			entityMn.getTransaction().rollback();
//			e.printStackTrace();
//		}finally {
//			if(entityMn.isOpen())
//				entityMn.close();
//		}
//	}
//
//	public void updateEntidade(Object nomeDaEntidade) {
//		try {
//			createEM();
//			if(!entityMn.getTransaction().isActive())
//				entityMn.getTransaction().begin();
//			entityMn.merge(nomeDaEntidade);
//			entityMn.flush();
//			entityMn.clear();
//			entityMn.getTransaction().commit();
//
//	    	Util.Alert("Entidade atualizada com sucesso!");
//		} catch (PersistenceException e) {
//			entityMn.getTransaction().rollback();
//			e.printStackTrace();
//		}finally {
//			if(entityMn.isOpen())
//				entityMn.close();
//		}
//	}

//	public Object getEntidade(Object id){
//		createEM();
//		Classe da entidade c = entityMn.find(Classe da entidade, id);
//		entityMn.close();
//		return c;
//		O id pode ser uma String, um Int, um float, e até mesmo uma classe, vai depender do Id da tabela;
//
//	}

//	@SuppressWarnings("unchecked")
//	public ObservableList<ClasseDaEntidade> getAllEntidades() {
//		try {
//			createEM();
//			List<ClasseDaEntidade> list = entityMn.createQuery("from ClasseDaEntidade").getResultList();
//			ObservableList<ClasseDaEntidade> oblist = FXCollections.observableList(list);
//			return oblist;
//		} catch (PersistenceException e) {
//			e.printStackTrace();
//		}finally {
//			entityMn.close();
//		}
//		return null;
//
//	}


}
