package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import model.FlightBuddy;
import model.License;

import java.net.URL;
import java.util.*;

public class StartPageController implements Initializable {

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    @FXML private Label welcomeLabel;
    @FXML private Button bookButton;
    @FXML private FlowPane licenseFlowpane;
    @FXML private FlowPane bookingFlowpane;

    private Map<String, LicenseItem> licenseItemMap = new HashMap<String, LicenseItem>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        welcomeLabel.setText("Välkommen " + flightBuddy.getCurrentUser().getName());
        welcomeButton();

        for(License license : flightBuddy.getCurrentUser().getLicenses()){
            LicenseItem licenseItem = new LicenseItem(license);
            licenseItemMap.put(license.getLicenseName(),licenseItem);
        }
        updateLicenseList();

    }

    private void welcomeButton(){
        bookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ViewNavigator.LoadView(ViewNavigator.BOOKING);
            }
        });
    }

    private void updateLicenseList(){
        licenseFlowpane.getChildren().clear();

        List<License> licenses = flightBuddy.getCurrentUser().getLicenses();

        for(License license : licenses){
            LicenseItem licenseItem = licenseItemMap.get(license.getLicenseName());
            licenseFlowpane.getChildren().add(licenseItem);
        }

    }

}
