package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import model.Booking;
import model.BookingHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookingController implements Initializable {

    @FXML private FlowPane bookingFlowPane;

    private BookingHandler bookings = new BookingHandler();


    private void updateAvailableBookings(){
        bookingFlowPane.getChildren().clear();

        if (bookings.getBookings().size() != 0) {
            for(Booking booking : bookings.getBookings() ){
                bookingFlowPane.getChildren().add(new BookingItem(booking, this));
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateAvailableBookings();

    }

}
