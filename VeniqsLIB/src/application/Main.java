package application;

import com.veniqs.view.LoginPanel;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage = LoginPanel.INSTANCE;
			LoginPanel.INSTANCE.getMyScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
