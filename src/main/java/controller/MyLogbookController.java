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

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @Author Albert Lund
 * Controller for the fxml file myLogBook.fxml
 */

public class MyLogbookController extends AbstractInputErrorController implements Initializable {

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
    @FXML private TableColumn<Flight,Integer> flightHoursTimeCol;
    @FXML private TableColumn<Flight,String> takeOffCol;
    @FXML private TableColumn<Flight,String> destinationCol;
    @FXML private TableColumn<Flight,String> commentCol;
    @FXML private TableColumn<Flight,Integer> flightMinutesTimeCol;

    FlightBuddy flightBuddy = FlightBuddy.getInstance();
    ObservableList<Flight> data = FXCollections.observableArrayList();


    /**
     * the initialize method that runs after the contructor and the FXML fields have been injected. Sets up the tableView
     * with its TableColumns. Also makes so the datepicker only allows past dates
     * @param url ??
     * @param resourceBundle ??
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lightBox.setVisible(false);
        lightBox2.setVisible(false);
        onClickLogBookLabel();
        ObservableList<String> options = FXCollections.observableArrayList();
        options.addAll(flightBuddy.getAirplaneReg());
        airPlaneComboBox.getItems().addAll(options);
        data.addAll(flightBuddy.getPilotsEntries());
        setCellValueFactory(dateCol,"date");
        setCellValueFactory(airPlaneCol,"airplaneRegistration");
        setCellValueFactory(nStartsCol,"nStarts");
        setCellValueFactory(flightHoursTimeCol,"nHours");
        setCellValueFactory(flightMinutesTimeCol,"nMinutes");
        setCellValueFactory(takeOffCol,"departurePlace");
        setCellValueFactory(destinationCol,"destination");
        setCellValueFactory(commentCol, "comment");
        flightsTableView.setItems(data);
        flightDatePicker.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
            }
        });
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
        nStartsLabel.setText(Integer.toString(flightBuddy.getPilotNStarts()));
        hoursLabel.setText(Integer.toString(getTotalFlightHours(flightBuddy.getPilotFlightTime())));
        minutesLabel.setText(Integer.toString(getRemainingMinutes(flightBuddy.getPilotFlightTime(),
                getTotalFlightHours(flightBuddy.getPilotFlightTime()))));
    }

    private int getTotalFlightHours(int totalMinutes){
        int k = 0;
        for (int i = totalMinutes; i>=60; i=i-60){
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
        boolean date = comboBoxHasSelectedValue(flightDatePicker);
        boolean nStarts = validIntegerInTextField(nStartsTextField);
        boolean flightHours = validIntegerInTextField(flightHoursTextField);
        boolean flightMinutes = validIntegerInTextField(flightMinutesTextField) && Integer.parseInt(flightMinutesTextField.getText())<60;
        boolean takeoff = !emptyTextField(takeOffTextField);
        boolean destination = !emptyTextField(destinationTextField);
        boolean airplane = comboBoxHasSelectedValue(airPlaneComboBox);
        boolean comment = commentCheck();
        if (date&&nStarts&&flightHours&&flightMinutes&&takeoff&&destination&&airplane&&comment) {
            flightBuddy.createPilotLogbookEntry(flightDatePicker.getValue(), Integer.parseInt(flightHoursTextField.getText()),
                    Integer.parseInt(flightMinutesTextField.getText()), Integer.parseInt(nStartsTextField.getText()), takeOffTextField.getText(),
                    destinationTextField.getText(), commentTextArea.getText(), airPlaneComboBox.getSelectionModel().getSelectedItem(),
                    flightBuddy.getPilotEmail());
            flightBuddy.addAirplaneLogBookEntry(flightDatePicker.getValue(), Integer.parseInt(flightHoursTextField.getText()),
                    Integer.parseInt(flightMinutesTextField.getText()), Integer.parseInt(nStartsTextField.getText()), takeOffTextField.getText(),
                    destinationTextField.getText(), commentTextArea.getText(), airPlaneComboBox.getSelectionModel().getSelectedItem(),
                    flightBuddy.getPilotEmail());
            data.add(flightBuddy.getPilotLastEntry());
            clearInput();
            exitLightBox();
        }
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
    //TODO Temporär?
    private boolean commentCheck(){
        if (commentTextArea.getText().length()>26){
            errorControlColorChange(commentTextArea);
            return false;
        }
        confirmedControlColorChange(commentTextArea);
        return true;
    }
}