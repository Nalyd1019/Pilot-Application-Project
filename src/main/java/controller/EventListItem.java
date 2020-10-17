package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Airplane;
import model.Event;

import java.io.IOException;

public class EventListItem extends AnchorPane {

    @FXML private Label eventName;
    @FXML private Label eventStartTime;
    @FXML private Label eventEndTime;

    EventListItem(Event event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("eventItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        eventName.setText(event.getDescription());
        eventStartTime.setText(String.valueOf(event.getStartTime()));
        eventEndTime.setText(String.valueOf(event.getEndTime()));

    }


}
