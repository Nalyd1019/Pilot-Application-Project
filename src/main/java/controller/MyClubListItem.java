package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.Airplane;

import java.io.IOException;


public class MyClubListItem extends AnchorPane {

    @FXML private Label registrationLabel;
    @FXML private Label flightTimeLabel;
    @FXML public Button checkIsDoneButton;

    private MyClubController myClubController;
    private Airplane airplane;

    // test
    public MyClubListItem(Airplane airplane, String registration, int flightTime) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("myClubPageListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.airplane = airplane;
        this.registrationLabel.setText(registration);
        this.flightTimeLabel.setText("Flygtid: " + String.valueOf(flightTime) + " minuter");
    }

    @FXML
    protected void onClick(Event event){
        checkIsDoneButton.setText("Tryckt");
    }


}
