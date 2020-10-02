package controller;

import javafx.fxml.FXMLLoader;
import model.Booking;

import java.io.IOException;

public class BookingItemController {

    private BookingController parentController;

    public BookingItemController(BookingController parentController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookingItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
    }



}
