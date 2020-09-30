package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.FlightBuddy;
import model.License;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccountController implements Initializable {

    @FXML private TextField nameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField medicalCertTextField;
    @FXML private TextField flightCertTextField;

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();

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

    @FXML public void updateUserInfo(){
        if (!flightBuddy.userExists(emailTextField.getText())
                ||flightBuddy.getCurrentUser().getEmail().equals(emailTextField.getText())){
        //TODO: Fixa så ny info kan sparas till användaren. Räcker det att skriva till currentuser?
        }
    }
}
