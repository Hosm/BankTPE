
package application.Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Modele.Client;
import Modele.CompteBancaire;
import Modele.Historique;
import Modele.Histotable;
import application.ControlledScreen;
import application.ScreensController;
import application.ScreensFramework;
import dao.ClientDAO;
import dao.HistoriqueDAO;
import dao.IClientDAO;
import dao.IHistoriqueDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class HistoriqueController implements Initializable, ControlledScreen{
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
    private TableView<Histotable> ttable;
	@FXML
    private TableColumn<Histotable,Integer> ID;
	@FXML
    private TableColumn<Histotable,Integer> Type;
	@FXML
    private TableColumn<Histotable,Double> Valeur;
	@FXML
    private TableColumn<Histotable,String> Date;
	private int numberid=1;
	private double initX, initY, initHeight, initWidth;
	private boolean maximized = false;
	private CompteBancaire comptecurrent;
	private Client client;
	
    final ObservableList<Histotable> data=FXCollections.observableArrayList();
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ID.setCellValueFactory(new PropertyValueFactory<>("id"));
		Type.setCellValueFactory(new PropertyValueFactory<>("val"));
		Valeur.setCellValueFactory(new PropertyValueFactory<>("msg"));
		Date.setCellValueFactory(new PropertyValueFactory<>("date"));
	    ttable.setItems(data);
	}
  public void addHisto(int idcompte){
	  IHistoriqueDAO HistoriqueDAO=new HistoriqueDAO();
	  List<Historique> listH=HistoriqueDAO.listHistorique(idcompte);
	  for(Historique h:listH){
		  data.add(new Histotable(numberid, h.getMsg(), h.getval(),h.getDate()));
		  numberid++;
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
		labelSolde.setText(String.valueOf(comptecurrent.getSolde()));
		addHisto(comptecurrent.getId()); 
	}

	public CompteBancaire getComptecurrent() {
		return comptecurrent;
	}
}

