package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Airplane;
import model.Event;
import model.FlightBuddy;
import model.Pilot;

import java.io.IOException;

public class EventListItem extends AnchorPane {

    @FXML private Label eventName;
    @FXML private Label eventStartTime;
    @FXML private Label eventEndTime;
    @FXML private Label eventDescription;
    @FXML private CheckBox acceptEventCheckbox;

    private Pilot currentUser = FlightBuddy.getInstance().getCurrentUser();

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
        eventDescription.setText(event.getDetailedDesc());

        acceptEventCheckbox.setText("Kommer");
        acceptEventCheckbox.setVisible(true);
        if (event.getPilotsAttending().contains(currentUser))

        checkboxListener(event);

    }

    private void checkboxListener(Event event){
        acceptEventCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    event.addAttendingPilot(currentUser);
                } else{
                    event.getPilotsAttending().remove(currentUser);
                }

            }
        });
    }


}
