
package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Modele.Client;
import Modele.CompteBancaire;
import application.ControlledScreen;
import application.ScreensController;
import application.ScreensFramework;
import dao.ClientDAO;
import dao.IClientDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
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
public class retirerController implements Initializable, ControlledScreen{
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
    private Label LabelName;
	@FXML
    private Label labelNumCompte;
	@FXML
	private Label labelSolde;
	@FXML
    private Button annulerButton;
	@FXML
	private double initX, initY, initHeight, initWidth;
	private boolean maximized = false;
	private CompteBancaire comptecurrent;
	private Client client;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {}


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
	@FXML
	private void goToMenu() {
		myController.setScreen(ScreensFramework.menupageID,ScreensFramework.menuFile,comptecurrent);
	}
	@Override
	public void setComptebancaire(CompteBancaire cb) {
		this.comptecurrent=cb;
		labelNumCompte.setText(Integer.toString(getComptecurrent().getCompteNumero()));
		IClientDAO clientDao= new ClientDAO();
		client = clientDao.getClient(getComptecurrent().getCompteNumero());
		LabelName.setText(client.getForName()+" "+client.getName());
		labelSolde.setText(" "+String.valueOf(comptecurrent.getSolde())+" Dhs");
	}

	public CompteBancaire getComptecurrent() {
		return comptecurrent;
	}
	public void retirer1(){
		if(!comptecurrent.retirer(20)){
			MyNotification(NotificationType.ERROR," Erreur"," Transaction annulée, Verifier les données entrées");
		}
		goToMenu();
	}
	public void retirer2(){
		if(!comptecurrent.retirer(50)){
		MyNotification(NotificationType.ERROR," Erreur"," Transaction annulée, Verifier les données entrées");
	}
		goToMenu();
	}
	public void retirer3(){
		if(!comptecurrent.retirer(100)){
			MyNotification(NotificationType.ERROR," Erreur"," Transaction annulée, Verifier les données entrées");
		}
		goToMenu();
	}
	public void retirer4(){
		if(!comptecurrent.retirer(200)){
			MyNotification(NotificationType.ERROR," Erreur"," Transaction annulée, Verifier les données entrées");
		}
		goToMenu();
	}
	public void retirer5(){
		if(!comptecurrent.retirer(500)){
			MyNotification(NotificationType.ERROR," Erreur"," Transaction annulée, Verifier les données entrées");
		}
		goToMenu();
	}
	public void retirer6(){
		if(!comptecurrent.retirer(1000)){
			MyNotification(NotificationType.ERROR," Erreur"," Transaction annulée, Verifier les données entrées");
		}
		goToMenu();
	}
	public void retirer7(){
		if(!comptecurrent.retirer(2000)){
			MyNotification(NotificationType.ERROR," Erreur"," Transaction annulée, Verifier les données entrées");
		}
		goToMenu();
	}
	public void retirer8(){
		if(!comptecurrent.retirer(3000)){
			MyNotification(NotificationType.ERROR," Erreur"," Transaction annulée, Verifier les données entrées");
		}
		goToMenu();
	}
	
    
}

