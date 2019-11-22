package br.com.siab.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import br.com.siab.model.Acompanhamento_H;
import br.com.siab.model.Acompanhamento_ID;
import br.com.siab.model.FichaA;
import br.com.siab.model.FichaA_ID;
import br.com.siab.model.Membro;
import br.com.siab.sql.ConnectionClass;
import br.com.siab.util.AutoCompleteComboBoxListener;
import br.com.siab.util.Util;
import br.com.siab.util.UtilDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class FichaB_HAController implements Initializable{

    String[] meses = {"Janeiro",
    	    "Fevereiro",
    	    "Março",
    	    "Abril",
    	    "Maio",
    	    "Junho",
    	    "Julho",
    	    "Agosto",
    	    "Setembro",
    	    "Outubro",
    	    "Novembro",
    	    "Dezembro"};

	@FXML
	private JFXTabPane tabPane;

	@FXML
	private AnchorPane localizacaoPane;

	@FXML
	private JFXTextField codMunicipio;

	@FXML
	private JFXTextField campo_pesquisa;

	@FXML
	private ComboBox<String> box_uf;

	@FXML
	private ComboBox<String> box_municipio;

	@FXML
	private JFXTextField area;

	@FXML
	private HBox box_dadosMunicipio;

	@FXML
	private Button btn_proximo;

	@FXML
	private TreeTableView<String> table_tree;

	@FXML
	private TreeTableColumn<String, String> col_main;

	@FXML
	private TreeTableColumn<String, String> col_hipertenso;

	@FXML
	private Tab tab_acomp;

	@FXML
	private JFXTextField nome;

	@FXML
	private JFXTextField sexo;

	@FXML
	private JFXTextField idade;

	@FXML
	private JFXCheckBox fumante;

	@FXML
	private JFXTextField endereco;

	@FXML
	private ComboBox<String> box_meses;

	@FXML
	private JFXCheckBox fazDieta;

	@FXML
	private JFXCheckBox medicacao;

	@FXML
	private JFXCheckBox exercicio;

	@FXML
	private JFXTextField pressao_arterial;

	@FXML
	private JFXTextField dia_consulta;

	@FXML
	private TableView<Acompanhamento_H> table_acompanhamentos;

	@FXML
	private TableColumn<Acompanhamento_H, Acompanhamento_ID> col_mes;

	@FXML
	private TableColumn<Acompanhamento_H, Integer> col_dia;

	@FXML
	private TableColumn<Acompanhamento_H, String> col_dieta;

	@FXML
	private TableColumn<Acompanhamento_H, String> col_medicamentos;

	@FXML
	private TableColumn<Acompanhamento_H, String> col_exercicio;

	@FXML
	private TableColumn<Acompanhamento_H, String> col_pressao;

	private FichaA hipertenso;

	private int codHipertenso;


	@FXML
	void checkMunicipio(ActionEvent event) throws SQLException {
		Connection con = ConnectionClass.createConnection();
		PreparedStatement stmt;
		ResultSet rs;

		if(!box_municipio.getSelectionModel().isEmpty()){
			stmt = con.prepareStatement("SELECT codMunicipio FROM siab.municipios WHERE nome = ? AND uf = ?;");
			stmt.setString(1, box_municipio.getSelectionModel().getSelectedItem());
			stmt.setString(2, box_uf.getSelectionModel().getSelectedItem());
			rs = stmt.executeQuery();
			if(rs.next()){
				codMunicipio.setText(String.valueOf(rs.getInt(1)));
			}
			else{
				Util.Alert("Município não cadastrado!");
				codMunicipio.setText("ERRO!");
			}
		}

		table_tree.setRoot(null);
	}

	@FXML
	void getHipertensos(ActionEvent event) throws SQLException {
		Connection con = ConnectionClass.createConnection();
		PreparedStatement stmt;

		if(area.getText().isEmpty() || codMunicipio.getText().equals("ERRO!")) return;

		stmt = con.prepareStatement("SELECT codPessoa FROM siab.CondicoesMembros WHERE condicaoOuDoenca = ?;");
		stmt.setString(1, "Hipertensão arterial");

		ResultSet codHipertensos = stmt.executeQuery();

		TreeItem<String> rootarea = new TreeItem<>("Area:"+area.getText());
		List<TreeItem<String>> microAreas = new ArrayList<>();
		List<TreeItem<String>> familias = new ArrayList<>();
		List<Membro> membros = new ArrayList<>();

		int numMicroAreas = 0;

		while(codHipertensos.next()){
			Membro m = UtilDao.daoFichaA.getMembro(codHipertensos.getInt(1));
			if(m.getArea() == Integer.valueOf(area.getText())){
				membros.add(m);

				stmt = con.prepareStatement("SELECT MAX(microArea) FROM siab.membrosfamilia WHERE area = ? and municipio = ?;");
				stmt.setInt(1, Integer.valueOf(area.getText()));
				stmt.setInt(2, Integer.valueOf(codMunicipio.getText()));
				ResultSet n = stmt.executeQuery();
				if(n.next() && numMicroAreas == 0){
					numMicroAreas = n.getInt(1);
				}
			}
		}

		if(numMicroAreas == 0) return;

		int numFamilias = 0;
		for (Membro membro : membros) {
			if(membro.getCodFamilia() > numFamilias){
				numFamilias = membro.getCodFamilia();
			}
		}

		for(int i = 0; i < numMicroAreas; i++){
			microAreas.add(new TreeItem<>("Micro-Área: "+(i+1)));
		}

		for(int i = 0; i < numFamilias; i++){
			familias.add(new TreeItem<>("Família: "+(i+1)));
		}


		for (Membro membro : membros) {

			micro:
				for (int i = 0; i < microAreas.size(); i++) {
					TreeItem<String> mc = microAreas.get(i);
					for (int j = 0; j < familias.size(); j++) {
						TreeItem<String> fm = familias.get(j);

						if(fm.getValue().equals("Família: "+membro.getCodFamilia()) && mc.getValue().equals("Micro-Área: "+membro.getMicroarea())){
							TreeItem<String> me = new TreeItem<>("(Código: "+membro.getCodPessoa()+") "+membro.getNome());
							fm.getChildren().add(me);
							if(!mc.getChildren().contains(fm)){
								mc.getChildren().add(fm);
							}

							i = 0;
							break micro;
						}
					}
				}
		}

		for(int i = 0; i < microAreas.size();i++){
			TreeItem<String> mi = microAreas.get(i);
			if(mi.getChildren().isEmpty()){
				microAreas.remove(mi);i--;
				continue;
			}
			rootarea.getChildren().add(mi);
		}

		rootarea.setExpanded(true);
		table_tree.setRoot(rootarea);

	}

	@FXML
	void populateCidades(ActionEvent event) {
		Util.populateCidade(box_uf, box_municipio);
	}

	@FXML
	void proximo(ActionEvent event) throws SQLException {
		TreeItem<String> select = table_tree.getSelectionModel().getSelectedItem();
		if(select != null && Integer.valueOf(select.getValue().replaceAll("\\D+","")) > 0){
			tab_acomp.setDisable(false);
			tabPane.getSelectionModel().select(tab_acomp);
			Membro m = UtilDao.daoFichaA.getMembro(Integer.valueOf(select.getValue().replaceAll("\\D+","")));
			nome.setText(m.getNome());
			sexo.setText(m.getSexo());
			idade.setText(String.valueOf(m.getIdade()));

			hipertenso = UtilDao.daoFichaA.getFichaA(new FichaA_ID(m.getCodMunicipio(), m.getArea(), m.getMicroarea(), m.getCodFamilia()));

			endereco.setText(hipertenso.getEndereco()+", "+hipertenso.getNumero());

			Connection con = ConnectionClass.createConnection();
			PreparedStatement stmt;
			stmt = con.prepareStatement("SELECT codPessoa FROM siab.CondicoesMembros WHERE condicaoOuDoenca = ? and codPessoa = ?;");
			stmt.setString(1, "Fumante");
			stmt.setInt(2, m.getCodPessoa());

			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				fumante.setSelected(true);
			}

			box_meses.getSelectionModel().clearSelection();
			fazDieta.setSelected(false);
			medicacao.setSelected(false);
			exercicio.setSelected(false);
			pressao_arterial.clear();
			dia_consulta.clear();

			codHipertenso = m.getCodPessoa();

			ObservableList<Acompanhamento_H> oblist = UtilDao.daoFichaB.getAcompanhamento_H(codHipertenso);
			table_acompanhamentos.setItems(oblist);
		}

	}

	@FXML
	void salvar(ActionEvent event) {
		if(!box_meses.getSelectionModel().isEmpty() && !pressao_arterial.getText().isEmpty() && !dia_consulta.getText().isEmpty()){
			Membro m = UtilDao.daoFichaA.getMembro(Integer.valueOf(table_tree.getSelectionModel().getSelectedItem().getValue().replaceAll("\\D+","")));
			Acompanhamento_H addAcom = new Acompanhamento_H();
			addAcom.setId(new Acompanhamento_ID(hipertenso.getId().getMunicipio(), hipertenso.getId().getArea(), hipertenso.getId().getMicroarea(),
					hipertenso.getId().getCodFamilia(), m.getCodPessoa(), box_meses.getSelectionModel().getSelectedItem()));

			addAcom.setDia_visita(Integer.valueOf(dia_consulta.getText()));
			addAcom.setPressao_arterial(pressao_arterial.getText());
			if(fazDieta.isSelected()){
				addAcom.setFaz_dieta("Sim");
			}
			else{
				addAcom.setFaz_dieta("Não");
			}
			if(medicacao.isSelected()){
				addAcom.setToma_medicacao("Sim");
			}
			else{
				addAcom.setToma_medicacao("Não");
			}
			if(exercicio.isSelected()){
				addAcom.setExercicio("Sim");
			}
			else{
				addAcom.setExercicio("Não");
			}

			UtilDao.daoFichaB.addAcompanhamento_H(addAcom);

			ObservableList<Acompanhamento_H> oblist = UtilDao.daoFichaB.getAcompanhamento_H(codHipertenso);
			table_acompanhamentos.setItems(oblist);

		}
	}

	@FXML
	void getAcompanhamento(ActionEvent event) {
		ObservableList<Acompanhamento_H> ac = UtilDao.daoFichaB.getAcompanhamento_H(codHipertenso);
		if(ac != null){
			for (Acompanhamento_H temp : ac) {
				if(temp.getId().getMes().equals(box_meses.getSelectionModel().getSelectedItem())){
					pressao_arterial.setText(temp.getPressao_arterial());
					dia_consulta.setText(String.valueOf(temp.getDia_visita()));

					if(temp.getFaz_dieta().equals("Sim")){
						fazDieta.setSelected(true);
					}else{
						fazDieta.setSelected(false);
					}

					if(temp.getToma_medicacao().equals("Sim")){
						medicacao.setSelected(true);
					}else{
						medicacao.setSelected(false);
					}

					if(temp.getExercicio().equals("Sim")){
						exercicio.setSelected(true);
					}else{
						exercicio.setSelected(false);
					}

					break;
				}
				else{
					fazDieta.setSelected(false);
					medicacao.setSelected(false);
					exercicio.setSelected(false);
					pressao_arterial.clear();
					dia_consulta.clear();
				}
			}
		}
	}


	@FXML
	void searchMembro(KeyEvent event) {
		outer:
			for (TreeItem<String> micro : table_tree.getRoot().getChildren()) {
				for (TreeItem<String> familia : micro.getChildren()) {
					for(TreeItem<String> membro : familia.getChildren()){
						String valueLow = campo_pesquisa.getText().toLowerCase();
						if(membro.getValue().toLowerCase().contains(valueLow)){
							familia.setExpanded(true);
							micro.setExpanded(true);
							membro.setExpanded(true);
							table_tree.getSelectionModel().select(membro);
							break outer;
						}
					}
				}
			}

	}

	void initCols(){
		col_hipertenso.setCellValueFactory(cellData -> {
			TreeItem<String> rowItem = cellData.getValue();
			if (rowItem != null && rowItem.getValue().contains("Código:")) {
				return new SimpleStringProperty(rowItem.getValue());
			} else {
				return new SimpleStringProperty("");
			}
		});

		col_main.setCellValueFactory(cellData -> {
			TreeItem<String> rowItem = cellData.getValue();
			if (rowItem != null && !rowItem.getValue().contains("Código:")) {
				return new SimpleStringProperty(rowItem.getValue());
			} else {
				return new SimpleStringProperty("");
			}
		});

		col_dia.setCellValueFactory(new PropertyValueFactory<>("dia_visita"));
		col_dieta.setCellValueFactory(new PropertyValueFactory<>("faz_dieta"));
		col_exercicio.setCellValueFactory(new PropertyValueFactory<>("exercicio"));
		col_medicamentos.setCellValueFactory(new PropertyValueFactory<>("toma_medicacao"));
		col_pressao.setCellValueFactory(new PropertyValueFactory<>("pressao_arterial"));
		col_mes.setCellValueFactory(new PropertyValueFactory<>("id"));

		col_mes.setCellFactory(column -> {
			final TableCell<Acompanhamento_H, Acompanhamento_ID> cell = new TableCell<Acompanhamento_H, Acompanhamento_ID>(){
				@Override
				protected void updateItem(Acompanhamento_ID item, boolean empty){
					super.updateItem(item, empty);
					if(empty){
						this.setText("");
					}else{
						this.setText(item.getMes());
					}

				}

			};return cell;
		});
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initCols();


		box_uf.getItems().addAll("Acre","Alagoas","Amapá","Amazonas","Bahia","Ceará","Distrito Federal",
				"Espírito Santo","Goiás","Maranhão","Mato Grosso","Mato Grosso do Sul","Minas Gerais","Pará","Paraíba",
				"Paraná","Pernambuco","Piauí","Rio de Janeiro",
				"Rio Grande do Norte","Rio Grande do Sul","Rondônia",
				"Roraima","Santa Catarina","São Paulo","Sergipe","Tocantins");

		for(int i = 0; i < 12;i++){
			box_meses.getItems().add(meses[i]);
		}

		new AutoCompleteComboBoxListener<>(box_uf);
		new AutoCompleteComboBoxListener<>(box_municipio);
	}
}
