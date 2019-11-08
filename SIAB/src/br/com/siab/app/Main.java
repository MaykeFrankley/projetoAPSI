package br.com.siab.app;

import java.util.TimeZone;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Main extends Application{

	public static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));

		Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/br/com/siab/view/MainTela.fxml")));
		Platform.setImplicitExit(false);
		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("")));
		primaryStage.show();

		stage = primaryStage;

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        event.consume();
		    }
		});

		primaryStage.iconifiedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(oldValue){
					primaryStage.setFullScreen(true);
					primaryStage.setFullScreen(false);
				}

			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}

}
