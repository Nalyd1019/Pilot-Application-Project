package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.Booking;
import model.BookingHandler;

import java.awt.*;
import java.io.IOException;

public class BookingItem extends HBox{

    BookingController bookingController;
    Booking booking;

    @FXML private HBox bookingItemContainer;
    @FXML private Label startTimeLabel;
    @FXML private Label endTimeLabel;
    @FXML private Label availabilityLabel;
    @FXML private Button bookingButton;



    public BookingItem(Booking booking, BookingController bookingController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookingItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        this.booking = booking;
        this.bookingController = bookingController;

        startTimeLabel.setText(booking.getStartTime());
        endTimeLabel.setText(booking.getStartTime());


    }






>>>>>>> bookingController
}
