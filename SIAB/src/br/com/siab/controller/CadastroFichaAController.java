package br.com.siab.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import br.com.siab.sql.ConnectionClass;
import br.com.siab.util.AutoCompleteComboBoxListener;
import br.com.siab.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;

public class CadastroFichaAController implements Initializable{

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private AnchorPane localizacaoPane;

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
    private JFXTextField microarea;

    @FXML
    private JFXTextField familia;

    @FXML
    private JFXDatePicker dt_visita;

    @FXML
    private Button btn_proximo;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private JFXTextField codMunicipio;

    @FXML
    private JFXTextField codMunicipio1;

    @FXML
    private ComboBox<String> box_uf2;

    @FXML
    private ComboBox<String> box_municipio2;

    @FXML
    void cadastrarMunicipio(ActionEvent event) {

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
    void checkMunicipio(ActionEvent event) throws SQLException {
    	if(!box_municipio.getSelectionModel().isEmpty()){
    		Connection conn = ConnectionClass.createConnection();
    		PreparedStatement stmt = conn.prepareStatement("SELECT codMunicipio FROM siab.municipios WHERE nome = ? AND uf = ?;");
    		stmt.setString(1, box_municipio.getSelectionModel().getSelectedItem());
    		stmt.setString(2, box_uf.getSelectionModel().getSelectedItem());
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()){
    			codMunicipio1.setText(String.valueOf(rs.getInt(1)));
    		}
    		else{
    			Util.Alert("Município não cadastrado!");
    			codMunicipio1.setText("ERRO!");
    		}

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

		new AutoCompleteComboBoxListener<>(box_uf);
		new AutoCompleteComboBoxListener<>(box_uf2);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateBoxes();

		codMunicipio.textProperty().addListener((observable, old_value, new_value) -> {
			if(new_value.matches("\\d+") || new_value.equals("")){
				codMunicipio.setText(new_value);
			}
			else{
				codMunicipio.setText(old_value);
			}
		});

	}

}
