package br.com.siab.util;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Util {

	public static StackPane contentPane;
	static JFXDialogLayout dialogLayout = new JFXDialogLayout();
	static JFXDialogLayout dialogLayoutAlert = new JFXDialogLayout();
	static JFXDialog dialog;

	public static void LoadWindow(URL loc,	Scene scene, String or) throws IOException{

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(loc);
		Parent root = loader.load();

		if(or.equals("x")){
			root.translateXProperty().set(scene.getWidth());
		}else{
			root.translateYProperty().set(scene.getHeight());
		}

		contentPane.getChildren().add(root);
		Timeline timel = new Timeline();

		KeyValue kv;
		if(or.equals("x")){
			kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
		}else{
			kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
		}

		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);

		timel.getKeyFrames().add(kf);
		timel.setOnFinished(e -> {
			if(contentPane.getChildren().size() > 1){
				contentPane.getChildren().remove(0);
			}
		});
		timel.play();

	}

	public static void confirmation(List<JFXButton> controls, String body) {
		BoxBlur blur = new BoxBlur(3, 3, 3);
		if (controls.isEmpty()) {
			controls.add(new JFXButton("Okay"));
		}

		dialog = new JFXDialog(contentPane, dialogLayout, JFXDialog.DialogTransition.TOP);

		controls.forEach(controlButton -> {
			controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
				dialog.close();
			});
		});

		dialogLayout.setHeading(new Label(body));
		dialogLayout.setActions(controls);

		dialog.setOverlayClose(false);
		dialog.show();

		if(Util.contentPane.getChildren().size() > 1)contentPane.getChildren().get(0).setEffect(blur);

	}


}
