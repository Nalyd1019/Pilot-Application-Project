package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.FlightBuddy;

import java.net.URL;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    @FXML private Label welcomeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        welcomeLabel.setText("Välkommen " + flightBuddy.getCurrentUser().getName());

    }
}
