package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * @Author Samuel Dahlberg
 * Controller class that manages all screen changes during runtime by changing fxml root node
 */
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

    /**
     * method used at launch of program to gain access to main stage
     * @param mainStage
     */
    public static void setMainStage(Stage mainStage){
        ViewNavigator.mainStage = mainStage;
    }

    /**
     * Method that changes root node on the main stage current scene.
     * @param fxml
     */
    public static void LoadView(String fxml){
        try {
            CURRENT_PAGE = fxml;
            Parent root = FXMLLoader.load(ViewNavigator.class.getClassLoader().getResource(fxml));
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

