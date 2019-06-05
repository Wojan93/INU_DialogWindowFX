package login;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertWindow {

	public static Optional<ButtonType> showAndWait(AlertType type, String title,String message) {
		
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(message);
		switch(type) {
		case ERROR:
		alert.setHeaderText("Nie zalogowano");
			break;
		case INFORMATION: {
			alert.setHeaderText("Dane zaakceptowane!");
			break;
		}
		default:
			break;
		}
		return alert.showAndWait();
	}
}
