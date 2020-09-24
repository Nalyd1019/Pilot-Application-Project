package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.io.IOException;
import java.net.URL;

public class ViewNavigator {
    public static final String LOGIN = "loginPage.fxml";
    public static final String START = "startPage.fxml";
    public static final String NEWUSER = "accountWizard.fxml";

    private static Stage mainStage;


    public static void setMainStage(Stage mainStage){
        ViewNavigator.mainStage = mainStage;
    }

    public static void LoadView(String fxml){
        try {
            URL location = ViewNavigator.class.getResource(fxml);
            Parent root = FXMLLoader.load(ViewNavigator.class.getClassLoader().getResource(fxml));
            System.out.println(root);
            mainStage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println("Could not open fxmlPath: " + fxml);
            e.printStackTrace();
        }
    }
}

