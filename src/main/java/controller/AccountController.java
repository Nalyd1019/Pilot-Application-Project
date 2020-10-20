package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.FlightBuddy;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccountController extends AbstractInputErrorController implements Initializable {

    @FXML private TextField nameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField medicalCertTextField;
    @FXML private TextField flightCertTextField;
    @FXML private Label emailErrorLabel;

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

    /**
     * the initialize method that runs after the contructor and the FXML fields have been injected
     * @param url ??
     * @param resourceBundle ??
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTextField.setText(flightBuddy.getPilotName());
        passwordTextField.setText(flightBuddy.getPilotPassword());
        emailTextField.setText(flightBuddy.getPilotEmail());
        medicalCertTextField.setText(Objects.requireNonNull(flightBuddy.getWantedeLicenseExpirationDate(FlightBuddy.MEDICALLICENSE)));
        flightCertTextField.setText(Objects.requireNonNull(flightBuddy.getWantedeLicenseExpirationDate(FlightBuddy.FLIGHTLICENSE)));
    }

    /**
     * saves the new data the user has put into the textFields to currentUser
     */
    @FXML private void updateUserInfo() {
        if (emailCheck()) {
            confirmedControlColorChange(emailTextField);
            boolean newName = emptyTextField(nameTextField);
            boolean newPassword = emptyTextField(passwordTextField);
            boolean newMedical = emptyTextField(medicalCertTextField);
            boolean newFlight = emptyTextField(flightCertTextField);
            if (!newName&&!newPassword&&!newMedical&&!newFlight){
                flightBuddy.setPilotName(nameTextField.getText());
                flightBuddy.setPilotPassword(passwordTextField.getText());
                flightBuddy.setPilotEmail(emailTextField.getText());
                flightBuddy.setPilotLicenseExpirationDate(medicalCertTextField.getText(),FlightBuddy.MEDICALLICENSE);
                flightBuddy.setPilotLicenseExpirationDate(flightCertTextField.getText(),FlightBuddy.FLIGHTLICENSE);
            }
        }
    }
    private boolean emailCheck(){
        if (flightBuddy.userExists(emailTextField.getText())||flightBuddy.getPilotEmail().equals(emailTextField.getText())){
            errorControlColorChange(emailTextField);
            emailErrorLabel.setText("Email redan registrerad");
            errorLabelColorChange(emailErrorLabel);
            return false;
        }
        else if (emptyTextField(emailTextField)){
            emailErrorLabel.setText("Email måste fyllas i");
            errorLabelColorChange(emailErrorLabel);
            return false;
        }
        else return  (validEmail(emailTextField,emailErrorLabel));
    }
}
