package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Booking;
import model.License;

import java.io.IOException;

public class BookingItem extends AnchorPane {

    @FXML
    private Label airplaneRegLabel;

    public BookingItem(Booking booking) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("bookingItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        airplaneRegLabel.setText(booking.getBookable().getRegistration());

    }
}







