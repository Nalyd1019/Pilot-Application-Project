package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.*;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookingController implements Initializable {

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    private FlyingClub flyingClub;

    private Airplane airplane;

    private boolean isBooked;

    private List<Integer> week = new ArrayList<>();

    BookingHandler currentClubBookingHandler = flightBuddy.getCurrentClub().getBookingHandler();

    @FXML private ComboBox pickFlightCombo;
    @FXML private ComboBox pickDayCombo;
    @FXML private AnchorPane bookFlightDetailView;
    @FXML private VBox bookFlightPage;
    @FXML private Label planeRegLabel;
    @FXML private Label startTimeLabel;
    @FXML private Label endTimeLabel;
    @FXML private Label dayLabel;
    @FXML private Button bookPlaneButton;

    @FXML private Button time7;
    @FXML private Button time9;
    @FXML private Button time11;
    @FXML private Button time13;
    @FXML private Button time15;
    @FXML private Button time17;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedFlight();
        selectedDay();
        populateButtons();
        populateDetailView();

    }

    @FXML public void openDetailView(){
        bookFlightDetailView.toFront();
    }

    @FXML public void closeDetailView(){
        bookFlightPage.toFront();
    }


    private void selectedFlight(){

        pickFlightCombo.getItems().addAll(flightBuddy.getCurrentClub().getAirplaneReg());

        pickFlightCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                planeRegLabel.setText(newValue);
            }
        });

    }

    private void selectedDay(){
        pickDayCombo.getItems().addAll(weekdays());

        pickDayCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                dayLabel.setText(newValue);
            }
        });

    }


    private void populateDetailView(){
        setStartAndEndTime();

    }

    private void populateButtons() {

        String hour7 = String.valueOf(getFlightHours().get(0));
        String hour9 = String.valueOf(getFlightHours().get(1));
        String hour11 = String.valueOf(getFlightHours().get(2));
        String hour13 = String.valueOf(getFlightHours().get(3));
        String hour15 = String.valueOf(getFlightHours().get(4));
        String hour17 = String.valueOf(getFlightHours().get(5));

        time7.setText(hour7 + ":00");
        time9.setText(hour9 + ":00");
        time11.setText(hour11 + ":00");
        time13.setText(hour13 + ":00");
        time15.setText(hour15 + ":00");
        time17.setText(hour17 + ":00");

    }

   /* private void populateButtons(){


        String hour11 = String.valueOf(getFlightHours().get(0));
        String hour13 = String.valueOf(getFlightHours().get(1));
        String hour15 = String.valueOf(getFlightHours().get(2));
        String hour17 = String.valueOf(getFlightHours().get(3));

        monday11.setText(hour11 + ":00");
        monday13.setText(hour13 + ":00");
        monday15.setText(hour15 + ":00");
        monday17.setText(hour17 + ":00");

        tuesday11.setText(hour11 + ":00");
        tuesday13.setText(hour13 + ":00");
        tuesday15.setText(hour15 + ":00");
        tuesday17.setText(hour17 + ":00");

        wednesday11.setText(hour11 + ":00");
        wednesday13.setText(hour13 + ":00");
        wednesday15.setText(hour15 + ":00");
        wednesday17.setText(hour17 + ":00");

        thursday11.setText(hour11 + ":00");
        thursday13.setText(hour13 + ":00");
        thursday15.setText(hour15 + ":00");
        thursday17.setText(hour17 + ":00");

        friday11.setText(hour11 + ":00");
        friday13.setText(hour13 + ":00");
        friday15.setText(hour15 + ":00");
        friday17.setText(hour17 + ":00");

        saturday11.setText(hour11 + ":00");
        saturday13.setText(hour13 + ":00");
        saturday15.setText(hour15 + ":00");
        saturday17.setText(hour17 + ":00");

        sunday11.setText(hour11 + ":00");
        sunday13.setText(hour13 + ":00");
        sunday15.setText(hour15 + ":00");
        sunday17.setText(hour17 + ":00");

    }

*/


    private List<Integer> getFlightHours(){
        List<Integer> flightHours = new ArrayList<>();

        for (int i = 7; i < 18; i++){
            if (isOdd(i) ){
                flightHours.add(i);
            }
        }
        return flightHours;
    }


    private boolean isOdd(int i){
        return i % 2 != 0;
    }

    private List<Integer> weekdays(){

        for (int i = 0; i < 7; i++){
            week.add(i);
        }
        return week;
    }

    private void setStartAndEndTime(){

        EventHandler<ActionEvent> eventTime7 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                startTimeLabel.setText("07:00");
                endTimeLabel.setText("09:00");
            }
        };

        EventHandler<ActionEvent> eventTime9 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                startTimeLabel.setText("09:00");
                endTimeLabel.setText("11:00");
            }
        };

        EventHandler<ActionEvent> eventTime11 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                startTimeLabel.setText("11:00");
                endTimeLabel.setText("13:00");
            }
        };

        EventHandler<ActionEvent> eventTime13 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                startTimeLabel.setText("13:00");
                endTimeLabel.setText("15:00");
            }
        };

        EventHandler<ActionEvent> eventTime15 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                startTimeLabel.setText("15:00");
                endTimeLabel.setText("17:00");
            }
        };

        EventHandler<ActionEvent> eventTime17 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                startTimeLabel.setText("17:00");
                endTimeLabel.setText("19:00");
            }
        };

        time7.setOnAction(eventTime7);
        time9.setOnAction(eventTime9);
        time11.setOnAction(eventTime11);
        time13.setOnAction(eventTime13);
        time15.setOnAction(eventTime15);
        time17.setOnAction(eventTime17);


    }

    @FXML private void createBooking(){

        String startTimeLabelText = startTimeLabel.getText();
        StringBuilder sb = new StringBuilder(startTimeLabelText);
        StringBuilder removeFromLabel = sb.delete(2,4);
        String newStartTime = removeFromLabel.toString();


        int startTime = Integer.parseInt(newStartTime);
        int day = 1;//Integer.parseInt(dayLabel.getText());
        Pilot pilot = flightBuddy.getCurrentUser();
      //  Airplane airplane = flightBuddy.getCurrentClub().
        Airplane airplane = flightBuddy.getCurrentClub().getAirplaneFromRegistration(planeRegLabel.getText());
        //TODO - lös sättet att komma åt flygplanet
     //   String registration = planeRegLabel.getText();

        currentClubBookingHandler.createBooking(startTime, day, pilot, airplane);

    }


}
