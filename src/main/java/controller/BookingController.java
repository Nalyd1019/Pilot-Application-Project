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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.*;

import java.awt.print.Book;
import java.net.URL;
import java.util.*;

/**
 * @author Malin Rosén
 * Controller for the booking system that populates the buttons and creates the bookings.
 */

public class BookingController implements Initializable {

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    private List<Integer> weekNr = new ArrayList<>();
    private List<String> weekNames = new ArrayList<>();
    private List<Button> buttonList = new ArrayList<>();

    private Map<Integer, String> weekdayNameMap = new HashMap<>();

    BookingSystem currentClubBookingHandler = flightBuddy.getCurrentClub().getBookingHandler();

    @FXML private ComboBox pickFlightCombo;
    @FXML private ComboBox pickDayCombo;
    @FXML private AnchorPane bookFlightDetailView;
    @FXML private VBox bookFlightPage;
    @FXML private Label planeRegLabel;
    @FXML private Label startTimeLabel;
    @FXML private Label endTimeLabel;
    @FXML private Label dayLabel;
    @FXML private Label pickComboBoxLabel;
    @FXML private Button bookPlaneButton;
    @FXML private AnchorPane lightbox;

    @FXML private Button time7;
    @FXML private Button time9;
    @FXML private Button time11;
    @FXML private Button time13;
    @FXML private Button time15;
    @FXML private Button time17;


    /**
     * The initialize method that populates all the necessary fields.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        weekdaysNames();
        weekdaysNr();

        for (int weekdayNumber : weekNr){
            weekdayNameMap.put(weekdayNumber, weekNames.get(weekdayNumber-1));
        }

        selectedFlight();
        selectedDay();
        populateButtons();
        populateDetailView();

    }

    /**
     * A method that opens the lightbox that contains the detail view when the user clicks on a timeslot.
     */
    @FXML public void openLightBox(){

        if (pickFlightCombo.getValue() == null && pickDayCombo.getValue() == null) {

            pickComboBoxLabel.setText("Vänligen välj en dag och ett flyg.");

        } else if (pickDayCombo.getValue() == null){

            pickComboBoxLabel.setText("Vänligen välj en dag.");

        } else if (pickFlightCombo.getValue() == null){

            pickComboBoxLabel.setText("Vänligen välj ett flyg.");

        } else {
            pickComboBoxLabel.setText("");
            lightbox.toFront();
        }
    }


    /**
     * Closes the lightbox/detail view to show the timeslots again.
     */
    @FXML public void closeDetailView(){
        bookFlightPage.toFront();
        lightbox.toBack();
    }

    /**
     * A method that checks what flight registration number is currently selected in the pickFlight combobox and
     * passes it to the label that shows the registration number in the detailview.
     * It also updates the makeBooked method so the booked time slots change with what flight is currently selected.
     */
    private void selectedFlight(){
        pickFlightCombo.getItems().addAll(flightBuddy.getCurrentClub().getAirplaneReg());

        pickFlightCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                planeRegLabel.setText(newValue);
                makeBooked();
            }
        });

    }

    /**
     * A method that checks what day is currently selected in the pickDay combobox and passes it to the
     * label that shows the day in the detailview.
     * It also updates the makeBooked method so the booked time slots change with what day is currently selected.
     */
    private void selectedDay(){
        pickDayCombo.getItems().addAll(weekdayNameMap.values());

        pickDayCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

              dayLabel.setText(String.valueOf(newValue));
              makeBooked();

            }
        });

    }


    /**
     * A method that populates the detail view with all the necessary data.
     */
    private void populateDetailView(){
        setStartAndEndTime();

    }

    /**
     * A method that sets the value of the buttons on the time slots page.
     */
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

        buttonList.add(time7);
        buttonList.add(time9);
        buttonList.add(time11);
        buttonList.add(time13);
        buttonList.add(time15);
        buttonList.add(time17);

    }

    /**
     * A method that calculates what times the user will be able to book a plane and puts them in a list.
     * @return a list of Integers that are the set flight hours.
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

    /**
     * A method that calculates if a number is odd.
     * @param i is the number that will be checked if its odd or not.
     * @return true if the number is odd.
     */
    private boolean isOdd(int i){
        return i % 2 != 0;
    }

    /**
     * A method that puts a set of ints into a list to represent days of the week.
     */
    private void weekdaysNr(){
        for (int i = 1; i < 8; i++){
            weekNr.add(i);
        }
    }

    /**
     * A method that changes the background color of the buttons with time slots that are booked.
     */
    private void makeBooked(){

        List<Button> bookedButtons = new ArrayList<>();

        for (Booking booking : currentClubBookingHandler.getBookings()) {
            for (Button button : buttonList) {
                if (isBooked(booking, button)){
                    button.setStyle("-fx-background-color: #C4C4C4");
                    bookedButtons.add(button);
                    button.setDisable(true);
                    break;
                } else if (!bookedButtons.contains(button)) {
                    button.setStyle("-fx-background-color: #7CAD6C");
                    button.setDisable(false);
                }
            }
        }
    }

    /**
     * A method that checks if the time slot is booked.
     * @param booking The booking that is checked if it's booked or not.
     * @param button The button that is pressed to book a flight.
     * @return A bool that confirms if the time slot is booked or not.
     */
    private boolean isBooked(Booking booking, Button button){
        if (weekdayNameMap.get(booking.getDay()).equals(pickDayCombo.getValue())){
            if (booking.getBookable().getRegistration().equals(pickFlightCombo.getValue())){
                if (button.getText().matches(booking.getStartTime() + ":00")){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * A method that puts all days of the week into a list.
     */
    private void weekdaysNames(){
        String monday = "måndag";
        String tuesday = "tisdag";
        String wednesday = "onsdag";
        String thursday = "torsdag";
        String friday = "fredag";
        String saturday = "lördag";
        String sunday = "söndag";

        weekNames.add(monday);
        weekNames.add(tuesday);
        weekNames.add(wednesday);
        weekNames.add(thursday);
        weekNames.add(friday);
        weekNames.add(saturday);
        weekNames.add(sunday);

    }

    /**
     * A method that sets the start and end times of each flight inside the detail view.
     */
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

    /**
     * A method that creates a booking.
     */
    @FXML private void createBooking(){


                String startTimeLabelText = startTimeLabel.getText();
                StringBuilder sb = new StringBuilder(startTimeLabelText);
                StringBuilder removeFromLabel = sb.delete(2,5);
                String newStartTime = removeFromLabel.toString();


                int startTime = Integer.parseInt(newStartTime);

                int day = 0;

                for (int i = 1; i < 8; i++) {
                    if (weekdayNameMap.get(i).matches(dayLabel.getText())){
                        day = i;
                        break;
                    }
                }

                iBorrower pilotEmail = flightBuddy.getCurrentUser();
                iBookable registration = flightBuddy.getCurrentClub().getAirplaneFromRegistration(planeRegLabel.getText());


                currentClubBookingHandler.createBooking(startTime, day, pilotEmail, registration);
                lightbox.toBack();
                makeBooked();

    }


}
