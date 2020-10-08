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
    @FXML public Label soonCheckLabel;
    @FXML public Button checkIsDoneButton;
    @FXML public Label distanceCheckLabel;
    @FXML public Button distanceCheckButton;


    private MyClubController myClubController;
    private Airplane airplane;

    // test
    public MyClubListItem(Airplane airplane, String registration, int flightTime, MyClubController myClubController) {
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
        this.soonCheckLabel.setText(" ");
    }

    @FXML
    protected void onClick(Event event){
        buttonIsClicked();
    }

    @FXML
    protected void onDistanceButtonClick(Event event) {
        distanceCheckButtonClicked();
    }

    private void buttonIsClicked(){
        airplane.yearlyCheckIsDone();
        // clubListItem.getStyleClass().clear();
        setStyle("-fx-border-color: green");
        checkIsDoneButton.toBack();
        soonCheckLabel.getStyleClass().remove("warning-background");
        soonCheckLabel.setText("");
    }

    private void distanceCheckButtonClicked() {
        airplane.distanceCheckIsDone();
        distanceCheckButton.toBack();
        distanceCheckLabel.getStyleClass().remove("warning-background");
        distanceCheckLabel.setText("");
    }

}
