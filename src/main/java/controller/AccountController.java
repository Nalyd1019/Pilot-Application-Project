package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author Albert Lund
 * Controller for the fxml file myAccountPage
 */
public class AccountController extends AbstractInputErrorController implements Initializable {

    @FXML private TextField nameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField medicalCertTextField;
    @FXML private TextField flightCertTextField;
    @FXML private Label emailErrorLabel;
    @FXML private Button saveChangesButton;
    @FXML private Label changesSavedLabel;

    @FXML private PasswordField currentPasswordField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label currentPasswordErrorLabel;
    @FXML private Label confirmPasswordErrorLabel;
    @FXML private Label passwordChangedLabel;
    @FXML private Button changePasswordButton;



    /**
     * the initialize method that runs after the contructor and the FXML fields have been injected
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordChangedLabel.setVisible(false);
        changesSavedLabel.setVisible(false);
        nameTextField.setText(getFlightBuddy().getPilotName());
        emailTextField.setText(getFlightBuddy().getPilotEmail());
        medicalCertTextField.setText(Objects.requireNonNull(getFlightBuddy().getWantedeLicenseExpirationDate(getMedicalLicense())));
        flightCertTextField.setText(Objects.requireNonNull(getFlightBuddy().getWantedeLicenseExpirationDate(getFlightLicense())));

        saveChangesButton.setOnMouseClicked(mouseEvent -> updateUserInfo());
        changePasswordButton.setOnMouseClicked(mouseEvent -> changePassword());
        setupErrorListeners();
    }

    /**
     * saves the new data the user has put into the textFields to currentUser
     */
    @FXML private void updateUserInfo() {
        if (emailCheck()) {
            confirmedControlColorChange(emailTextField);
            boolean newName = emptyTextField(nameTextField);
            boolean newMedical = emptyTextField(medicalCertTextField);
            boolean newFlight = emptyTextField(flightCertTextField);
            if (!newName&&!newMedical&&!newFlight){
                getFlightBuddy().setPilotName(nameTextField.getText());
                getFlightBuddy().setPilotEmail(emailTextField.getText());
                getFlightBuddy().setPilotLicenseExpirationDate(medicalCertTextField.getText(),getMedicalLicense());
                getFlightBuddy().setPilotLicenseExpirationDate(flightCertTextField.getText(),getFlightLicense());

                changesSavedLabel.setVisible(true);
                CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> {
                    changesSavedLabel.setVisible(false);
                });
            }
        }
    }
    private boolean emailCheck() {
        if (getFlightBuddy().userExists(emailTextField.getText()) || getFlightBuddy().getPilotEmail().equals(emailTextField.getText())) {
            errorControlColorChange(emailTextField);
            emailErrorLabel.setText("Email redan registrerad");
            errorLabelColorChange(emailErrorLabel);
            return false;
        } else if (emptyTextField(emailTextField)) {
            emailErrorLabel.setText("Email måste fyllas i");
            errorLabelColorChange(emailErrorLabel);
            return false;
        } else return (validEmail(emailTextField, emailErrorLabel));
    }

    /**
     * Method that controls that valid input has been given and if so changes the current users password
     */
    private void changePassword(){
        if (!isCorrectCurrentPassword() || currentPasswordField.getText().isEmpty()){
            currentPasswordField.setText("");
            currentPasswordErrorLabel.setText("Fel nuvarande lösenord!");
        }
        else if (!isCorrectConfirmedPassword() || confirmPasswordField.getText().isEmpty()){
            confirmPasswordField.setText("");
            newPasswordField.setText("");
            confirmPasswordErrorLabel.setText("matchar ej med nytt lösenord!");
        }
        else {

            StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
            String pwField = newPasswordField.getText();
            System.out.println(pwField);
            String newPassword = encryptor.encryptPassword(pwField);
            getFlightBuddy().setPilotPassword(newPassword);

            passwordChangedLabel.setVisible(true);
            CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> {
                passwordChangedLabel.setVisible(false);
            });

            currentPasswordField.setText("");
            confirmPasswordField.setText("");
            newPasswordField.setText("");
        }
    }

    private Boolean isCorrectCurrentPassword(){
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        String userPassword = getFlightBuddy().getCurrentUser().getPassword();
        String inputPassword = currentPasswordField.getText();
        return encryptor.checkPassword(inputPassword, userPassword);
    }

    private Boolean isCorrectConfirmedPassword(){
        String newPassword = newPasswordField.getText();
        String confirmedPassword = confirmPasswordField.getText();
        return newPassword.equals(confirmedPassword);
    }

    /**
     * sets up listeners for the password fields to clear the error labels when user is trying to fix said error
     */
    void setupErrorListeners(){
        currentPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            currentPasswordErrorLabel.setText("");
        });
        newPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmPasswordErrorLabel.setText("");
        });
        confirmPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmPasswordErrorLabel.setText("");
        });
    }

}
