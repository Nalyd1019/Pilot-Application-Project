package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import model.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;


/**
 * @author Lisa Samuelsson &
 * Controller for the fxml file myClubPage which shows the wizard information about the club.
 */
public class MyClubController implements Initializable {

    @FXML private Label clubNameLabel;
    @FXML public Label descriptionLabel;
    @FXML private FlowPane airplaneListFlowPane;
    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane eventFlowPane;

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    private Map<String, MyClubListItem> listItemMap = new HashMap<String, MyClubListItem>();
    private Map<String, EventListItem> eventListItemMap = new HashMap<>();


    /**
     * The initialize method that sets up the controller, creates list items and sets basic styling
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setUpStyling();

        for (Airplane airplane : flightBuddy.getCurrentClub().getAirplanes()) {
            MyClubListItem myClubListItem = new MyClubListItem(airplane, airplane.getRegistration(), airplane.getTotalFlightTime());
            listItemMap.put(airplane.getRegistration(), myClubListItem);
        }

        for (Event event : flightBuddy.getCurrentClub().getSortedEvents()){
            EventListItem eventListItem = new EventListItem(event);
            eventListItemMap.put(event.getDescription(), eventListItem);
        }

        updateAirplaneList();
        updateEventList();

    }


    /**
     * Method that populates the flowpane with clublistitems (airplanes)
     * For all airplanes, it inspects if any checks are needed.
     */
    private void updateAirplaneList() {
        airplaneListFlowPane.getChildren().clear();
        List<Airplane> airplanes = flightBuddy.getCurrentClub().getAirplanes();

        for (Airplane airplane : airplanes) {
            airplane.inspectYearlyCheck();

            MyClubListItem myClubListItem = listItemMap.get(airplane.getRegistration());
            airplaneListFlowPane.getChildren().add(myClubListItem);

            controlCheckStatus(airplane, myClubListItem);
            controlDistanceCheckStatus(airplane, myClubListItem);
        }
    }

    // TODO - javadoc
    private void updateEventList(){
        eventFlowPane.getChildren().clear();
        List<Event> events = flightBuddy.getCurrentClub().getSortedEvents();

        for (Event event : events){
            EventListItem eventListItem = eventListItemMap.get(event.getDescription());
            eventListItem.updateCheckboxes();
            eventFlowPane.getChildren().add(eventListItem);
        }

    }


    /**
     * Method that checks if it is time for yearly check soon or now.
     * If one is true, corresponding method that changes GUI is called in MyClubListItem
     * @param airplane the airplane that may need check
     * @param myClubListItem the list item displaying the airplane
     */
    private void controlCheckStatus(Airplane airplane, MyClubListItem myClubListItem) {
        if(airplane.isTimeForYearlyCheckNow()) {
            myClubListItem.applyYearlyCheck();

        } else if(airplane.isTimeForYearlyCheckSoon()) {
           myClubListItem.applySoonYearlyCheck();
        }
    }


    /**
     * Checks if a distance check is needed (now or soon), if yes, corresponding method changing GUI is called
     * @param airplane the airplane that may need check
     * @param myClubListItem the list item displaying the airplane
     */
    private void controlDistanceCheckStatus(Airplane airplane, MyClubListItem myClubListItem){
        if(airplane.isCheckNeededNow()) {
           myClubListItem.applyDistanceCheck();
        }

        else if(airplane.isCheckNeededSoon()) {
           myClubListItem.applySoonDistanceCheck();
        }
    }


    /**
     * Method that sets styling on the page, including text to the labels.
     */
    private void setUpStyling() {
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        clubNameLabel.setText(flightBuddy.getCurrentClub().getClubName());
        descriptionLabel.setText("Här ser du din klubbs flygplan samt om de behöver kontroll/tillsyn.");

        airplaneListFlowPane.getStyleClass().add("remove-focus");
        scrollPane.getStyleClass().add("remove-focus");
    }

}
