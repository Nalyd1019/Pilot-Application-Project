package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
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
    @FXML private PasswordField passwordField;
    @FXML private TextField passwordTextField;
    @FXML private CheckBox showPasswordCheckBox;
    @FXML private Button createAccountButton;
    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    private String password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logInButton.setOnMouseClicked(mouseEvent -> logIn());
        createAccountButton.setOnMouseClicked(mouseEvent -> onClickNewUser());
        setupPasswordField();
        setupPasswordCheckBox();


    }

    public void logIn(){
        if (flightBuddy.validateLogIn(emailTextField.getText(),password)){
            ViewNavigator.LoadView(ViewNavigator.START);
        }
    }

    public void onClickNewUser(){
        ViewNavigator.LoadView(ViewNavigator.NEWUSER);
    }

    public void setupPasswordField(){
        passwordField.toFront();

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            password = newValue;
            passwordTextField.setText(password);
        });
        passwordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            password = newValue;
            passwordField.setText(password);
        });
    }

    public void setupPasswordCheckBox(){
        showPasswordCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (showPasswordCheckBox.isSelected()){
                    passwordTextField.toFront();
                }
                else {
                    passwordField.toFront();
                }
            }
        });
    }


}