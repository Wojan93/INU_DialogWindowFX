package login;

import java.util.Optional;

import login.Environment;
import login.Users;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class LoginWindow {

	private Dialog<Pair<Environment, String>> dialog;
	private ChoiceBox<Environment> cbxEnv;
	private ComboBox<String> cbxUsers;
	private PasswordField passField;
	private ButtonType loginBtnType;
	private ButtonType cancelBtnType;
	private Node loginButton;
	private Optional<Pair<Environment, String>> result;

	public LoginWindow() {

		dialog = new Dialog();
		dialog.setTitle("Okno logowania do systemu");

		loginBtnType = ButtonType.OK;
		cancelBtnType = ButtonType.CANCEL;

		dialog.getDialogPane().getButtonTypes().addAll(loginBtnType, cancelBtnType);
		dialog.setHeaderText("Logowanie do systemu INU");
		Image loginImg = new Image(LoginWindow.class.getResourceAsStream("login_64x.png"));
		ImageView loginIv = new ImageView(loginImg);
		dialog.setGraphic(loginIv);
		dialog.setResizable(true);

		cbxEnv = new ChoiceBox();
		cbxEnv.getItems().addAll(Environment.values());
		cbxEnv.valueProperty().addListener((observable, oldVal, newVal) -> cbxEnv_Changed(newVal));
		cbxUsers = new ComboBox();
		cbxUsers.setEditable(true);
		cbxUsers.setPromptText("nazwa uzytkownika");
		cbxUsers.setVisibleRowCount(3);
		cbxUsers.valueProperty().addListener((observable, oldVal, newVal) -> cbxUsers_Changed(newVal));
		cbxUsers.setDisable(true);

		passField = new PasswordField();
		passField.textProperty().addListener((observable, oldVal, newVal) -> passField_Changed(newVal));
		passField.setPromptText("haslo");
		passField.setDisable(true);

		GridPane grid = new GridPane();
		this.addLabelsAndFields(grid);
		result = dialog.showAndWait();
		pushedCancelBtn();
		pushedLoginBtn();

		System.out.println(result.isPresent());
	}

	public void pushedCancelBtn() {

	}

	public void pushedLoginBtn() {
		if (result.isPresent()) {
			if (Users.passCheck(cbxUsers.getValue(), passField.getText())) {

				String msgText = "Witaj u¿ytkowniku " + cbxUsers.getValue() + " !";
				AlertWindow.showAndWait(AlertType.INFORMATION, "Zalogowa³eœ siê do systemu", msgText);

			} else {
				AlertWindow.showAndWait(AlertType.ERROR, " ",
						"U¿ytkowniku wpisa³eœ b³êdne dane. Nie zostaniesz zalogowany");

			}

		}
	}

	private void cbxUsers_Changed(String newVal) {
		if (newVal.equals("")) {
			passField.setDisable(true);
		} else {
			passField.setDisable(false);
		}
	}

	private void cbxEnv_Changed(Environment newVal) {
		cbxUsers.setValue("");
		cbxUsers.getItems().clear();

		if (newVal != null) {
			cbxUsers.setDisable(false);
		}

		Users.init(cbxEnv.getValue());

		for (String s : Users.users.keySet()) {
			cbxUsers.getItems().add(s);
		}

	}

	private void passField_Changed(String newVal) {
		if (newVal.length() == 0) {
			loginButton.setDisable(true);
		} else {
			loginButton.setDisable(false);
		}
	}

	public void addLabelsAndFields(GridPane grid) {

		// LoginBox text labels

		Label envLbl = new Label("Srodowisko:");
		Label usrLbl = new Label("Uzytkownik:");
		Label passLbl = new Label("Haslo:");

		// Grid Pane to set elements

		grid.setPadding(new Insets(30));
		grid.setHgap(30);
		grid.setVgap(35);
		grid.setMinWidth(400);
		grid.addRow(0, envLbl, cbxEnv);
		grid.addRow(1, usrLbl, cbxUsers);
		grid.addRow(2, passLbl, passField);
		grid.setAlignment(Pos.CENTER);
		dialog.getDialogPane().setContent(grid);
		loginButton = dialog.getDialogPane().lookupButton(loginBtnType);
		loginButton.setDisable(true);
	}

}
