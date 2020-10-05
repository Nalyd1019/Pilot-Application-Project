package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.FlightBuddy;
import model.License;
import model.Pilot;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccountController implements Initializable {

    @FXML private TextField nameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField medicalCertTextField;
    @FXML private TextField flightCertTextField;
    @FXML private Label nameErrorLabel;
    @FXML private Label passwordErrorLabel;
    @FXML private Label emailErrorLabel;
    @FXML private Label medicalCertErrorLabel;
    @FXML private Label flightCertErrorLabel;

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

    // TODO - skriva javadoc

    @FXML public void updateUserInfo() {
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
    //Dessa metoder till en abstrakt superklass?
    private void errorControlColorChange(Control control){
        controlColorChange(control, Color.RED);
    }
    private void confirmedControlColorChange(Control control){
        controlColorChange(control,Color.GREEN);
    }
    private void controlColorChange(Control control, Color color){
        control.setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,new BorderWidths(1))));
    }
    private void errorLabelColorChange(Label label){
        label.setTextFill(Color.RED);
    }
    private boolean emptyTextField(TextField textField){
        if (textField.getText().isEmpty()){
            errorControlColorChange(textField);
            return true;
        }
        confirmedControlColorChange(textField);
        return false;
    }
}
