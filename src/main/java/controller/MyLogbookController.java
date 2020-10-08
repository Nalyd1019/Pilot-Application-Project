package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Flight;
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
    @FXML private TableView<Flight> flightsTableView;
    @FXML private AnchorPane lightBox;
    @FXML private AnchorPane lightBox2;
    @FXML private ComboBox<String> airPlaneComboBox;
    @FXML private DatePicker flightDatePicker;
    @FXML private TextField nStartsTextField;
    @FXML private TextField flightHoursTextField;
    @FXML private TextField takeOffTextField;
    @FXML private TextField destinationTextField;
    @FXML private TextArea commentTextArea;
    @FXML private TextField flightMinutesTextField;
    @FXML private TableColumn<Flight,String> dateCol;
    @FXML private TableColumn<Flight,String> airPlaneCol;
    @FXML private TableColumn<Flight,Integer> nStartsCol;
    @FXML private TableColumn<Flight,Integer> flightTimeCol;
    @FXML private TableColumn<Flight,String> takeOffCol;
    @FXML private TableColumn<Flight,String> destinationCol;
    @FXML private TableColumn<Flight,String> commentCol;

    FlightBuddy flightBuddy = FlightBuddy.getInstance();
    Pilot pilot = flightBuddy.getCurrentUser();
    ObservableList<Flight> data = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lightBox.setVisible(false);
        lightBox2.setVisible(false);
        onClickLogBookLabel();
        ObservableList<String> options = FXCollections.observableArrayList();
        for (int i=0; i<flightBuddy.getCurrentClub().getAirplanes().size();i++){
            options.add(flightBuddy.getCurrentClub().getAirplanes().get(i).getRegistration());
        }
        airPlaneComboBox.getItems().addAll(options);
        data.addAll(pilot.getLogbook().getPilotsEntries(pilot.getEmail()));
        setCellValueFactory(dateCol,"date");
        setCellValueFactory(airPlaneCol,"airplaneRegistration");
        setCellValueFactory(nStartsCol,"nStarts");
        setCellValueFactory(flightTimeCol,"nHours");
        setCellValueFactory(takeOffCol,"departurePlace");
        setCellValueFactory(destinationCol,"destination");
        setCellValueFactory(commentCol, "comment");
        flightsTableView.setItems(data);
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

    @FXML private void onClickAddFlight() {
        lightBox.toFront();
        lightBox.setVisible(true);
        lightBox2.toFront();
        lightBox2.setVisible(true);
    }
    @FXML private void exitLightBox(){
        lightBox2.toBack();
        lightBox.setVisible(false);
        lightBox.toBack();
        lightBox2.setVisible(false);
    }
    @FXML private void onClickLogFlight(){
        pilot.getLogbook().addLogbookEntry(flightDatePicker.getValue(),Integer.parseInt(flightHoursTextField.getText()),
                Integer.parseInt(flightMinutesTextField.getText()),Integer.parseInt(nStartsTextField.getText()),takeOffTextField.getText(),
                destinationTextField.getText(),commentTextArea.getText(),airPlaneComboBox.getSelectionModel().getSelectedItem(),
                pilot.getEmail());
        data.add(pilot.getLogbook().getFlights().get(pilot.getLogbook().getFlights().size()-1));
        clearInput();
        exitLightBox();
    }
    private <T> void setCellValueFactory(TableColumn<Flight,T> col, String attribute){
        col.setCellValueFactory(new PropertyValueFactory<>(attribute));
    }
    private void clearInput(){
        nStartsTextField.clear();
        flightHoursTextField.clear();
        takeOffTextField.clear();
        destinationTextField.clear();
        commentTextArea.clear();
        flightMinutesTextField.clear();
    }
}