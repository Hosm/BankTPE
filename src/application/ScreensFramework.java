package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ScreensFramework extends Application {
    
    public static String pagePageId = "passpage";
    public static String pagePageFile = "Passpage.fxml";
    public static String menupageID = "Menupage";
    public static String menuFile = "Menupage.fxml";
    public static String HistoID = "Histo";
    public static String HistoFile = "Historiquepage.fxml";
    public static String retirerID = "retirer";
    public static String retirerFile = "Retirepage.fxml";
    public static String deposerID = "deposer";
    public static String deposerFile = "Deposepage.fxml";
    public static String TransfererID = "Transferer";
    public static String TransfererFile = "Transferepage.fxml";
    
    @Override
    public void start(Stage primaryStage) {
        
        ScreensController mainContainer = new ScreensController();
      /*  mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainContainer.loadScreen(ScreensFramework.HistoID, ScreensFramework.HistoFile);*/
        
        mainContainer.setScreen(ScreensFramework.pagePageId,ScreensFramework.pagePageFile,null);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
