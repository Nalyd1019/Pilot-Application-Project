package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.Event;
import model.FlightBuddy;
import model.License;
import model.Pilot;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountWizardController extends AbstractInputErrorController implements Initializable {

    @FXML private AnchorPane pageOne;
    @FXML private AnchorPane pageTwo;
    @FXML private AnchorPane pageThree;
    @FXML private ComboBox<String> flyingClubComboBox;
    @FXML private TextField pageTwoNameTextField;
    @FXML private TextField pageTwoEmailTextField;
    @FXML private TextField pageTwoPasswordTextField;
    @FXML private TextField pageTwoPasswordVerificationTextField;
    @FXML private Label emailErrorLabel;
    @FXML private TextField nStartsTextField;
    @FXML private TextField flightHoursTextField;
    @FXML private TextField flyingClubPasswordTextField;
    @FXML private DatePicker flightLicenseExpiration;
    @FXML private DatePicker medicalLicenseExpiration;




    private FlightBuddy flightBuddy = FlightBuddy.getInstance();
    private Pilot pilot;

    /**
     * the initialize method that runs after the contructor and the FXML fields have been injected
     * @param url ??
     * @param resourceBundle ??
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> options = FXCollections.observableArrayList();
        for (int i = 0; i<flightBuddy.getNFlyingClubs(); i++){
            options.add(flightBuddy.getFlyingClubName(i));
        }
        flyingClubComboBox.getItems().addAll(options);

    }



    /**
     * Checks if all the fields on the first page have correct input from the user, if so, moves on to the next step in
     * the account set-up wizard
     * @param event any event from the user
     */
    @FXML private void onClickfirstPageNext(Event event){
        if (comboBoxHasSelectedValue(flyingClubComboBox)){
            confirmedControlColorChange(flyingClubComboBox);
            if (flightBuddy.flyingClubMatchingPassword(flyingClubComboBox.getSelectionModel().getSelectedItem(),flyingClubPasswordTextField.getText())){
                confirmedControlColorChange(flyingClubPasswordTextField);
                pageTwo.toFront();
            }
            else{
                errorControlColorChange(flyingClubPasswordTextField);
            }
        }
        else{
            errorControlColorChange(flyingClubComboBox);
        }
    }

    /**
     * returns the user to the log in page
     * @param event any event from the user
     */
    @FXML private void onClickpageOneBack(Event event){
        ViewNavigator.LoadView(ViewNavigator.LOGIN);
    }

    /**
     * Takes the user back one step, from page two to page one
     * @param event any event from the user
     */
    @FXML private void onClickpageTwoBack(Event event){
        pageOne.toFront();

    }

    /**
     * Checks if all the fields on the second page have correct input from the user, if so, moves on to the next step in
     * the account set-up wizard. Also creates a new pilot/user in order to store the information given by the user.
     * @param event any event from the user
     */
    @FXML private void onClickpageTwoNext(Event event){
        if (checkUserInput()) {
            StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
            pilot = new Pilot(encryptor.encryptPassword(pageTwoPasswordTextField.getText()),
                    pageTwoNameTextField.getText(),pageTwoEmailTextField.getText());
            pageThree.toFront();

        }


    }

    /**
     * Takes the user back one step, from page three to page two
     * @param event any event from the user
     */
    @FXML private void onClickpageThreeBack(Event event){
        pageTwo.toFront();

    }

    /**
     * This methods runs when the user press 'done' with creating a new user/pilot. The methods checks if the input in
     * the third step of the wizard is correct, if so, finalizes the creation of the new pilot and takes the user to the
     * main page, logged in to their new account.
     * @param event any event from the user
     */
    @FXML private void onClickpageThreeDone(Event event){
        boolean nStarts = validIntegerInTextField(nStartsTextField);
        boolean flightHours = validIntegerInTextField(flightHoursTextField);
        boolean flyingExpiration = comboBoxHasSelectedValue(flightLicenseExpiration);
        boolean medicalExpiration = comboBoxHasSelectedValue(medicalLicenseExpiration);
        if (nStarts&&flightHours&&flyingExpiration&&medicalExpiration){
            pilot.setnStarts(Integer.parseInt(nStartsTextField.getText()));
            pilot.setStartHours(Integer.parseInt(flightHoursTextField.getText()));
            pilot.addLicense(FlightBuddy.FLIGHTLICENSE, flightLicenseExpiration.getValue());
            pilot.addLicense(FlightBuddy.MEDICALLICENSE, medicalLicenseExpiration.getValue());
            flightBuddy.addMemberToCurrentClub(pilot);
            flightBuddy.setCurrentUser(pilot);
            ViewNavigator.LoadView(ViewNavigator.START);
        }
    }
    private boolean checkUserInput(){
        boolean exists = !emailExists();
        boolean equalPassword = equalPassword();
        boolean validName = !emptyTextField(pageTwoNameTextField);
        return exists&&equalPassword&&validName;
    }
    //Flyttas till modellen?
    private boolean emailExists(){
        if (emptyTextField(pageTwoEmailTextField)) {
            return true;
        }
        else if (flightBuddy.userExists(pageTwoEmailTextField.getText())){
                        errorLabelColorChange(emailErrorLabel);
                        emailErrorLabel.setText("Email redan registrerad");
                        errorControlColorChange(pageTwoEmailTextField);
                        return true;
                    } else return !(validEmail(pageTwoEmailTextField,emailErrorLabel));
    }
    private boolean equalPassword(){
        if (!emptyTextField(pageTwoPasswordTextField)&& pageTwoPasswordTextField.getText().equals
                (pageTwoPasswordVerificationTextField.getText())){
            confirmedControlColorChange(pageTwoPasswordTextField);
            confirmedControlColorChange(pageTwoPasswordVerificationTextField);
            return true;
        }
        errorControlColorChange(pageTwoPasswordTextField);
        errorControlColorChange(pageTwoPasswordVerificationTextField);
        return false;
    }

}
