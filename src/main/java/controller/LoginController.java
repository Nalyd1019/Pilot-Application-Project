package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.FlightBuddy;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private Button logInButton;
    @FXML private TextField emailTextField;
    @FXML private TextField passwordTextField;
    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void logIn(Event event){
        if (flightBuddy.validateLogIn(emailTextField.getText(),passwordTextField.getText())){
            ViewNavigator.LoadView(ViewNavigator.START);
        }
    }
}