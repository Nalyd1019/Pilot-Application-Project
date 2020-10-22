package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.FlightBuddy;
import model.FlyingClub;
import model.Pilot;
import org.jasypt.util.password.StrongPasswordEncryptor;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller linked to the fxml file loginPage
 * @Author Albert Lund &
 */
public class LoginController implements Initializable {

    @FXML private Button logInButton;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordField;
    @FXML private TextField passwordTextField;
    @FXML private CheckBox showPasswordCheckBox;
    @FXML private Button createAccountButton;
    @FXML private Label userNotFoundLabel;
    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    private String password;

    /**
     * the initialize method that runs after the contructor and the FXML fields have been injected. Also sets up the
     * email and password textfields, as well as the password checkbox.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logInButton.setOnMouseClicked(mouseEvent -> logIn());
        createAccountButton.setOnMouseClicked(mouseEvent -> onClickNewUser());
        setupPasswordField();
        setupPasswordCheckBox();
        setupEmailField();

    }

    /**
     * Logs in the user and takes them to the startpage if they have provided a user with the matching password, else
     * the method keeps the user at the login page and provides an errormessage
     */
    public void logIn(){
        /*
        if (flightBuddy.validateLogIn(emailTextField.getText(),password)){
            ViewNavigator.LoadView(ViewNavigator.START);
        }

         */

        if (flightBuddy.userExists(emailTextField.getText())) {
            verifyLogin();
        }
        else {
            incorrectInput();
        }
    }

    private void incorrectInput(){
        userNotFoundLabel.setText("Fel E-mail eller lösenord");
        userNotFoundLabel.setStyle("-fx-text-fill: red");
    }

    private void verifyLogin(){
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        Pilot user = flightBuddy.getUser(emailTextField.getText());
        FlyingClub userClub = flightBuddy.getUserClub(emailTextField.getText());
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();

        if (userEmail.equals(emailTextField.getText()) && encryptor.checkPassword(password, userPassword)){
            flightBuddy.setCurrentUser(user);
            flightBuddy.setCurrentClub(userClub);
            ViewNavigator.LoadView(ViewNavigator.START);
        }
        else {
            incorrectInput();
        }

    }

    /**
     * Loads the accountWizard if the user clicks on the button to create a new user
     */
    public void onClickNewUser(){
        ViewNavigator.LoadView(ViewNavigator.NEWUSER);
    }

    private void setupEmailField(){
        emailTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            userNotFoundLabel.setText("");
        });
    }

    private void setupPasswordField(){
        passwordField.toFront();

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            password = newValue;
            passwordTextField.setText(password);
            userNotFoundLabel.setText("");
        });
        passwordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            password = newValue;
            passwordField.setText(password);
            userNotFoundLabel.setText("");
        });
    }

    private void setupPasswordCheckBox(){
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