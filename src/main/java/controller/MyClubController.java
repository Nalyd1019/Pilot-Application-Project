package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
    @FXML private FlowPane airplaneListFlowPane;

    private FlyingClub flyingClub;
    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    private Map<String, MyClubListItem> listItemMap = new HashMap<String, MyClubListItem>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clubNameLabel.setText(flightBuddy.getCurrentClub().getClubName());

        for (Airplane airplane : flightBuddy.getCurrentClub().getAirplanes()) {
            MyClubListItem myClubListItem = new MyClubListItem(airplane, airplane.getRegistration(), airplane.getTotalFlightTime(), this);
            listItemMap.put(airplane.getRegistration(), myClubListItem);
        }

         updateAirplaneList();
    }

    public void updateAirplaneList() {
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

    public void controlCheckStatus(Airplane airplane, MyClubListItem myClubListItem) {

        if(airplane.isTimeForYearlyCheckNow()) {
            myClubListItem.setStyle("-fx-border-color: red;");
            myClubListItem.checkIsDoneButton.toFront();
            myClubListItem.checkIsDoneButton.setText("Tillsyn utförd");
            myClubListItem.soonCheckLabel.getStyleClass().add("warning-background");
            myClubListItem.soonCheckLabel.setText("Dags för årstillsyn");

        } else if(airplane.isTimeForYearlyCheckSoon()) {
            myClubListItem.setStyle("-fx-border-color: #FFCC00;");
            myClubListItem.soonCheckLabel.getStyleClass().add("warning-background");
            myClubListItem.soonCheckLabel.setText("Årstillsyn: " + airplane.getYearlyCheckDate().toString() );

        } else {
            myClubListItem.setStyle("-fx-border-color: white");
            myClubListItem.checkIsDoneButton.toBack();
            myClubListItem.soonCheckLabel.setText("");
        }
    }

    private void controlDistanceCheckStatus(Airplane airplane, MyClubListItem myClubListItem){
        if(airplane.isCheckNeededNow()) {
            myClubListItem.distanceCheckButton.toFront();
            myClubListItem.distanceCheckLabel.setText("Flygplanet har flugit 250 h sedan tillsyn. Då är det dags för tillsyn.");
        }
        // TODO - just nu implementeras inte en förvarning.
        /* else if(airplane.isCheckNeededSoon()) {
            myClubListItem.distanceCheckLabel.getStyleClass().add("warning-background");
            myClubListItem.distanceCheckLabel.setText("Flygplanet har flugit 250 h sedan tillsyn. Det är dags för tillsyn.");
        } */
    }






// använder ej dessa för tillfället
    private void addCheckIsDoneButton(MyClubListItem clubListItem, Airplane airplane){
        Button button = new Button();
        clubListItem.getChildren().add(button);
        button.setText("Kontrollen har utförts");
    }

    /*
     void buttonIsClicked(Airplane airplane, MyClubListItem clubListItem){
        airplane.inspectYearlyCheck();
        airplane.yearlyCheckIsDone();
        clubListItem.getStyleClass().clear();
        controlCheckStatus(airplane, clubListItem);
    }
    */

}
