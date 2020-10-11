package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.Event;
import javafx.scene.paint.Color;
import model.FlightBuddy;
import model.FlyingClub;
import model.License;
import model.Pilot;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountWizardController implements Initializable {

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

    @FXML private Button stepOne;
    @FXML private Button stepTwo;
    @FXML private Button stepThree;


    private FlightBuddy flightBuddy = FlightBuddy.getInstance();
    private Pilot pilot;
    private FlyingClub flyingClub;

    /**
     * the initialize method that runs after the contructor and the FXML fields have been injected
     * @param url ??
     * @param resourceBundle ??
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> options = FXCollections.observableArrayList();
        for (int i = 0; i<flightBuddy.getFlyingclubs().size(); i++){
            options.add(flightBuddy.getFlyingclubs().get(i).getClubName());
        }
        flyingClubComboBox.getItems().addAll(options);
        stepOne.setStyle("-fx-background-color: #7DAD6C");

    }



    /**
     * Checks if all the fields on the first page have correct input from the user, if so, moves on to the next step in
     * the account set-up wizard
     * @param event any event from the user
     */
    @FXML private void onClickfirstPageNext(Event event){
        flyingClub = nameToFlyingClub(flyingClubComboBox.getSelectionModel().getSelectedItem());
        if (flyingClub != null){
            confirmedControlColorChange(flyingClubComboBox);
            if (flyingClub.getPassword().equals(flyingClubPasswordTextField.getText())){
                confirmedControlColorChange(flyingClubPasswordTextField);
                pageTwo.toFront();
                stepOne.setStyle(null);
                stepTwo.setStyle("-fx-background-color: #7DAD6C");
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
        stepTwo.setStyle(null);
        stepOne.setStyle("-fx-background-color: #7DAD6C");
    }

    /**
     * Checks if all the fields on the second page have correct input from the user, if so, moves on to the next step in
     * the account set-up wizard. Also creates a new pilot/user in order to store the information given by the user.
     * @param event any event from the user
     */
    @FXML private void onClickpageTwoNext(Event event){
        if (checkUserInput()) {
            pilot = new Pilot(pageTwoPasswordTextField.getText(),pageTwoPasswordVerificationTextField.getText(),
                    pageTwoNameTextField.getText(),pageTwoEmailTextField.getText());
            pageThree.toFront();
            stepTwo.setStyle(null);
            stepThree.setStyle("-fx-background-color: #7DAD6C");
        }


    }

    /**
     * Takes the user back one step, from page three to page two
     * @param event any event from the user
     */
    @FXML private void onClickpageThreeBack(Event event){
        pageTwo.toFront();
        stepThree.setStyle(null);
        stepTwo.setStyle("-fx-background-color: #7DAD6C");
    }

    /**
     * This methods runs when the user press 'done' with creating a new user/pilot. The methods checks if the input in
     * the third step of the wizard is correct, if so, finalizes the creation of the new pilot and takes the user to the
     * main page, logged in to their new account.
     * @param event any event from the user
     */
    @FXML private void onClickpageThreeDone(Event event){
        boolean nStarts = validFlightTime(nStartsTextField);
        boolean flightHours = validFlightTime(flightHoursTextField);
        boolean flyingExpiration = dateIsSelected(flightLicenseExpiration);
        boolean medicalExpiration = dateIsSelected(medicalLicenseExpiration);
        if (nStarts&&flightHours&&flyingExpiration&&medicalExpiration){
            pilot.setnStarts(Integer.parseInt(nStartsTextField.getText()));
            pilot.setStartHours(Integer.parseInt(flightHoursTextField.getText()));
            pilot.addLicense(License.FLIGHT, flightLicenseExpiration.getValue());
            pilot.addLicense(License.MEDICAL, medicalLicenseExpiration.getValue());
            flyingClub.addMember(pilot);
            flightBuddy.setCurrentUser(pilot);
            flightBuddy.setCurrentClub(flyingClub);
            ViewNavigator.LoadView(ViewNavigator.START);
            stepThree.setStyle(null);
        }
    }
    private boolean checkUserInput(){
        boolean exists = !emailExists();
        boolean equalPassword = equalPassword();
        boolean validName = validName();
        return exists&&equalPassword&&validName;
    }
    //Flyttas till modellen?
    private boolean emailExists(){
        if (emptyTextField(pageTwoEmailTextField)) {
            return true;
        }
            if (flightBuddy.userExists(pageTwoEmailTextField.getText())){
                        errorLabelColorChange(emailErrorLabel);
                        emailErrorLabel.setText("Email redan registrerad");
                        errorControlColorChange(pageTwoEmailTextField);
                        return true;
                    }
        return false;
    }
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

    private boolean validFlightTime(TextField textField){
        try {
            if (Integer.parseInt(textField.getText())<0){
                errorControlColorChange(textField);
                return false;
        }
        }        //Totalt wack
        catch (NumberFormatException e){
            errorControlColorChange(textField);
            return false;
        }
        return true;
    }
    private FlyingClub nameToFlyingClub(String s){
        for (int i =0; i<flightBuddy.getFlyingclubs().size(); i++){
            if (flightBuddy.getFlyingclubs().get(i).getClubName().equals(s)){
                return flightBuddy.getFlyingclubs().get(i);
            }
        }
        return null;
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
    private boolean validName(){
        if (!pageTwoNameTextField.getText().isEmpty()){
            confirmedControlColorChange(pageTwoNameTextField);
            return true;
        }
        errorControlColorChange(pageTwoNameTextField);
        return false;
    }
    private boolean emptyTextField(TextField textField){
        if (textField.getText().isEmpty()){
            errorControlColorChange(textField);
            return true;
        }
        confirmedControlColorChange(textField);
        return false;
    }
    private boolean dateIsSelected(DatePicker datePicker){
        if (datePicker.getValue() == null){
            errorControlColorChange(datePicker);
            return false;
        }
        confirmedControlColorChange(datePicker);
        return true;
    }
}
