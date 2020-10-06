package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.Flight;
import model.FlightBuddy;
import model.Pilot;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MyLogbookController implements Initializable {

    @FXML
    private AnchorPane logBookAnchorPane;
    @FXML private AnchorPane statisticsAnchorPane;
    @FXML private Label statisticsLabel;
    @FXML private Label logBookLabel;
    @FXML private Label hoursLabel;
    @FXML private Label minutesLabel;
    @FXML private Label nStartsLabel;
    @FXML private TableView<String> flightsTableView;
    @FXML private AnchorPane lightBox;

    FlightBuddy flightBuddy = FlightBuddy.getInstance();
    Pilot pilot = flightBuddy.getCurrentUser();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lightBox.setVisible(false);
        onClickLogBookLabel();
    }

    @FXML
    private void onClickLogBookLabel(){
        logBookAnchorPane.toFront();
        logBookLabel.setUnderline(true);
        statisticsLabel.setUnderline(false);
        populateTable();
    }

    @FXML
    private void onClickStatisticsLabel(){
        statisticsAnchorPane.toFront();
        statisticsLabel.setUnderline(true);
        logBookLabel.setUnderline(false);
        nStartsLabel.setText(Integer.toString(pilot.getTotalNStarts()));
        hoursLabel.setText(Integer.toString(getTotalFlightHours(pilot.getPilotFlightTime())));
        minutesLabel.setText(Integer.toString(getRemainingMinutes(pilot.getPilotFlightTime(),
                getTotalFlightHours(pilot.getPilotFlightTime()))));
    }

    private int getTotalFlightHours(int totalMinutes){
        int k = 0;
        for (int i = totalMinutes; i>0; i=i-60){
            k++;
        }
        return k;
    }
    private int getRemainingMinutes (int totalFlightTime, int totalHours){
        return totalFlightTime-(totalHours*60);
    }
    private void populateTable(){
        List<Flight> flights = pilot.getLogbook().getPilotsEntries(pilot.getEmail());
        for (Flight flight : flights) {
            setCellValue(0,flight.getDate().toString());
            setCellValue(1,flight.getAirplaneRegistration());
            setCellValue(2,Integer.toString(flight.getnStarts()));
            setCellValue(3, flight.getnHours() + " h " + flight.getnMinutes() + " min");
            setCellValue(4,flight.getDeparturePlace());
            setCellValue(5, flight.getDestination());
            setCellValue(6,flight.getComment());
        }
    }
    private void setCellValue(int cell, String cellValue){
        flightsTableView.getColumns().get(cell).setText(cellValue);
    }
    @FXML private void onClickAddFlight() {
        lightBox.toFront();
        lightBox.setVisible(true);
    }
}