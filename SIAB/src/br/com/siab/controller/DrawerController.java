package br.com.siab.controller;

import java.io.IOException;

import br.com.siab.util.Util;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DrawerController {

    @FXML
    private VBox box_cadastrar;

    @FXML
    private VBox box_buttons;

    @FXML
    private Label label_user;

    @FXML
    private Button cadastrar_btn;

    @FXML
    private FontAwesomeIconView icon_arrow;

    private MainTelaController mainController;

    @FXML
    void cadastrarDropDown(ActionEvent event) {
    	if(!box_cadastrar.isVisible()){
			icon_arrow.setGlyphName("CHEVRON_DOWN");
			int idx = box_buttons.getChildren().indexOf(cadastrar_btn)+1;
			box_buttons.getChildren().add(idx, box_cadastrar);
			box_cadastrar.setVisible(true);
		}else{
			icon_arrow.setGlyphName("CHEVRON_UP");
			box_buttons.getChildren().remove(box_cadastrar);
			box_cadastrar.setVisible(false);
		}
    }

    // Aqui ficará o carregamento das telas através dos botões do Drawer


    @FXML
    void cadastrar_usuario(ActionEvent event) throws IOException {
    	Scene scene = (Scene) ((Node) event.getSource()).getScene();
		Util.LoadWindow(getClass().getResource("/br/com/Acad/view/Arquivo.fxml"), scene, "x");
    }

    @FXML
    void logout_handler(ActionEvent event) {

    }

	void setMainTela(MainTelaController mc){
		this.mainController = mc;
	}

}
