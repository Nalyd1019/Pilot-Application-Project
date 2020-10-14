package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.FlightBuddy;
import model.License;
import model.Pilot;

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
        nameTextField.setText(flightBuddy.getCurrentUser().getName());
        passwordTextField.setText(flightBuddy.getCurrentUser().getPassword());
        emailTextField.setText(flightBuddy.getCurrentUser().getEmail());
        medicalCertTextField.setText(Objects.requireNonNull(getWantedLicense(License.MEDICAL)).getExpirationDate());
        flightCertTextField.setText(Objects.requireNonNull(getWantedLicense(License.FLIGHT)).getExpirationDate());
    }

    private License getWantedLicense(String s){
        for (int i=0; i<flightBuddy.getCurrentUser().getLicenses().size(); i++){
            if (flightBuddy.getCurrentUser().getLicenses().get(i).getLicenseName().equals(s)) {
                return flightBuddy.getCurrentUser().getLicenses().get(i);
            }
        }
        return null;
    }

    /**
     * saves the new data the user has put into the textFields to currentUser
     */
    @FXML private void updateUserInfo() {
        Pilot pilot = flightBuddy.getCurrentUser();
        if ((!flightBuddy.userExists(emailTextField.getText()) || pilot.getEmail().equals(emailTextField.getText()))&&!emptyTextField(emailTextField)) {
            confirmedControlColorChange(emailTextField);
            boolean newName = emptyTextField(nameTextField);
            boolean newPassword = emptyTextField(passwordTextField);
            boolean newMedical = emptyTextField(medicalCertTextField);
            boolean newFlight = emptyTextField(flightCertTextField);
            if (!newName&&!newPassword&&!newMedical&&!newFlight){
                pilot.setName(nameTextField.getText());
                pilot.setPassword(passwordTextField.getText());
                pilot.setEmail(emailTextField.getText());
                Objects.requireNonNull(getWantedLicense(License.MEDICAL)).setExpirationDate(medicalCertTextField.getText());
                Objects.requireNonNull(getWantedLicense(License.FLIGHT)).setExpirationDate(flightCertTextField.getText());
            }
        }
        else {
            errorControlColorChange(emailTextField);
            emailErrorLabel.setText("Email redan registrerad");
            errorLabelColorChange(emailErrorLabel);
        }
    }
}
