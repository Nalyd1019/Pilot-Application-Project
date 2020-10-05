package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import model.Booking;
import model.BookingHandler;
import model.FlightBuddy;
import model.License;

import java.net.URL;
import java.util.*;

public class StartPageController implements Initializable {

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    @FXML private Label welcomeLabel;
    @FXML private Button bookButton;
    @FXML private FlowPane licenseFlowpane;
    @FXML private FlowPane bookingFlowpane;

    private Map<String, LicenseItem> licenseItemMap = new HashMap<String, LicenseItem>();
    private Map<Integer, BookingItem> bookingItemMap = new HashMap<>();

    BookingHandler currentClubBookingHandler = flightBuddy.getCurrentClub().getBookingHandler();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        welcomeLabel.setText("Välkommen " + flightBuddy.getCurrentUser().getName());
        welcomeButton();

        for(License license : flightBuddy.getCurrentUser().getLicenses()){
            LicenseItem licenseItem = new LicenseItem(license);
            licenseItemMap.put(license.getLicenseName(),licenseItem);
        }

        for (Booking booking : currentClubBookingHandler.getUsersBookings(flightBuddy.getCurrentUser().getEmail()) ){
            BookingItem bookingItem = new BookingItem(booking);
            bookingItemMap.put(booking.getBookingID(), bookingItem);
        }

        updateBookingList();
        updateLicenseList();

    }

    private void welcomeButton(){
        bookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ViewNavigator.LoadView(ViewNavigator.BOOKING);
            }
        });
    }

    private void updateBookingList(){
        bookingFlowpane.getChildren().clear();


        List<Booking> bookings = currentClubBookingHandler.getUsersBookings(flightBuddy.getCurrentUser().getEmail());

        for (Booking booking : bookings){
            BookingItem bookingItem = bookingItemMap.get(booking.getBookingID());
            bookingFlowpane.getChildren().add(bookingItem);
        }


    }

    private void updateLicenseList(){
        licenseFlowpane.getChildren().clear();

        List<License> licenses = flightBuddy.getCurrentUser().getLicenses();

        for(License license : licenses){
            LicenseItem licenseItem = licenseItemMap.get(license.getLicenseName());
            licenseFlowpane.getChildren().add(licenseItem);
        }

    }

}