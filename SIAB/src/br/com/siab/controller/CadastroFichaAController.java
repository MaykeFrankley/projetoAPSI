package br.com.siab.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import br.com.siab.model.CondicaoDoenca;
import br.com.siab.model.FichaA;
import br.com.siab.model.FichaA_ID;
import br.com.siab.model.Membro;
import br.com.siab.model.Municipio;
import br.com.siab.sql.ConnectionClass;
import br.com.siab.util.AutoCompleteComboBoxListener;
import br.com.siab.util.Util;
import br.com.siab.util.UtilDao;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class CadastroFichaAController implements Initializable{

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private AnchorPane localizacaoPane;

    @FXML
    private HBox box_dadosMunicipio;

    @FXML
    private HBox box_enderecoFamilia;

    @FXML
    private JFXTextField endereco;

    @FXML
    private JFXTextField numEndereco;

    @FXML
    private JFXTextField bairro;

    @FXML
    private JFXTextField cep;

    @FXML
    private ComboBox<String> box_uf;

    @FXML
    private ComboBox<String> box_municipio;

    @FXML
    private JFXTextField segmento;

    @FXML
    private JFXTextField area;

    @FXML
    private ComboBox<String> microarea;

    @FXML
    private JFXTextField familia;

    @FXML
    private JFXDatePicker dt_cadastro;

    @FXML
    private Button btn_proximo;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private JFXTextField codMunicipio1;

    @FXML
    private JFXTextField codMunicipio;

    @FXML
    private ComboBox<String> box_uf2;

    @FXML
    private ComboBox<String> box_municipio2;

    @FXML
    private Button btn_cadastrarMembro;

    private boolean newFicha;

    @FXML
    private Tab tab_cadastroMembros;

    @FXML
    private JFXTextField membroNome;

    @FXML
    private JFXDatePicker membroDt;

    @FXML
    private JFXTextField membroOcupacao;

    @FXML
    private JFXTextField membroIdade;

    @FXML
    private JFXCheckBox frequentaEscola;

    @FXML
    private JFXCheckBox alfabetizado;

    @FXML
    private ComboBox<String> box_sexo;

    @FXML
    private HBox doencasPanel;

    @FXML
    private ComboBox<String> box_doencasCondicoes;

    @FXML
    private JFXListView<String> listDoencasCondicoes;

    @FXML
    private TableView<Membro> table_membros;

    @FXML
    private TableColumn<Membro, Integer> col_codFamiilia;

    @FXML
    private TableColumn<Membro, String> col_nome;

    @FXML
    private TableColumn<Membro, Date> col_dt_nascimento;

    @FXML
    private TableColumn<Membro, Integer> col_idade;

    @FXML
    private TableColumn<Membro, String> col_sexo;

    @FXML
    private TableColumn<Membro, String> col_situacao;

    @FXML
    private TableColumn<Membro, String> col_ocupacao;

    @FXML
    void cadastrarMunicipio(ActionEvent event) {
    	if(!box_municipio2.getSelectionModel().isEmpty() && !codMunicipio1.getText().isEmpty()){
    		Municipio m = new Municipio();
    		m.setCodMunicipio(Integer.valueOf(codMunicipio1.getText()));
    		m.setNome(box_municipio2.getSelectionModel().getSelectedItem());
    		m.setUf(box_uf2.getSelectionModel().getSelectedItem());
    		UtilDao.daoMunicipio.addMunicipio(m);
    	}

    }

    @FXML
    void closeDialog(ActionEvent event) {
    	localizacaoPane.setEffect(null);
    	localizacaoPane.setMouseTransparent(false);
    	dialogPane.setVisible(false);
    }

    @FXML
    void openDialog(ActionEvent event) {
    	BoxBlur blur = new BoxBlur(3, 3, 3);
    	localizacaoPane.setEffect(blur);
    	localizacaoPane.setMouseTransparent(true);
    	dialogPane.setVisible(true);

    }

	@FXML
	void proximo(ActionEvent event){
		if(!(endereco.getText().isEmpty() || bairro.getText().isEmpty() || numEndereco.getText().isEmpty() || cep.getText().isEmpty()) &&
				!box_municipio.getSelectionModel().isEmpty() && !segmento.getText().isEmpty() && !area.getText().isEmpty() && !microarea.getSelectionModel().isEmpty() &&
					!familia.getText().isEmpty() && dt_cadastro.getValue() != null){
			limparSelecao(event);
			FichaA fA = new FichaA();
    		fA.setBairro(bairro.getText());
    		fA.setCep(Integer.valueOf(cep.getText()));
    		dt_cadastro.setValue(LocalDate.now());
    		Date dt = Date.valueOf(dt_cadastro.getValue());
    		fA.setDt_cadastro(dt);
    		fA.setEndereco(endereco.getText());
    		fA.setNumero(Integer.valueOf(numEndereco.getText()));
    		fA.setSegmento(Integer.valueOf(segmento.getText()));
    		fA.setId(new FichaA_ID(Integer.valueOf(codMunicipio.getText()), Integer.valueOf(area.getText()),
    				Integer.valueOf(microarea.getSelectionModel().getSelectedItem()), Integer.valueOf(familia.getText())));
			if(UtilDao.daoFichaA.getFichaA(fA.getId()) == null){

	    		UtilDao.daoFichaA.addFichaA(fA);
	    		System.out.println("Ficha A cadastrada!");

	    		table_membros.setItems(UtilDao.daoFichaA.getMembros(fA.getId()));
			}
			else{
				table_membros.setItems(UtilDao.daoFichaA.getMembros(fA.getId()));
			}
			tab_cadastroMembros.setDisable(false);
			tabPane.getSelectionModel().select(tab_cadastroMembros);

		}
		else{
			tab_cadastroMembros.setDisable(true);
		}
	}

    @FXML
    void getCodFamilia(ActionEvent event) throws SQLException {
    	if(!microarea.getSelectionModel().isEmpty() && !newFicha){
    		Connection conn = ConnectionClass.createConnection();
    		PreparedStatement stmt = conn.prepareStatement("SELECT MAX(codFamilia) FROM siab.fichaa WHERE siab.fichaa.municipio = ? AND area = ? AND microArea = ? group by microarea;");
    		stmt.setInt(1, Integer.valueOf(codMunicipio.getText()));
    		stmt.setInt(2, Integer.valueOf(area.getText()));
    		stmt.setInt(3, Integer.valueOf(microarea.getSelectionModel().getSelectedItem()));
    		ResultSet rs = stmt.executeQuery();

    		if(rs.next()){
    			familia.setText(String.valueOf(rs.getInt(1)+1));
    		}
    		else{
    			familia.setText("1");
    		}
    	}
    }

    @FXML
    void checkArea(ActionEvent event) throws SQLException {
    	if(!area.getText().isEmpty() && !segmento.getText().isEmpty() && !codMunicipio.getText().isEmpty()){
    		newFicha = false;
    		Connection conn = ConnectionClass.createConnection();
    		PreparedStatement stmt = conn.prepareStatement("SELECT microarea FROM siab.fichaa WHERE siab.fichaa.municipio = ? AND area = ? GROUP by microarea;");
    		stmt.setInt(1, Integer.valueOf(codMunicipio.getText()));
    		stmt.setInt(2, Integer.valueOf(area.getText()));
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()){
    			rs.previous();
    			microarea.getItems().clear();
				while(rs.next()){
					if(rs.getInt(1) < 10)
						microarea.getItems().add("0"+String.valueOf(rs.getInt(1)));
					else
						microarea.getItems().add(String.valueOf(rs.getInt(1)));
				}

				microarea.requestFocus();
				microarea.show();
    		}
    		else{

    			JFXButton yes = new JFXButton("Cadastrar");
    			yes.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent even1) ->{
    				FichaA fA = new FichaA();
    	    		fA.setBairro(bairro.getText());
    	    		fA.setCep(Integer.valueOf(cep.getText()));
    	    		dt_cadastro.setValue(LocalDate.now());
    	    		Date dt = Date.valueOf(dt_cadastro.getValue());
    	    		fA.setDt_cadastro(dt);
    	    		fA.setEndereco(endereco.getText());
    	    		fA.setNumero(Integer.valueOf(numEndereco.getText()));
    	    		fA.setSegmento(Integer.valueOf(segmento.getText()));
    	    		fA.setId(new FichaA_ID(Integer.valueOf(codMunicipio.getText()), Integer.valueOf(area.getText()), 1, 1));
    	    		microarea.getItems().setAll("01");
    	    		microarea.show();
    	    		familia.setText("1");

    	    		UtilDao.daoFichaA.addFichaA(fA);
    	    		System.out.println("Ficha A cadastrada!");
    	    		Util.contentPane.getChildren().get(0).setEffect(null);
    	    		newFicha = true;

    			});
    			JFXButton cancel = new JFXButton("Cancelar");
    			cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent even2) ->{
    				Util.contentPane.getChildren().get(0).setEffect(null);
    			});

    			Util.confirmation(Arrays.asList(yes, cancel),"Área não cadastrada para esse município!\nDeseja cadastrar essa nova área?");
    		}

    		conn.close();
    		stmt.close();
    		rs.close();

    	}
    	else{
    		Util.Alert("Preencha o segmento, selecione a cidade e Uf e digite a área!");
    	}
    }

    @FXML
    void checkEndereco(MouseEvent event) {
    	if(!(endereco.getText().isEmpty() || bairro.getText().isEmpty() || numEndereco.getText().isEmpty() || cep.getText().isEmpty())){
    		Platform.runLater(() -> {
    			box_dadosMunicipio.setDisable(false);
    		});
    	}
    }

    @FXML
    void checkMunicipio(ActionEvent event) throws SQLException {
    	if(!box_municipio.getSelectionModel().isEmpty()){
    		Connection conn = ConnectionClass.createConnection();
    		PreparedStatement stmt = conn.prepareStatement("SELECT codMunicipio FROM siab.municipios WHERE nome = ? AND uf = ?;");
    		stmt.setString(1, box_municipio.getSelectionModel().getSelectedItem());
    		stmt.setString(2, box_uf.getSelectionModel().getSelectedItem());
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()){
    			codMunicipio.setText(String.valueOf(rs.getInt(1)));
    		}
    		else{
    			Util.Alert("Município não cadastrado!");
    			codMunicipio.setText("ERRO!");
    		}

    		conn.close();
    		stmt.close();
    		rs.close();

    	}
    }

    @FXML
    void populateCidades(ActionEvent event) {
    	Util.populateCidade(box_uf, box_municipio);
    }

    @FXML
    void populateCidades1(ActionEvent event) {
    	Util.populateCidade(box_uf2, box_municipio2);
    }

    void populateBoxes(){
    	box_uf.getItems().addAll("Acre","Alagoas","Amapá","Amazonas","Bahia","Ceará","Distrito Federal",
				"Espírito Santo","Goiás","Maranhão","Mato Grosso","Mato Grosso do Sul","Minas Gerais","Pará","Paraíba",
				"Paraná","Pernambuco","Piauí","Rio de Janeiro",
				"Rio Grande do Norte","Rio Grande do Sul","Rondônia",
				"Roraima","Santa Catarina","São Paulo","Sergipe","Tocantins");

    	box_uf2.getItems().addAll("Acre","Alagoas","Amapá","Amazonas","Bahia","Ceará","Distrito Federal",
				"Espírito Santo","Goiás","Maranhão","Mato Grosso","Mato Grosso do Sul","Minas Gerais","Pará","Paraíba",
				"Paraná","Pernambuco","Piauí","Rio de Janeiro",
				"Rio Grande do Norte","Rio Grande do Sul","Rondônia",
				"Roraima","Santa Catarina","São Paulo","Sergipe","Tocantins");

    	box_sexo.getItems().addAll("Masculino", "Feminino", "Outro");

    	box_doencasCondicoes.getItems().addAll("Alcoolismo", "Chagas", "Deficiência", "Diabestes", "Epilepsia", "Hanseníase",
    				"Hipertensão arterial", "Malária", "Tuberculose", "Gestação", "Outras");

		new AutoCompleteComboBoxListener<>(box_uf);
		new AutoCompleteComboBoxListener<>(box_uf2);
    }

    //////////////////////////////////////////////////////////Tab cadastrar membro ///////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    void setMembroIdade(ActionEvent event) {
    	LocalDate today = LocalDate.now();
    	LocalDate birthday = membroDt.getValue();

    	if(birthday != null){
    		Period p = Period.between(birthday, today);
        	membroIdade.setText(String.valueOf(p.getYears()));
        	if(p.getYears() > 14){
        		alfabetizado.setVisible(true);
        		frequentaEscola.setVisible(false);
        	}
        	else if(p.getYears() <= 14){
        		alfabetizado.setVisible(false);
        		frequentaEscola.setVisible(true);
        	}

    	}

    }

    @FXML
    void setIdadeMembro(KeyEvent event){
    	if(!membroIdade.getText().isEmpty()){
	    	if(Integer.valueOf(membroIdade.getText()) > 14){
	    		alfabetizado.setVisible(true);
	    		frequentaEscola.setVisible(false);
	    	}
	    	else if(Integer.valueOf(membroIdade.getText()) <= 14){
	    		alfabetizado.setVisible(false);
	    		frequentaEscola.setVisible(true);
	    	}
    	}
    }

    @FXML
    void checkMembroBox(MouseEvent event) {
    	if(!(membroNome.getText().isEmpty() || membroIdade.getText().isEmpty() || box_sexo.getSelectionModel().isEmpty())){
    		doencasPanel.setDisable(false);
    	}
    	else{
    		doencasPanel.setDisable(true);
    	}

    }

    @FXML
    void addDoencaCond(ActionEvent event) {
    	if(!box_doencasCondicoes.getSelectionModel().isEmpty()){
    		for (String d: listDoencasCondicoes.getItems()) {
				if(d.equals(box_doencasCondicoes.getSelectionModel().getSelectedItem())){
					return;
				}
			}
    		listDoencasCondicoes.getItems().add(box_doencasCondicoes.getSelectionModel().getSelectedItem());
    	}
    }

    @FXML
    void removeDoencaCond(ActionEvent event) {
    	String dc = listDoencasCondicoes.getSelectionModel().getSelectedItem();
    	if(dc != null){
    		listDoencasCondicoes.getItems().remove(dc);
    	}
    }

    @FXML
    void cadastrarMembro(ActionEvent event) {
    	Membro selected = table_membros.getSelectionModel().getSelectedItem();
    	if(selected != null){
    		selected.setArea(Integer.valueOf(area.getText()));
    		selected.setMicroarea(Integer.valueOf(microarea.getSelectionModel().getSelectedItem()));
    		selected.setCodFamilia(Integer.valueOf(familia.getText()));
    		selected.setCodMunicipio(Integer.valueOf(codMunicipio.getText()));
    		selected.setNome(membroNome.getText());
    		if(membroDt.getValue() != null){
    			Date dt = Date.valueOf(membroDt.getValue());
    			selected.setDt_nascimento(dt);
    		}
    		selected.setIdade(Integer.valueOf(membroIdade.getText()));
    		if(selected.getIdade() > 14){
    			if(alfabetizado.isSelected())selected.setSituacaoEscolar("Alfabetizado");
    			else selected.setSituacaoEscolar("Não alfabetizado");
    		}
    		else{
    			if(frequentaEscola.isSelected())selected.setSituacaoEscolar("Frequenta a escola");
    			else selected.setSituacaoEscolar("Não frequenta a escola");
    		}

    		selected.setOcupacao(membroOcupacao.getText());
    		selected.setSexo(box_sexo.getSelectionModel().getSelectedItem());


    		UtilDao.daoFichaA.updateMembro(selected);

    		table_membros.getItems().remove(selected);
    		table_membros.getItems().add(selected);

    		UtilDao.daoFichaA.removeAllCondicao(selected.getCodPessoa());

    		for (String d : listDoencasCondicoes.getItems()) {
    			CondicaoDoenca cd = new CondicaoDoenca();
    			cd.setCodPessoa(selected.getCodFamilia());
    			cd.setCondicaoOuDoenca(d);
				UtilDao.daoFichaA.updateCondicao(cd);
			}
    	}

    	else if(!(membroNome.getText().isEmpty() && membroIdade.getText().isEmpty() && box_sexo.getSelectionModel().isEmpty())){
    		Membro m = new Membro();
    		m.setArea(Integer.valueOf(area.getText()));
    		m.setMicroarea(Integer.valueOf(microarea.getSelectionModel().getSelectedItem()));
    		m.setCodFamilia(Integer.valueOf(familia.getText()));
    		m.setCodMunicipio(Integer.valueOf(codMunicipio.getText()));
    		m.setNome(membroNome.getText());
    		if(membroDt.getValue() != null){
    			Date dt = Date.valueOf(membroDt.getValue());
    			m.setDt_nascimento(dt);
    		}
    		m.setIdade(Integer.valueOf(membroIdade.getText()));
    		if(m.getIdade() > 14){
    			if(alfabetizado.isSelected())m.setSituacaoEscolar("Alfabetizado");
    			else m.setSituacaoEscolar("Não alfabetizado");
    		}
    		else{
    			if(frequentaEscola.isSelected())m.setSituacaoEscolar("Frequenta a escola");
    			else m.setSituacaoEscolar("Não frequenta a escola");
    		}

    		m.setOcupacao(membroOcupacao.getText());
    		m.setSexo(box_sexo.getSelectionModel().getSelectedItem());


    		UtilDao.daoFichaA.addMembro(m);
    		System.out.println("Membro cadastrado");
    		table_membros.getItems().add(m);

    		for (String d : listDoencasCondicoes.getItems()) {
    			CondicaoDoenca cd = new CondicaoDoenca();
    			cd.setCodPessoa(m.getCodFamilia());
    			cd.setCondicaoOuDoenca(d);
				UtilDao.daoFichaA.addCondicao(cd);
			}


    	}
    }

    @FXML
    void limparSelecao(ActionEvent event) {
    	table_membros.getSelectionModel().clearSelection();
    	membroNome.clear();membroIdade.clear();membroDt.setValue(null);
    	membroOcupacao.clear();listDoencasCondicoes.getItems().clear();
    	box_sexo.getSelectionModel().clearSelection();
    }

    @FXML
    void checkGroup(ActionEvent event){
    	if(alfabetizado.isSelected()){
    		frequentaEscola.setSelected(false);
    	}else{
    		frequentaEscola.setSelected(true);
    	}
    }

    void initTable(){

    	col_codFamiilia.setCellValueFactory(new PropertyValueFactory<>("codFamilia"));
    	col_dt_nascimento.setCellValueFactory(new PropertyValueFactory<>("dt_nascimento"));
    	col_idade.setCellValueFactory(new PropertyValueFactory<>("idade"));
    	col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	col_ocupacao.setCellValueFactory(new PropertyValueFactory<>("ocupacao"));
    	col_sexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
    	col_situacao.setCellValueFactory(new PropertyValueFactory<>("situacaoEscolar"));

    	col_dt_nascimento.setCellFactory(column -> {
    		TableCell<Membro, Date> cell = new TableCell<Membro, Date>() {
    			private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    			@Override
    			protected void updateItem(Date item, boolean empty) {
    				super.updateItem(item, empty);
    				if(empty) {
    					setText(null);
    				}
    				else {
    					if(item != null)
    						this.setText(format.format(item));
    				}
    			}
    		};
    		return cell;
    	});

    	table_membros.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
    		if(newSelection != null){
    			membroNome.setText(newSelection.getNome());
    			membroIdade.setText(String.valueOf(newSelection.getIdade()));
    			membroOcupacao.setText(newSelection.getOcupacao());
    			if(newSelection.getDt_nascimento() != null){
    				LocalDate lc = newSelection.getDt_nascimento().toLocalDate();
    				membroDt.setValue(lc);
    			}
    			else{
    				membroDt.setValue(null);
    			}
    			box_sexo.getSelectionModel().select(newSelection.getSexo());
    			if(newSelection.getSituacaoEscolar().equals("Frequenta a escola")){
    				frequentaEscola.setSelected(true);
    			}
    			else{
    				frequentaEscola.setSelected(false);
    			}

    			if(newSelection.getSituacaoEscolar().equals("Alfabetizado")){
    				alfabetizado.setSelected(true);
    			}
    			else{
    				alfabetizado.setSelected(false);
    			}

    			if(!membroIdade.getText().isEmpty()){
    		    	if(Integer.valueOf(membroIdade.getText()) > 14){
    		    		alfabetizado.setVisible(true);
    		    		frequentaEscola.setVisible(false);
    		    	}
    		    	else if(Integer.valueOf(membroIdade.getText()) <= 14){
    		    		alfabetizado.setVisible(false);
    		    		frequentaEscola.setVisible(true);
    		    	}
    	    	}

    			listDoencasCondicoes.getItems().clear();
    			ObservableList<CondicaoDoenca> ob = UtilDao.daoFichaA.getDoencasOuCondicoes(newSelection.getCodPessoa());
    			for (int i = 0; i < ob.size(); i++) {
    				listDoencasCondicoes.getItems().add(ob.get(i).getCondicaoOuDoenca());

				}

    			doencasPanel.setDisable(false);
    		}
    	});


    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateBoxes();
		initTable();
		dt_cadastro.setValue(LocalDate.now());

		codMunicipio1.textProperty().addListener((observable, old_value, new_value) -> {
			if(new_value.matches("\\d+") || new_value.equals("")){
				codMunicipio1.setText(new_value);
			}
			else{
				codMunicipio1.setText(old_value);
			}
		});

		area.textProperty().addListener((observable, old_value, new_value) -> {
			if(new_value.matches("\\d+") || new_value.equals("")){
				area.setText(new_value);
			}
			else{
				area.setText(old_value);
			}
		});

		numEndereco.textProperty().addListener((observable, old_value, new_value) -> {
			if(new_value.matches("\\d+") || new_value.equals("")){
				numEndereco.setText(new_value);
			}
			else{
				numEndereco.setText(old_value);
			}
		});

		cep.textProperty().addListener((observable, old_value, new_value) -> {
			if(new_value.matches("\\d+") || new_value.equals("")){
				cep.setText(new_value);
			}
			else{
				cep.setText(old_value);
			}
		});

		segmento.textProperty().addListener((observable, old_value, new_value) -> {
			if(new_value.matches("\\d+") || new_value.equals("")){
				segmento.setText(new_value);
			}
			else{
				segmento.setText(old_value);
			}
		});

		familia.textProperty().addListener((observable, old_value, new_value) -> {
			if(new_value.matches("\\d+") || new_value.equals("")){
				familia.setText(new_value);
			}
			else{
				familia.setText(old_value);
			}
		});

		membroIdade.textProperty().addListener((observable, old_value, new_value) -> {
			if(new_value.matches("\\d+") || new_value.equals("")){
				membroIdade.setText(new_value);
			}
			else{
				membroIdade.setText(old_value);
			}
		});

	}

}
