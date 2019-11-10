package br.com.siab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.siab.app.Main;
import br.com.siab.model.CondicaoDoenca;
import br.com.siab.model.CondicaoDoencaID;
import br.com.siab.model.FichaA;
import br.com.siab.model.FichaA_ID;
import br.com.siab.model.InfoFamilia;
import br.com.siab.model.Membro;
import br.com.siab.model.PlanoSaude;
import br.com.siab.model.SituacaoFamilia;
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
			stmt.execute();
			con.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addSituacao(SituacaoFamilia st){
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.persist(st);
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

	public void updateSituacao(SituacaoFamilia st){
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.merge(st);
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

	public SituacaoFamilia getSituacaoFamilia(FichaA_ID id){
		createEM();
		SituacaoFamilia f = entityMn.find(SituacaoFamilia.class, id);
		entityMn.close();
		return f;
	}

	public void addPlano(PlanoSaude pl){
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.persist(pl);
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

	public int addInfoFamilia(InfoFamilia inf){
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.persist(inf);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();
			return inf.getCodInformacao();
		} catch (Exception e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
		return 0;
	}

	public int updateInfoFamilia(InfoFamilia inf){
		try {
			createEM();
			if(!entityMn.getTransaction().isActive())
				entityMn.getTransaction().begin();
			entityMn.merge(inf);
			entityMn.flush();
			entityMn.clear();
			entityMn.getTransaction().commit();
			return inf.getCodInformacao();
		} catch (Exception e) {
			entityMn.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(entityMn.isOpen())
				entityMn.close();
		}
		return 0;
	}

	public InfoFamilia getInfoFamilia(FichaA_ID id){
		Connection con = ConnectionClass.createConnection();
		ArrayList<String> casoDoenca = new ArrayList<>();
		ArrayList<String> meiosComun = new ArrayList<>();
		ArrayList<String> gruposComuni = new ArrayList<>();
		ArrayList<String> meiosTransporte = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM siab.informacoesfamilia WHERE municipio = ? AND area = ? AND microarea = ? AND codFamilia = ?;");
			stmt.setInt(1, id.getMunicipio());
			stmt.setInt(2, id.getArea());
			stmt.setInt(3, id.getMicroarea());
			stmt.setInt(4, id.getCodFamilia());
			ResultSet rs = stmt.executeQuery();
			InfoFamilia inf = new InfoFamilia();
			if(rs.next()){
				inf = new InfoFamilia();
				inf.setCodInformacao(rs.getInt(1));
				inf.setMunicipio(rs.getInt(2));
				inf.setArea(rs.getInt(3));
				inf.setMicroarea(rs.getInt(4));
				inf.setCodFamilia(rs.getInt(5));
				inf.setObservacao(rs.getString(6));
			}

			stmt = con.prepareStatement("SELECT * FROM siab.infoCasoDoenca WHERE codInformacao = ?;");
			stmt.setInt(1, inf.getCodInformacao());
			rs = stmt.executeQuery();
			while(rs.next()){
				casoDoenca.add(rs.getString(2));
			}

			inf.setCasoDoenca(casoDoenca);

			stmt = con.prepareStatement("SELECT * FROM siab.infoMeiosComunicacao WHERE codInformacao = ?;");
			stmt.setInt(1, inf.getCodInformacao());
			rs = stmt.executeQuery();
			while(rs.next()){
				meiosComun.add(rs.getString(2));
			}

			inf.setMeiosComunicacao(meiosComun);

			stmt = con.prepareStatement("SELECT * FROM siab.infoGruposComunitarios WHERE codInformacao = ?;");
			stmt.setInt(1, inf.getCodInformacao());
			rs = stmt.executeQuery();
			while(rs.next()){
				gruposComuni.add(rs.getString(2));
			}

			inf.setGruposComunitarios(gruposComuni);

			stmt = con.prepareStatement("SELECT * FROM siab.infoMeiosTransporte WHERE codInformacao = ?;");
			stmt.setInt(1, inf.getCodInformacao());
			rs = stmt.executeQuery();
			while(rs.next()){
				meiosTransporte.add(rs.getString(2));
			}

			inf.setMeiosTransporte(meiosTransporte);

			con.close();
			stmt.close();
			rs.close();

			return inf;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
				cd.setId(new CondicaoDoencaID(rs.getInt(1), rs.getString(2)));
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
