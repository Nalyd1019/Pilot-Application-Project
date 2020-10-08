package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import model.Airplane;
import model.FlightBuddy;
import model.FlyingClub;

import java.net.URL;
import java.util.*;

public class MyClubController implements Initializable {

    @FXML private Label clubNameLabel;
    @FXML public Label descriptionLabel;
    @FXML private FlowPane airplaneListFlowPane;

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    private Map<String, MyClubListItem> listItemMap = new HashMap<String, MyClubListItem>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clubNameLabel.setText(flightBuddy.getCurrentClub().getClubName());
        descriptionLabel.setText("Här ser du din klubbs flygplan samt om de behöver tillsyn.");

        // TODO - For testing of distanceCheck
        // Airplane seuyb = flightBuddy.getCurrentClub().getAirplanes().get(0);
        // Logbook logSeuyb = seuyb.getLogbook();
        // logSeuyb.addLogbookEntry(LocalDate.of(2020, 2, 2),0,15001,5,"depPlace","dest", "com", "SE-UYB", "test@gmail.com");
        // seuyb.setnChecks(0);
        // clubNameLabel.setText(String.valueOf(seuyb.getnChecks()));
        // seuyb.removeLogbookEntries();
        // TODO - testing over

        for (Airplane airplane : flightBuddy.getCurrentClub().getAirplanes()) {
            MyClubListItem myClubListItem = new MyClubListItem(airplane, airplane.getRegistration(), airplane.getTotalFlightTime(), this);
            listItemMap.put(airplane.getRegistration(), myClubListItem);
        }

         updateAirplaneList();
    }


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


    private void controlCheckStatus(Airplane airplane, MyClubListItem myClubListItem) {
        if(airplane.isTimeForYearlyCheckNow()) {
            myClubListItem.applyYearlyCheck();

        } else if(airplane.isTimeForYearlyCheckSoon()) {
           myClubListItem.applySoonYearlyCheck();
        }
    }

    private void controlDistanceCheckStatus(Airplane airplane, MyClubListItem myClubListItem){
        if(airplane.isCheckNeededNow()) {
           myClubListItem.applyDistanceCheck();
        }

        // TODO - just nu implementeras inte en förvarning.
        /* else if(airplane.isCheckNeededSoon()) {
            myClubListItem.distanceCheckLabel.getStyleClass().add("warning-background");
            myClubListItem.distanceCheckLabel.setText("Flygplanet har flugit 250 h sedan tillsyn. Det är dags för tillsyn.");
        } */
    }

}
