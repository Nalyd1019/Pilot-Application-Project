package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import model.Booking;
import model.BookingSystem;
import model.FlightBuddy;
import model.License;

import java.net.URL;
import java.util.*;

/**
 * @Author
 * Controller for the fxml file startPage
 */
public class StartPageController implements Initializable {

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    @FXML private Label welcomeLabel;
    @FXML private Button bookButton;
    @FXML private FlowPane licenseFlowpane;
    @FXML private FlowPane bookingFlowpane;

    private Map<String, LicenseItem> licenseItemMap = new HashMap<String, LicenseItem>();
    private Map<Integer, BookingItem> bookingItemMap = new HashMap<>();

    private BookingSystem currentClubBookingHandler = flightBuddy.getCurrentClub().getBookingHandler();

    /**
     * the initialize method that runs after the constructor and the FXML fields have been injected. Also sets text for
     * welcome label, fills up both the licenseItemMap and the bookingItemMap and updates the lists in the view.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        welcomeLabel.setText("Välkommen " + flightBuddy.getCurrentUser().getName());
        welcomeButton();

        for(License license : flightBuddy.getPilotLicenses()){
            LicenseItem licenseItem = new LicenseItem(license);
            licenseItemMap.put(license.getLicenseName(),licenseItem);
        }

        for (Booking booking : currentClubBookingHandler.getUsersBookings(flightBuddy.getPilotEmail()) ){
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

    /**
     * method that populates the pilots start page with the pilots current bookings.
     */
    private void updateBookingList(){
        bookingFlowpane.getChildren().clear();


        List<Booking> bookings = currentClubBookingHandler.getUsersBookings(flightBuddy.getPilotEmail());

        for (Booking booking : bookings){
            BookingItem bookingItem = bookingItemMap.get(booking.getBookingID());
            bookingFlowpane.getChildren().add(bookingItem);
        }


    }

    /**
     * method that populates the pilots start page with the pilots current licenses
     */
    private void updateLicenseList(){
        licenseFlowpane.getChildren().clear();

        List<License> licenses = flightBuddy.getPilotLicenses();

        for(License license : licenses){
            LicenseItem licenseItem = licenseItemMap.get(license.getLicenseName());
            licenseFlowpane.getChildren().add(licenseItem);
            licenseItem.expiryDateCheck(license);
        }

    }



}