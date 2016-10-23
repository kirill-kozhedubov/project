package application;

import com.veniqs.view.LoginPanel;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = LoginPanel.INSTANCE;
			
			
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			
			//primaryStage.setOnCloseRequest(value);
			primaryStage.setScene(scene);
			primaryStage.setTitle("VeniqsLIB");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
