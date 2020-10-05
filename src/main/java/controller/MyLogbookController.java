package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.layout.AnchorPane;
import model.FlightBuddy;
import model.Pilot;

import java.net.URL;
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

    FlightBuddy flightBuddy = FlightBuddy.getInstance();
    Pilot pilot = flightBuddy.getCurrentUser();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onClickLogBookLabel();
    }

    @FXML
    private void onClickLogBookLabel(){
        logBookAnchorPane.toFront();
        logBookLabel.setUnderline(true);
        statisticsLabel.setUnderline(false);
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
}
