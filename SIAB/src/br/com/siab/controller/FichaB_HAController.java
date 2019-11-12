package br.com.siab.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class FichaB_HAController {


    @FXML
    private JFXTabPane tabPane;

    @FXML
    private AnchorPane localizacaoPane;

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
    private HBox box_dadosMunicipio;

    @FXML
    private JFXTextField codMunicipio;

    @FXML
    private ComboBox<?> box_uf;

    @FXML
    private ComboBox<?> box_municipio;

    @FXML
    private JFXTextField segmento;

    @FXML
    private JFXTextField area;

    @FXML
    private ComboBox<?> microarea;

    @FXML
    private JFXTextField familia;

    @FXML
    private Button btn_proximo;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private JFXTextField codMunicipio1;

    @FXML
    private ComboBox<?> box_uf2;

    @FXML
    private ComboBox<?> box_municipio2;

    @FXML
    private Tab tab_cadastroMembros;

    @FXML
    private JFXTextField membroNome;

    @FXML
    private JFXComboBox<?> fumante;

    @FXML
    private JFXTextField membroIdade;

    @FXML
    private ComboBox<?> box_sexo;

    @FXML
    private HBox doencasPanel;

    @FXML
    private JFXComboBox<?> dieta;

    @FXML
    private JFXComboBox<?> medicamento;

    @FXML
    private JFXComboBox<?> exercicio;

    @FXML
    private JFXTextField pressao;

    @FXML
    private JFXDatePicker dataConsulta;

    @FXML
    private TableView<?> table_membros;

    @FXML
    private TableColumn<?, ?> col_codFamiilia;

    @FXML
    private TableColumn<?, ?> col_mes;

    @FXML
    private TableColumn<?, ?> col_dieta;

    @FXML
    private TableColumn<?, ?> col_medicamento;

    @FXML
    private TableColumn<?, ?> col_exercicio;

    @FXML
    private TableColumn<?, ?> col_pressao;

    @FXML
    private TableColumn<?, ?> col_consulta;

    @FXML
    private Button btn_visita;

    @FXML
    private Tab tab_outros;

    @FXML
    private JFXTextArea observacoes;

    @FXML
    void cadastrarMunicipio(ActionEvent event) {

    }

    @FXML
    void cadastrarVisita(ActionEvent event) {

    }

    @FXML
    void checkArea(ActionEvent event) {

    }

    @FXML
    void checkEndereco(MouseEvent event) {

    }

    @FXML
    void checkMembroBox(MouseEvent event) {

    }

    @FXML
    void checkMunicipio(ActionEvent event) {

    }

    @FXML
    void closeDialog(ActionEvent event) {

    }

    @FXML
    void finalizarCadastro(ActionEvent event) {

    }

    @FXML
    void getCodFamilia(ActionEvent event) {

    }

    @FXML
    void limparSelecao(ActionEvent event) {

    }

    @FXML
    void populateCidades(ActionEvent event) {

    }

    @FXML
    void populateCidades1(ActionEvent event) {

    }

    @FXML
    void proximo(ActionEvent event) {

    }

    @FXML
    void proximo2(ActionEvent event) {

    }

    @FXML
    void setIdadeMembro(KeyEvent event) {

    }

}
