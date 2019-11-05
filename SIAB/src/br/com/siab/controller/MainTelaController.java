package br.com.siab.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import br.com.siab.app.Main;
import br.com.siab.util.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class MainTelaController implements Initializable{

    @FXML
    private BorderPane borderPane;

    @FXML
    private StackPane contentPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private HBox top;

    @FXML
    private JFXHamburger hamburger;

    @FXML
	void close_app(MouseEvent event) {
		JFXButton yes = new JFXButton("Sair");
		yes.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent even1) ->{
			Platform.exit();
			System.exit(1);
		});
		JFXButton cancel = new JFXButton("Cancelar");
		cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent even2) ->{
			Util.contentPane.getChildren().get(0).setEffect(null);
		});

		Util.confirmation(Arrays.asList(yes, cancel),"Deseja sair do sistema?");

	}

	@FXML
	void minimize_stage(MouseEvent event) {
		Main.stage.setIconified(true);
	}

    private void initDrawer(){
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/br/com/siab/view/Dashboard_drawer.fxml"));
			ScrollPane pane = loader.load();
			DrawerController drawerController = loader.getController();
			drawerController.setMainTela(this);

			drawer.setSidePane(pane);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

    private void initializeMenu(){
		Util.contentPane = contentPane;

		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
		transition.setRate(-1);

		hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
			transition.setRate(transition.getRate()*-1);
			transition.play();

			if(drawer.isShown()){
				drawer.close();
			}else{
				drawer.open();
			}
		});

		drawer.setOnDrawerClosed(e -> {
			if(transition.getRate() == 1){
				transition.setRate(transition.getRate()*-1);
				transition.play();
			}
		});

	}

    void enableHamburger(){
		if(hamburger.isDisabled()){
			hamburger.setDisable(false);
			hamburger.setVisible(true);
		}else{
			hamburger.setDisable(true);
			hamburger.setVisible(false);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initDrawer();
		initializeMenu();
		enableHamburger();

	}

}
