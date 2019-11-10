package br.com.siab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.siab.app.Main;
import br.com.siab.model.CondicaoDoenca;
import br.com.siab.model.FichaA;
import br.com.siab.model.FichaA_ID;
import br.com.siab.model.Membro;
import br.com.siab.sql.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaoFichaA {

	private EntityManager entityMn;

	public void createEM() {
		this.entityMn = Main.factory.createEntityManager();
	}

	public void addFichaA(FichaA fichaa){
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.persist(fichaa);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();
		} catch (Exception e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
	}

	public void updateFichaA(FichaA fichaa){
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.merge(fichaa);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();
		} catch (Exception e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
	}

	public FichaA getFichaA(FichaA_ID id){
		createEM();
		FichaA f = entityMn.find(FichaA.class, id);
		entityMn.close();
		return f;
	}


	public void addMembro(Membro membro){
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.persist(membro);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();
		} catch (Exception e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
	}

	public void updateMembro(Membro membro){
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.merge(membro);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();
		} catch (Exception e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
	}

	public void addCondicao(CondicaoDoenca cd){
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.persist(cd);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();
		} catch (Exception e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
	}

	public void updateCondicao(CondicaoDoenca cd){
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.merge(cd);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();
		} catch (Exception e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
	}

	public void removeAllCondicao(int cd){
		Connection con = ConnectionClass.createConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM siab.condicoesmembros WHERE codPessoa = ?;");
			stmt.setInt(1, cd);
			stmt.executeUpdate();
			con.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public ObservableList<Membro> getMembros(FichaA_ID id){
		try {
			createEM();
			List<Membro> list = entityMn.createQuery("from Membro where municipio = :m and area = :a and microarea = :mc and codfamilia = :f")
					.setParameter("m", id.getMunicipio())
					.setParameter("a", id.getArea())
					.setParameter("mc", id.getMicroarea())
					.setParameter("f", id.getCodFamilia())
					.getResultList();
			ObservableList<Membro> oblist = FXCollections.observableList(list);
			return oblist;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			entityMn.close();
		}
		return null;
	}


	public ObservableList<CondicaoDoenca> getDoencasOuCondicoes(int codPessoa){
		Connection con = ConnectionClass.createConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM siab.condicoesmembros WHERE codPessoa = ?;");
			stmt.setInt(1, codPessoa);
			ResultSet rs = stmt.executeQuery();
			ObservableList<CondicaoDoenca> oblist = FXCollections.observableArrayList();
			while(rs.next()){
				CondicaoDoenca cd = new CondicaoDoenca();
				cd.setCodPessoa(rs.getInt(1));
				cd.setCondicaoOuDoenca(rs.getString(2));
				oblist.add(cd);
			}

			con.close();
			stmt.close();
			rs.close();

			return oblist;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
