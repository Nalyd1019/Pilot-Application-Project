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


public class MyClubListItem extends AnchorPane {

    @FXML private Label registrationLabel;
    @FXML private Label flightTimeLabel;
    @FXML private Label soonCheckLabel;
    @FXML private Button checkIsDoneButton;
    @FXML private Label distanceCheckLabel;
    @FXML private Button distanceCheckButton;


    private MyClubController myClubController;
    private Airplane airplane;


    MyClubListItem(Airplane airplane, String registration, int flightTime, MyClubController myClubController) {
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
        this.flightTimeLabel.setText("Flygtid: " + flightTime + " minuter");
        this.myClubController = myClubController;
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
        setStyle("-fx-border-color: green");
        checkIsDoneButton.toBack();
        soonCheckLabel.getStyleClass().remove("warning-background");
        soonCheckLabel.setText("");
    }


    /**
     * Method that is called when distance check button is clicked
     * Makes appropriate changes in GUI
     */
    private void distanceCheckButtonClicked() {
        airplane.distanceCheckIsDone();
        distanceCheckButton.toBack();
        distanceCheckLabel.getStyleClass().remove("warning-background");
        distanceCheckLabel.setText("");
    }


    /**
     * Method that changes GUI when yearly check is needed
     */
    void applyYearlyCheck() {
        setStyle("-fx-border-color: red;");
        checkIsDoneButton.toFront();
        checkIsDoneButton.setText("Tillsyn utförd");
        soonCheckLabel.getStyleClass().add("warning-background");
        soonCheckLabel.setText("Dags för årstillsyn");
    }


    /**
     * Method that changes GUI when yearly check soon is needed
     */
    void applySoonYearlyCheck() {
         setStyle("-fx-border-color: #FFCC00;");
         soonCheckLabel.getStyleClass().add("warning-background");
         soonCheckLabel.setText("Årstillsyn: " + airplane.getYearlyCheckDate().toString() );
     }


    /**
     * Method that changes GUI when distance check is needed
     */
    void applyDistanceCheck() {
         distanceCheckButton.toFront();
         distanceCheckLabel.setText("Flygplanet har flugit 250 h sedan tillsyn. Då är det dags för tillsyn.");
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

        distanceCheckLabel.getStyleClass().add("warning-background");
        distanceCheckLabel.setText("Flygplanet har snart flugit 250 h sedan tillsyn. Dags för tillsyn om " + hoursUntilCheck + " h");
    }
}
