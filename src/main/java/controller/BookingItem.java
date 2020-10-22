package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Booking;

import java.io.IOException;

/**
 * @Author Dylan Osolian
 *  A controller for BookingItem that represents one booking.
 */
public class BookingItem extends AnchorPane {

    @FXML
    private Label airplaneRegLabel;
    @FXML Label dayLabel;
    @FXML Label timeLabel;

    /**
     * Constructor for the BookingItem, initializes and makes the fxml file display relevant informatioon about the booking.
     * @param booking The booking which the Item represents.
     */
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
        dayLabel.setText(getDayFromInt(booking.getDay()));
        timeLabel.setText(String.valueOf("Kl: " + booking.getStartTime()) + "-" + String.valueOf(booking.getStartTime()+2));

    }

    /**
     * Method that returns which day of the week an int represents.
     * @param i The int which represents a day of the week.
     * @return A string which is a day of the week
     */
    private String getDayFromInt(int i){
        switch (i){
            case 1:
                return "Måndag";
            case 2:
                return "Tisdag";
            case 3:
                return "Onsdag";
            case 4:
                return "Torsdag";
            case 5:
                return "Fredag";
            case 6:
                return "Lördag";
            case 7:
                return "Söndag";
            default:
                return "Invalid day";
        }

    }
}







