package app;

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import login.LoginWindow;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		LoginWindow logDialog = new LoginWindow();

	}

	// main method
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
