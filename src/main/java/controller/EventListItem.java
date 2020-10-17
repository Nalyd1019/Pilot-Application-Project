package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Airplane;

import java.io.IOException;

public class EventListItem extends AnchorPane {

    @FXML private Label eventName;

    EventListItem(String eventName) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("eventItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.eventName.setText(eventName);

    }


}
