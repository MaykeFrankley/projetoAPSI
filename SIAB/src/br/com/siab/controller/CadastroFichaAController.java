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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import br.com.siab.model.CondicaoDoenca;
import br.com.siab.model.CondicaoDoencaID;
import br.com.siab.model.FichaA;
import br.com.siab.model.FichaA_ID;
import br.com.siab.model.InfoFamilia;
import br.com.siab.model.Membro;
import br.com.siab.model.Municipio;
import br.com.siab.model.PlanoSaude;
import br.com.siab.model.SituacaoFamilia;
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
    private Tab tab_situacao;

    @FXML
    private ComboBox<String> box_tipoCasa;

    @FXML
    private JFXTextField outro_tipoCasa;

    @FXML
    private JFXTextField numeComodos;

    @FXML
    private JFXCheckBox energiaEletrica;

    @FXML
    private ComboBox<String> box_destinoLixo;

    @FXML
    private ComboBox<String> box_tratamentoAgua;

    @FXML
    private ComboBox<String> box_abastecimentoAgua;

    @FXML
    private ComboBox<String> box_destinoFezes;

    private FichaA_ID fichaA_ID;

    private boolean updateSituacao;

    @FXML
    private Tab tab_outros;

    @FXML
    private JFXTextField nomePlanoSaude;

    @FXML
    private JFXTextField numPessoaPlano;

    @FXML
    private CheckComboBox<String> boxCk_casoDoencProcura;

    @FXML
    private JFXTextField outros_casoDoenca;

    @FXML
    private CheckComboBox<String> boxCk_meiosComunicacao;

    @FXML
    private JFXTextField outros_meioComunicacao;

    @FXML
    private CheckComboBox<String> boxCk_gruposComunitario;

    @FXML
    private JFXTextField outros_gruposComunitario;

    @FXML
    private CheckComboBox<String> boxCk_meiosTransporte;

    @FXML
    private JFXTextField outros_meiosTransporte;

    @FXML
    private JFXTextArea observacoes;

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
			limparInfos();
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

				SituacaoFamilia st = UtilDao.daoFichaA.getSituacaoFamilia(fA.getId());
				if(st != null){
	    			box_abastecimentoAgua.getSelectionModel().select(st.getAbastecimento_agua());
	    			numeComodos.setText(String.valueOf(st.getNum_comodos()));
	    			if(st.getEnergiaEletrica() == 1){
	    				energiaEletrica.setSelected(true);
	    			}
	    			box_destinoLixo.getSelectionModel().select(st.getDestino_lixo());
	    			box_tratamentoAgua.getSelectionModel().select(st.getTratamento_agua());
	    			box_destinoFezes.getSelectionModel().select(st.getDestino_fezesUrina());

	    			for (String tipoCasa : box_tipoCasa.getItems()) {
	    				outro_tipoCasa.clear();
						if(tipoCasa.equals(st.getTipoCasa())){
							box_tipoCasa.getSelectionModel().select(st.getTipoCasa());
							break;
						}
						else{
							outro_tipoCasa.setText(st.getTipoCasa());
						}
					}

	    			updateSituacao = true;
	    		}
			}
			tab_cadastroMembros.setDisable(false);
			tabPane.getSelectionModel().select(tab_cadastroMembros);

			fichaA_ID = fA.getId();

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
    		fichaA_ID = null;
    		newFicha = false;
    		updateSituacao = false;
    		Connection conn = ConnectionClass.createConnection();
    		PreparedStatement stmt = conn.prepareStatement("SELECT microarea FROM siab.fichaa WHERE siab.fichaa.municipio = ? AND area = ? GROUP by microarea;");
    		stmt.setInt(1, Integer.valueOf(codMunicipio.getText()));
    		stmt.setInt(2, Integer.valueOf(area.getText()));
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()){
    			rs.previous();
    			microarea.getItems().clear();
    			int i = 1;
				while(i < 100){
					if(i < 10)
						microarea.getItems().add("0"+String.valueOf(i));
					else
						microarea.getItems().add(String.valueOf(i));
					i++;
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
    	    		int i = 1;
    				while(i < 100){
    					if(i < 10)
    						microarea.getItems().add("0"+String.valueOf(i));
    					else
    						microarea.getItems().add(String.valueOf(i));
    					i++;
    				}
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

    	box_doencasCondicoes.getItems().addAll("Alcoolismo", "Chagas", "Deficiência", "Diabetes", "Epilepsia", "Hanseníase",
    				"Hipertensão arterial", "Malária", "Tuberculose", "Gestação", "Outras");

    	box_tipoCasa.getItems().addAll("Tijolo/Adobe", "Taipa revestida", "Taipa não revestida", "Madeira", "Material aproveitado");

    	box_destinoLixo.getItems().addAll("Coletado", "Queimado/Enterrado", "Céu aberto");

    	box_tratamentoAgua.getItems().addAll("Filtração", "Fervura", "Cloração", "Sem tratamento");

    	box_abastecimentoAgua.getItems().addAll("Rede geral", "Poço ou nascente", "Outros");

    	box_destinoFezes.getItems().addAll("Sistema de esgoto (rede geral)", "Fossa", "Céu aberto");


    	boxCk_casoDoencProcura.getItems().addAll("Hospital", "Unidade de Saúde", "Benzedeira", "Farmácia");

    	boxCk_meiosComunicacao.getItems().addAll("Rádio", "Televisão");

    	boxCk_gruposComunitario.getItems().addAll("Cooperativa", "Grupo religioso", "Associações");

    	boxCk_meiosTransporte.getItems().addAll("Ônibus", "Caminhão", "Carro", "Carroça");

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
    	initTable();
    	Membro selected = table_membros.getSelectionModel().getSelectedItem();
    	if(selected != null){
    		selected.setArea(Integer.valueOf(area.getText()));
    		selected.setMicroarea(Integer.valueOf(microarea.getSelectionModel().getSelectedItem()));
    		selected.setCodFamilia(Integer.valueOf(familia.getText()));
    		selected.setCodMunicipio(Integer.valueOf(codMunicipio.getText()));
    		selected.setNome(membroNome.getText());
    		selected.setDt_nascimento(null);
    		if(membroDt.getValue() != null && membroDt.getEditor().getText().length() > 1){
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

    		UtilDao.daoFichaA.removeAllCondicao(selected.getCodPessoa());


    		for (String d : listDoencasCondicoes.getItems()) {
    			CondicaoDoenca cd = new CondicaoDoenca();
    			cd.setId(new CondicaoDoencaID(selected.getCodPessoa(), d));
				UtilDao.daoFichaA.addCondicao(cd);
			}

    		UtilDao.daoFichaA.updateMembro(selected);

    		table_membros.getItems().remove(selected);
    		table_membros.getItems().add(selected);

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
    			cd.setId(new CondicaoDoencaID(m.getCodPessoa(), d));
				UtilDao.daoFichaA.addCondicao(cd);;
			}

    	}

    }

    @FXML
    void limparSelecao(ActionEvent event) {
    	table_membros.getSelectionModel().clearSelection();
    	membroNome.clear();membroIdade.clear();membroDt.setValue(null);
    	membroOcupacao.clear();listDoencasCondicoes.getItems().clear();
    	box_sexo.getSelectionModel().clearSelection();btn_cadastrarMembro.setText("Cadastrar membro");


    }

    void limparInfos(){
    	box_tipoCasa.getSelectionModel().clearSelection();outro_tipoCasa.clear();numeComodos.clear();
    	energiaEletrica.setSelected(false);box_destinoLixo.getSelectionModel().clearSelection();
    	box_tratamentoAgua.getSelectionModel().clearSelection();box_abastecimentoAgua.getSelectionModel().clearSelection();
    	box_destinoFezes.getSelectionModel().clearSelection();nomePlanoSaude.clear();numPessoaPlano.clear();
    	boxCk_casoDoencProcura.getCheckModel().clearChecks();outros_casoDoenca.clear();
    	boxCk_meiosComunicacao.getCheckModel().clearChecks();outros_meioComunicacao.clear();
    	boxCk_gruposComunitario.getCheckModel().clearChecks();outros_gruposComunitario.clear();
    	boxCk_meiosTransporte.getCheckModel().clearChecks();outros_meiosTransporte.clear();
    }

    @FXML
    void checkGroup(ActionEvent event){
    	if(alfabetizado.isSelected()){
    		frequentaEscola.setSelected(false);
    	}
    	if(frequentaEscola.isSelected()){
    		alfabetizado.setSelected(false);
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
    			btn_cadastrarMembro.setText("Atualizar membro");
    			listDoencasCondicoes.getItems().clear();
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

    			ObservableList<CondicaoDoenca> ob = UtilDao.daoFichaA.getDoencasOuCondicoes(newSelection.getCodPessoa());
    			for (int i = 0; i < ob.size(); i++) {
    				listDoencasCondicoes.getItems().add(ob.get(i).getId().getCondicaoOuDoenca());

				}

    			doencasPanel.setDisable(false);
    		}
    	});


    }


    @FXML
    void proximo2(ActionEvent event) {
    	if(table_membros.getItems().isEmpty()){
			return;
		}

    	tab_situacao.setDisable(false);
    	tabPane.getSelectionModel().select(tab_situacao);
    }

    ////////////////////////////////////////////////////// Tab situacaoMoradia /////////////////////////////////////////////////

    @FXML
    void proximo3(ActionEvent event) {
    	if(!(numeComodos.getText().isEmpty() && box_destinoLixo.getSelectionModel().isEmpty() && box_tratamentoAgua.getSelectionModel().isEmpty() &&
    			box_abastecimentoAgua.getSelectionModel().isEmpty() && box_destinoFezes.getSelectionModel().isEmpty()) && !updateSituacao){

    		if(box_tipoCasa.getSelectionModel().isEmpty()){
    			if(outro_tipoCasa.getText().isEmpty())
    				return;
    		}

    		SituacaoFamilia st = new SituacaoFamilia();
    		st.setId(fichaA_ID);
    		st.setAbastecimento_agua(box_abastecimentoAgua.getSelectionModel().getSelectedItem());
    		if(!box_tipoCasa.getSelectionModel().isEmpty()){
    			st.setTipoCasa(box_tipoCasa.getSelectionModel().getSelectedItem());
    		}else{
    			st.setTipoCasa(outro_tipoCasa.getText());
    		}

    		st.setNum_comodos(Integer.valueOf(numeComodos.getText()));
    		if(energiaEletrica.isSelected()){
    			st.setEnergiaEletrica(1);
    		}else{
    			st.setEnergiaEletrica(0);
    		}

    		st.setDestino_lixo(box_destinoLixo.getSelectionModel().getSelectedItem());
    		st.setTratamento_agua(box_tratamentoAgua.getSelectionModel().getSelectedItem());
    		st.setDestino_fezesUrina(box_destinoFezes.getSelectionModel().getSelectedItem());

    		UtilDao.daoFichaA.addSituacao(st);

    		System.out.println("Situacao cadastrada!");

    		tab_outros.setDisable(false);
    		tabPane.getSelectionModel().select(tab_outros);

    	}
    	else if(!(numeComodos.getText().isEmpty() && box_destinoLixo.getSelectionModel().isEmpty() && box_tratamentoAgua.getSelectionModel().isEmpty() &&
    			box_abastecimentoAgua.getSelectionModel().isEmpty() && box_destinoFezes.getSelectionModel().isEmpty()) && updateSituacao){

    		SituacaoFamilia st = new SituacaoFamilia();
    		st.setId(fichaA_ID);
    		st.setAbastecimento_agua(box_abastecimentoAgua.getSelectionModel().getSelectedItem());
    		if(!box_tipoCasa.getSelectionModel().isEmpty()){
    			st.setTipoCasa(box_tipoCasa.getSelectionModel().getSelectedItem());
    		}else{
    			st.setTipoCasa(outro_tipoCasa.getText());
    		}

    		st.setNum_comodos(Integer.valueOf(numeComodos.getText()));
    		if(energiaEletrica.isSelected()){
    			st.setEnergiaEletrica(1);
    		}else{
    			st.setEnergiaEletrica(0);
    		}

    		st.setDestino_lixo(box_destinoLixo.getSelectionModel().getSelectedItem());
    		st.setTratamento_agua(box_tratamentoAgua.getSelectionModel().getSelectedItem());
    		st.setDestino_fezesUrina(box_destinoFezes.getSelectionModel().getSelectedItem());

    		UtilDao.daoFichaA.updateSituacao(st);

    		System.out.println("Situacao atualizada!");

    		tab_outros.setDisable(false);
    		tabPane.getSelectionModel().select(tab_outros);

    		InfoFamilia check = UtilDao.daoFichaA.getInfoFamilia(fichaA_ID);
    		if(check != null){
    			observacoes.setText(check.getObservacao());
    			for (String i : check.getCasoDoenca()) {
    				boxCk_casoDoencProcura.getCheckModel().check(i);
				}

    			for (String i : check.getMeiosComunicacao()) {
    				boxCk_meiosComunicacao.getCheckModel().check(i);
				}

    			for (String i : check.getGruposComunitarios()) {
    				boxCk_gruposComunitario.getCheckModel().check(i);
				}

    			for (String i : check.getMeiosTransporte()) {
    				boxCk_meiosTransporte.getCheckModel().check(i);
				}

    		}
    	}
    }

    @FXML
    void clearTipoCasa(KeyEvent event){
    	box_tipoCasa.getSelectionModel().clearSelection();
    }

    ///////////////////////////////////////////////////////////////////////////////////Tab Outras informacoes //////////////////////////////////////////////////////

    @FXML
    void finalizarCadastro(ActionEvent event) throws SQLException {
    	Connection con = ConnectionClass.createConnection();
    	PreparedStatement stmt = null;

    	if(!nomePlanoSaude.getText().isEmpty()){
    		if(!numPessoaPlano.getText().isEmpty()){
    			PlanoSaude pl = new PlanoSaude();
    			pl.setId(fichaA_ID);
    			pl.setNomePlano(nomePlanoSaude.getText());
    			pl.setNumeroPessoa(Integer.valueOf(numPessoaPlano.getText()));

    			UtilDao.daoFichaA.addPlano(pl);
    		}
    	}



    	InfoFamilia inf = new InfoFamilia();
    	inf.setArea(fichaA_ID.getArea());
    	inf.setMicroarea(fichaA_ID.getMicroarea());
    	inf.setMunicipio(fichaA_ID.getMunicipio());
    	inf.setCodFamilia(fichaA_ID.getCodFamilia());
    	inf.setObservacao(observacoes.getText());


    	ArrayList<String> casoDoenca = new ArrayList<>(boxCk_casoDoencProcura.getCheckModel().getCheckedItems());
    	if(!outros_casoDoenca.getText().isEmpty())casoDoenca.add(outros_casoDoenca.getText());

    	ArrayList<String> meiosComun = new ArrayList<>(boxCk_meiosComunicacao.getCheckModel().getCheckedItems());
		if(!outros_meioComunicacao.getText().isEmpty())meiosComun.add(outros_meioComunicacao.getText());

		ArrayList<String> gruposComuni = new ArrayList<>(boxCk_gruposComunitario.getCheckModel().getCheckedItems());
		if(!outros_gruposComunitario.getText().isEmpty())gruposComuni.add(outros_gruposComunitario.getText());

		ArrayList<String> meiosTransporte = new ArrayList<>(boxCk_meiosTransporte.getCheckModel().getCheckedItems());
		if(!outros_meiosTransporte.getText().isEmpty())meiosTransporte.add(outros_meiosTransporte.getText());

    	InfoFamilia check = UtilDao.daoFichaA.getInfoFamilia(fichaA_ID);
    	if(check.getMunicipio() == 0){
    		int cod = UtilDao.daoFichaA.addInfoFamilia(inf);
    		con = ConnectionClass.createConnection();

    		for (String string : casoDoenca) {
    			stmt = con.prepareStatement("INSERT INTO siab.infoCasoDoenca VALUES(?, ?);");
    			stmt.setInt(1, cod);stmt.setString(2, string);
    			stmt.execute();
    		}

    		for (String string : meiosComun) {
    			stmt = con.prepareStatement("INSERT INTO siab.InfoMeiosComunicacao VALUES(?, ?);");
    			stmt.setInt(1, cod);stmt.setString(2, string);
    			stmt.execute();
    		}

    		for (String string : gruposComuni) {
    			stmt = con.prepareStatement("INSERT INTO siab.infoGruposComunitarios VALUES(?, ?);");
    			stmt.setInt(1, cod);stmt.setString(2, string);
    			stmt.execute();
    		}

    		for (String string : meiosTransporte) {
    			stmt = con.prepareStatement("INSERT INTO siab.InfoMeiosTransporte VALUES(?, ?);");
    			stmt.setInt(1, cod);stmt.setString(2, string);
    			stmt.execute();
    		}

    	}
    	else{
    		check.setArea(fichaA_ID.getArea());
        	check.setMicroarea(fichaA_ID.getMicroarea());
        	check.setMunicipio(fichaA_ID.getMunicipio());
        	check.setCodFamilia(fichaA_ID.getCodFamilia());
        	check.setObservacao(observacoes.getText());

    		UtilDao.daoFichaA.updateInfoFamilia(check);

    		con = ConnectionClass.createConnection();
    		stmt = con.prepareStatement("DELETE FROM siab.infoCasoDoenca WHERE codInformacao = ?;");
    		stmt.setInt(1, check.getCodInformacao());
    		stmt.execute();

    		stmt = con.prepareStatement("DELETE FROM siab.InfoMeiosComunicacao WHERE codInformacao = ?;");
    		stmt.setInt(1, check.getCodInformacao());
    		stmt.execute();

    		stmt = con.prepareStatement("DELETE FROM siab.infoGruposComunitarios WHERE codInformacao = ?;");
    		stmt.setInt(1, check.getCodInformacao());
    		stmt.execute();

    		stmt = con.prepareStatement("DELETE FROM siab.InfoMeiosTransporte WHERE codInformacao = ?;");
    		stmt.setInt(1, check.getCodInformacao());
    		stmt.execute();

    		for (String string : casoDoenca) {
    			stmt = con.prepareStatement("INSERT INTO siab.infoCasoDoenca VALUES(?, ?);");
    			stmt.setInt(1, check.getCodInformacao());stmt.setString(2, string);
    			stmt.execute();
    		}

    		for (String string : meiosComun) {
    			stmt = con.prepareStatement("INSERT INTO siab.InfoMeiosComunicacao VALUES(?, ?);");
    			stmt.setInt(1, check.getCodInformacao());stmt.setString(2, string);
    			stmt.execute();
    		}

    		for (String string : gruposComuni) {
    			stmt = con.prepareStatement("INSERT INTO siab.infoGruposComunitarios VALUES(?, ?);");
    			stmt.setInt(1, check.getCodInformacao());stmt.setString(2, string);
    			stmt.execute();
    		}

    		for (String string : meiosTransporte) {
    			stmt = con.prepareStatement("INSERT INTO siab.InfoMeiosTransporte VALUES(?, ?);");
    			stmt.setInt(1, check.getCodInformacao());stmt.setString(2, string);
    			stmt.execute();
    		}
    	}

    	Util.Alert("Cadastro finalizado!");

		stmt.close();
		con.close();

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

			if(area.getText().length() > 3){
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
			if(segmento.getText().length() > 2){
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
			if(familia.getText().length() > 3){
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

		numeComodos.textProperty().addListener((observable, old_value, new_value) -> {
			if(new_value.matches("\\d+") || new_value.equals("")){
				numeComodos.setText(new_value);
			}
			else{
				numeComodos.setText(old_value);
			}
		});

		numPessoaPlano.textProperty().addListener((observable, old_value, new_value) -> {
			if(new_value.matches("\\d+") || new_value.equals("")){
				numPessoaPlano.setText(new_value);
			}
			else{
				numPessoaPlano.setText(old_value);
			}
		});



	}

}
