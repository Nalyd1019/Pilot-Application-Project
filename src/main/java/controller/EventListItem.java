package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Event;
import model.FlightBuddy;
import model.Pilot;

import java.io.IOException;

/**
 * @Author Dylan Osolian
 * A controller for the EventListItem that represents one event.
 */
public class EventListItem extends AnchorPane {

    @FXML private Label eventName;
    @FXML private Label eventStartTime;
    @FXML private Label eventEndTime;
    @FXML private Label eventDescription;
    @FXML private CheckBox acceptEventCheckbox;
    @FXML private Label dateLabel;
    @FXML private Label numberAcceptedLabel;

    private Pilot currentUser = FlightBuddy.getInstance().getCurrentUser();
    private Event event;

    /**
     * Constructor for the EventListItem, initializes and makes the fxml file display relevant informatioon about the event.
     * @param event the event that the listItem represents
     */
    EventListItem(Event event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("eventItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.event = event;

        eventName.setText(event.getDescription());
        eventStartTime.setText(String.valueOf(event.getStartTime()));
        eventEndTime.setText(String.valueOf(event.getEndTime()));
        eventDescription.setText(event.getDetailedDesc());
        dateLabel.setText(event.getDate().toString());
        numberAcceptedLabel.setText(String.valueOf(event.getPilotsAttending().size()));


        updateCheckboxes();
        acceptEventCheckbox.setText("Kommer");
        acceptEventCheckbox.setVisible(true);

        checkboxListener(event);

    }

    /**
     * Listener to the checkbox to check if the user is attending the event or not.
     * @param event The event the listItem represents.
     */
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

    /**
     * Method to update checkboxes in order for them to show the same setting that has previosly been selected by the user
     */
    void updateCheckboxes(){
        for (Pilot pilot : event.getPilotsAttending()){
            if (pilot.getEmail().equals(currentUser.getEmail())){
                acceptEventCheckbox.setSelected(true);
            }
        }
    }



}
