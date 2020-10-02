package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.Booking;
import model.BookingHandler;

import java.awt.*;
import java.io.IOException;

public class BookingItem {

    @FXML private HBox bookingItemContainer;
    @FXML private Label startTimeLabel;
    @FXML private Label endTimeLabel;
    @FXML private Label availabilityLabel;
    @FXML private Button bookingButton;

    private BookingController parentController;
    private BookingHandler bookingHandler;

    public BookingItem(BookingController parentController, BookingHandler bookingHandler){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookingItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
        this.bookingHandler = bookingHandler;
    }



}
