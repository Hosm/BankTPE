package application.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Modele.CompteBancaire;
import application.ControlledScreen;
import application.ScreensController;
import application.ScreensFramework;
import dao.CompteBancaireDAO;
import dao.ConfigConnection;
import dao.ConfigConnection2;
import dao.ICompteBancaireDAO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MHY
 */
public class PassController implements Initializable, ControlledScreen {
	ScreensController myController;
	@FXML
	private Pane up;
	@FXML
	private Pane down;
	@FXML
	private Pane left;
	@FXML
	private Pane right;
	@FXML
	private Button closeButton;
	@FXML
	private Button minimizeButton;
	@FXML
	private Button maximizeButton;
	@FXML
	private AnchorPane frame;
	@FXML
	private TextField inputNumC;
	@FXML
	private TextField inputPassword;
	@FXML
	private ProgressIndicator loader;
	@FXML
	private Circle signe1, signe2;
	ICompteBancaireDAO compteDao = new CompteBancaireDAO();

	CompteBancaire comptecurrent = null;

	private double initX, initY, initHeight, initWidth;
	private boolean maximized = false;
	Thread queryThread = new Thread();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		BoucleCheckC();
	}

	public void CheckConnexion() {

		queryThread = new Thread() {
			public void run() {
				/*********/
				Connection connection;

				try {
					connection = ConfigConnection.getConnection();
					if (connection != null) {
						connection.close();
						signe1.setFill(Color.GREEN);
						final Timeline line = new Timeline();
						line.getKeyFrames().add(new KeyFrame(Duration.millis(500), ae -> signe1.setFill(Color.WHITE)));
						line.getKeyFrames().add(new KeyFrame(Duration.millis(1000), ae -> signe1.setFill(Color.GREEN)));
						line.cycleCountProperty();
						line.setCycleCount(3);
						line.play();
					} else {
						signe1.setFill(Color.RED);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};
		queryThread.start();
	}

	public void CheckConnexion2() {
		queryThread = new Thread() {
			public void run() {
				/*********/
				Connection connection;

				try {
					connection = ConfigConnection2.getConnection();
					if (connection != null) {
						connection.close();
						signe2.setFill(Color.GREEN);
						final Timeline line = new Timeline();
						line.getKeyFrames().add(new KeyFrame(Duration.millis(500), ae -> signe2.setFill(Color.WHITE)));
						line.getKeyFrames().add(new KeyFrame(Duration.millis(1000), ae -> signe2.setFill(Color.GREEN)));
						line.cycleCountProperty();
						line.setCycleCount(3);
						line.play();

					} else {
						signe2.setFill(Color.RED);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

				/*************/
			}
		};
		queryThread.start();
	}

	public void BoucleCheckC() {
		final Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(3000), ae -> CheckConnexion()));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(3000), ae -> CheckConnexion2()));
		timeline.cycleCountProperty();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

	}

	@FXML
	public void onEnter() {

		if (!inputPassword.getText().isEmpty() && !inputNumC.getText().isEmpty()) {
			int password = Integer.parseInt(inputPassword.getText());
			int comptebancaire = Integer.parseInt(inputNumC.getText());
			if (compteDao.isAuthentified(comptebancaire, password)) {
				comptecurrent = compteDao.getIDCompteBancaire(comptebancaire);
				MyNotification(NotificationType.SUCCESS, "SUCCESS", "Authentification réussite");
				Timeline doin2sec = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Stage stage = (Stage) down.getScene().getWindow();
						stage.setHeight((stage.getHeight() + 80));
						goToScreen2();
					}
				}));
				doin2sec.play();

			} else
				MyNotification(NotificationType.ERROR, "FAILED",
						"Authentification échouée, veuillez essayer a nouveau");

		}

	}

	public void MyNotification(NotificationType type, String title, String message) {
		Image img = new Image(getClass().getClassLoader().getResourceAsStream("logo.png"));
		TrayNotification tray = new TrayNotification();
		tray.setTitle(title);
		tray.setMessage(message);
		tray.setImage(img);
		tray.showAndDismiss(Duration.seconds(2));
		tray.setNotificationType(type);
		if (type.equals(NotificationType.SUCCESS)) {
			tray.setRectangleFill(Paint.valueOf("#00FF00"));
			tray.setAnimationType(AnimationType.SLIDE);
		} else {
			tray.setRectangleFill(Paint.valueOf("#000000"));
			tray.setAnimationType(AnimationType.POPUP);
		}
	}

	@FXML
	private void goToScreen2() {
		myController.setScreen(ScreensFramework.menupageID, ScreensFramework.menuFile, comptecurrent);
	}

	@FXML
	private void resizeUpPressed(MouseEvent evt) {
		Stage stage = (Stage) up.getScene().getWindow();
		initY = stage.getY();
		initHeight = stage.getHeight();
	}

	@FXML
	private void resizeUpDragged(MouseEvent evt) {
		Stage stage = (Stage) up.getScene().getWindow();
		stage.setHeight(initHeight - (evt.getScreenY() - initY));
		stage.setY(evt.getScreenY());
	}

	@FXML
	private void resizeDownPressed(MouseEvent evt) {
		Stage stage = (Stage) down.getScene().getWindow();

		initHeight = stage.getHeight();
		initY = stage.getY();
	}

	@FXML
	private void resizeDownDragged(MouseEvent evt) {
		Stage stage = (Stage) down.getScene().getWindow();
		stage.setHeight((evt.getScreenY() - initY));
	}

	@FXML
	private void resizeLeftPressed(MouseEvent evt) {
		Stage stage = (Stage) left.getScene().getWindow();
		initWidth = stage.getWidth();
		initX = stage.getX();
	}

	@FXML
	private void resizeLeftDragged(MouseEvent evt) {
		Stage stage = (Stage) left.getScene().getWindow();
		stage.setWidth(initWidth + (initX - evt.getScreenX()));
		stage.setX(evt.getScreenX());
	}

	@FXML
	private void resizeRightPressed(MouseEvent evt) {
		Stage stage = (Stage) right.getScene().getWindow();
		initWidth = stage.getWidth();
		initX = stage.getX();
	}

	@FXML
	private void resizeRightDragged(MouseEvent evt) {
		Stage stage = (Stage) right.getScene().getWindow();
		stage.setWidth((evt.getScreenX() - initX));
	}

	@FXML
	private void minimizeAction(ActionEvent evt) {
		Stage stage = (Stage) minimizeButton.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	private void closeAction(ActionEvent evt) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void maximizeAction(ActionEvent evt) {
		Stage stage = (Stage) maximizeButton.getScene().getWindow();
		if (!maximized) {
			stage.setMaximized(true);
			maximized = true;
		} else {
			stage.setMaximized(false);
			maximized = false;
		}
	}

	@FXML
	private void movePressed(MouseEvent evt) {
		initX = evt.getSceneX();
		initY = evt.getSceneY();
	}

	@FXML
	private void moveDragged(MouseEvent evt) {
		Stage stage = (Stage) frame.getScene().getWindow();
		stage.setX(evt.getScreenX() - initX);
		stage.setY(evt.getScreenY() - initY);

	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@Override
	public void setComptebancaire(CompteBancaire cb) {
		this.comptecurrent = cb;
	}

}
