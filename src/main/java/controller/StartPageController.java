package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.FlightBuddy;

import java.net.URL;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    @FXML private Label welcomeLabel;
    @FXML private Button bookButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        welcomeLabel.setText("Välkommen " + flightBuddy.getCurrentUser().getName());

        bookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ViewNavigator.LoadView(ViewNavigator.BOOKING);
            }
        });

    }
}
