package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.Airplane;

import java.io.IOException;

/**
 * @author Lisa Samuelsson
 * Controller for the fxml file myClubPageListItem which shows information about an airplane belonging to the club.
 */
public class MyClubListItem extends AnchorPane {

    @FXML private Label registrationLabel;
    @FXML private Label flightTimeLabel;
    @FXML private Label soonCheckLabel;
    @FXML private Button checkIsDoneButton;
    @FXML private Label distanceCheckLabel;
    @FXML private Button distanceCheckButton;
    @FXML private AnchorPane myClubPageListItem;

    private Airplane airplane;


    MyClubListItem(Airplane airplane, String registration, int flightTime) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("myClubPageListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.airplane = airplane;
        this.registrationLabel.setText(registration);
        this.flightTimeLabel.setText("Flygtid: " + flightTime/60 + " h " + flightTime%60 + " min");
        this.soonCheckLabel.setText("Datum för årskontroll: " + airplane.getYearlyCheckDate().toString());
    }

    @FXML
    protected void onClick(Event event){
        buttonIsClicked();
    }

    @FXML
    protected void onDistanceButtonClick(Event event) {
        distanceCheckButtonClicked();
    }


    /**
     * Method that is called when yearly check button is clicked
     * Makes appropriate changes in GUI
     */
    private void buttonIsClicked(){
        airplane.yearlyCheckIsDone();
        checkIsDoneButton.toBack();
        soonCheckLabel.getStyleClass().remove("warning-background");
        soonCheckLabel.setText("");
        myClubPageListItem.getStyleClass().add("check-done-border");
    }


    /**
     * Method that is called when distance check button is clicked
     * Makes appropriate changes in GUI
     */
    private void distanceCheckButtonClicked() {
        airplane.distanceCheckIsDone();
        distanceCheckButton.toBack();
        myClubPageListItem.getStyleClass().add("check-done-border");
        distanceCheckLabel.getStyleClass().remove("warning-background");
        distanceCheckLabel.setText("");
    }


    /**
     * Method that changes GUI when yearly check is needed
     */
    void applyYearlyCheck() {
        checkIsDoneButton.toFront();
        checkIsDoneButton.setText("Årskontroll klar");
        myClubPageListItem.getStyleClass().add("check-now-border");
        soonCheckLabel.getStyleClass().add("warning-background");
        soonCheckLabel.setText("Dags för årskontroll");
    }


    /**
     * Method that changes GUI when yearly check soon is needed
     */
    void applySoonYearlyCheck() {
         soonCheckLabel.getStyleClass().add("warning-background");
        myClubPageListItem.getStyleClass().add("check-soon-border");
        soonCheckLabel.setText("Årskontroll: " + airplane.getYearlyCheckDate().toString() );
     }


    /**
     * Method that changes GUI when distance check is needed
     */
    void applyDistanceCheck() {
        myClubPageListItem.getStyleClass().add("check-now-border");
        distanceCheckLabel.getStyleClass().add("warning-background");
         distanceCheckButton.toFront();
         distanceCheckLabel.setText("Dags för ny tillsyn!");
     }


    /**
     * Method that changes GUI when distance check soon is needed, displays hours until check
     */
    void applySoonDistanceCheck() {
        int nChecks = airplane.getnChecks();
        int totalTime = airplane.getTotalFlightTime();
        int checkedTime = (nChecks*15000);

        int minutesUntilCheck = 15000-(totalTime-checkedTime);
        int hoursUntilCheck = minutesUntilCheck/60;

        distanceCheckLabel.setText(" Dags för tillsyn om " + hoursUntilCheck + " h");
    }
}
