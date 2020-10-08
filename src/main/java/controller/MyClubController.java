package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import model.Airplane;
import model.FlightBuddy;
import model.FlyingClub;
import model.Logbook;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class MyClubController implements Initializable {

    @FXML private Label clubNameLabel;
    @FXML public Label descriptionLabel;
    @FXML private FlowPane airplaneListFlowPane;

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    private Map<String, MyClubListItem> listItemMap = new HashMap<String, MyClubListItem>();


    /**
     * The initialize method that sets up the controller, creates list items and sets label texts
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clubNameLabel.setText(flightBuddy.getCurrentClub().getClubName());
        descriptionLabel.setText("Här ser du din klubbs flygplan samt om de behöver tillsyn.");

        // TODO - For testing of distanceCheck
        //Airplane seuyb = flightBuddy.getCurrentClub().getAirplanes().get(0);
        //Logbook logSeuyb = seuyb.getLogbook();
        //logSeuyb.addLogbookEntry(LocalDate.of(2020, 2, 2),0,1005,5,"depPlace","dest", "com", "SE-UYB", "test@gmail.com");
        //seuyb.setnChecks(0);
        //seuyb.removeLogbookEntries();
        // clubNameLabel.setText(String.valueOf(seuyb.getnChecks()));
        // TODO - testing over

        for (Airplane airplane : flightBuddy.getCurrentClub().getAirplanes()) {
            MyClubListItem myClubListItem = new MyClubListItem(airplane, airplane.getRegistration(), airplane.getTotalFlightTime(), this);
            listItemMap.put(airplane.getRegistration(), myClubListItem);
        }

         updateAirplaneList();
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

}
