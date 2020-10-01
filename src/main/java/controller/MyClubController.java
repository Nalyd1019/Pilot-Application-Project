package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import model.Airplane;
import model.FlightBuddy;
import model.FlyingClub;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
            MyClubListItem myClubListItem = new MyClubListItem(airplane, airplane.getRegistration(), airplane.getTotalFlightTime());
            listItemMap.put(airplane.getRegistration(), myClubListItem);
        }

         updateAirplaneList();
    }

    public void updateAirplaneList() {
        airplaneListFlowPane.getChildren().clear();

        List<Airplane> airplanes = flightBuddy.getCurrentClub().getAirplanes();

        for (Airplane airplane : airplanes) {
            MyClubListItem myClubListItem = listItemMap.get(airplane.getRegistration());
            airplaneListFlowPane.getChildren().add(myClubListItem);
        }
    }





}
