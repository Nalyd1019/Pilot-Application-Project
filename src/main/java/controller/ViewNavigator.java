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
    public static final String BOOKING = "bookingPage.fxml";
    public static final String ACCOUNT = "myAccountPage.fxml";
    public static final String CLUB = "myClubPage.fxml";
    public static final String LOG = "myLogbook.fxml";
    public static final String NEWUSER = "accountWizard.fxml";

    private static String CURRENT_PAGE = "loginPage.fxml";
    private static Stage mainStage;


    public static void setMainStage(Stage mainStage){
        ViewNavigator.mainStage = mainStage;
    }

    public static void LoadView(String fxml){
        try {
            Parent root = FXMLLoader.load(ViewNavigator.class.getClassLoader().getResource(fxml));
            CURRENT_PAGE = fxml;
            System.out.println(root);
            mainStage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println("Could not open fxmlPath: " + fxml);
            e.printStackTrace();
        }
    }

    public static String getCurrentPage(){
        return CURRENT_PAGE;
    }


}

