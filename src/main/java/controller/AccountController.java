package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.event.Event;
import model.FlightBuddy;
import model.FlyingClub;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {

    @FXML private AnchorPane pageOne;
    @FXML private AnchorPane pageTwo;
    @FXML private AnchorPane pageThree;
    @FXML private ComboBox<String> flyingClubComboBox;
    @FXML private Button pageOneNext;
    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> options = FXCollections.observableArrayList();
        for (int i = 0; i<flightBuddy.getFlyingclubs().size(); i++){
            options.add(flightBuddy.getFlyingclubs().get(i).getClubName());
        }
        flyingClubComboBox.getItems().addAll(options);
    }

    private void flyingClubPasswordCheck(String password){
        String currentclub =  flyingClubComboBox.getSelectionModel().getSelectedItem();

    }
    @FXML public void onClickfirstPageNext(Event event){
        pageTwo.toFront();
    }
    @FXML public void onClickpageOneBack(Event event){
        ViewNavigator.LoadView(ViewNavigator.LOGIN);
    }
    @FXML public void onClickpageTwoBack(Event event){
        pageOne.toFront();
    }
}
