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
import model.Pilot;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AccountWizardController implements Initializable {

    @FXML private AnchorPane pageOne;
    @FXML private AnchorPane pageTwo;
    @FXML private AnchorPane pageThree;
    @FXML private ComboBox<String> flyingClubComboBox;
    @FXML private Button pageOneNext;
    @FXML private TextField pageTwoNameTextField;
    @FXML private TextField pageTwoEmailTextField;
    @FXML private TextField pageTwoPasswordTextField;
    @FXML private TextField pageTwoPasswordVerificationTextField;
    @FXML private Label emailErrorLabel;
    @FXML private TextField nStartsTextField;
    @FXML private TextField flightHoursTextField;
    @FXML private Label nStartsLabel;
    @FXML private Label flightHoursLabel;
    @FXML private TextField flyingClubPasswordTextField;

    private FlightBuddy flightBuddy = FlightBuddy.getInstance();
    private Pilot pilot;
    private FlyingClub flyingClub;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> options = FXCollections.observableArrayList();
        for (int i = 0; i<flightBuddy.getFlyingclubs().size(); i++){
            options.add(flightBuddy.getFlyingclubs().get(i).getClubName());
        }
        flyingClubComboBox.getItems().addAll(options);
    }

    private void flyingClubPasswordCheck(String password){
        String currentclub = flyingClubComboBox.getSelectionModel().getSelectedItem();

    }
    @FXML public void onClickfirstPageNext(Event event){
        flyingClub = nameToFlyingClub(flyingClubComboBox.getSelectionModel().getSelectedItem());
        if (flyingClub != null){
            confirmedControlColorChange(flyingClubComboBox);
            if (flyingClub.getPassword().equals(flyingClubPasswordTextField.getText())){
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
    @FXML public void onClickpageOneBack(Event event){
        ViewNavigator.LoadView(ViewNavigator.LOGIN);
    }
    @FXML public void onClickpageTwoBack(Event event){
        pageOne.toFront();
    }
    @FXML public void onClickpageTwoNext(Event event){
        if (checkUserInput()) {
            pilot = new Pilot(pageTwoPasswordTextField.toString(),pageTwoPasswordVerificationTextField.toString(),pageTwoNameTextField.toString(),pageTwoEmailTextField.toString());
            pageThree.toFront();
        }
    }
    @FXML public void onClickpageThreeBack(Event event){
        pageTwo.toFront();
    }
    @FXML public void onClickpageThreeDone(Event event){
        boolean nStarts = validFlightTime(nStartsTextField);
        boolean flightHours = validFlightTime(flightHoursTextField);
        if (nStarts&&flightHours){
            pilot.setnStarts(Integer.parseInt(nStartsTextField.getText()));
            pilot.setStartHours(Integer.parseInt(flightHoursTextField.getText()));
            flyingClub.addMember(pilot);
            flightBuddy.setCurrentUser(pilot);
            flightBuddy.setCurrentClub(flyingClub);
            ViewNavigator.LoadView(ViewNavigator.START);
        }
    }
    private boolean checkUserInput(){
        boolean exists = !userExists();
        boolean equalPassword = equalPassword();
        boolean validName = validName();
        return exists&&equalPassword&&validName;
    }
    //Flyttas till modellen?
    private boolean userExists(){
        if (emptyTextField(pageTwoEmailTextField)) {
            return true;
        }
            List<FlyingClub> flyingclubs = flightBuddy.getFlyingclubs();
            for (FlyingClub flyingclub : flyingclubs) {
                int n = flyingclub.getPilots().size();
                for (int j = 0; j < n; j++) {
                    if (flyingclub.getPilots().get(j).getEmail().equals(pageTwoEmailTextField.getText())) {
                        emailErrorLabel.setText("Email redan registrerad");
                        errorLabelColorChange(emailErrorLabel);
                        errorControlColorChange(pageTwoEmailTextField);
                        return true;
                    }
                }
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
        if (!emptyTextField(pageTwoPasswordTextField)&& pageTwoPasswordTextField.getText().equals(pageTwoPasswordVerificationTextField.getText())){
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
}
