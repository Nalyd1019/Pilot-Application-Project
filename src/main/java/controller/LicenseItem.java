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
    }

}
