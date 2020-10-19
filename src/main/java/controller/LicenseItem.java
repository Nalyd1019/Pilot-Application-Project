package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Airplane;
import model.License;

import java.io.IOException;

public class LicenseItem extends AnchorPane {

    @FXML
    private Label licenseName;
    @FXML
    private Label dateLabel;
    @FXML
    private Label expireSoonLabel;


    public LicenseItem(License license) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("licenseItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        licenseName.setText(license.getLicenseName());
        dateLabel.setText("Utgår " + license.getExpirationDate());
    }

    public void expiryDateCheck(License license){
        if (license.isExpired()){
            //red text + border  ..
            this.getStyleClass().add("check-now-border");
            expireSoonLabel.setText("Certifikat har utgått!");
            expireSoonLabel.getStyleClass().add("warning-background");
            dateLabel.setText("Utgick " + license.getExpirationDate());
        }
        else if (license.isSoonExpired()) {
            //yellow text + border
            this.getStyleClass().add("check-soon-border");
            expireSoonLabel.setText("Certifikat utgår snart!");
            expireSoonLabel.getStyleClass().add("warning-background");
        }
    }

}
