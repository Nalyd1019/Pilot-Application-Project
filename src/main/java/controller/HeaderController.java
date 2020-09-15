package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HeaderController implements Initializable {

    @FXML private Hyperlink startLink;
    @FXML private Hyperlink clubLink;
    @FXML private Hyperlink accountLink;
    @FXML private Hyperlink logbookLink;
    @FXML private Hyperlink bookingLink;
    @FXML private Hyperlink logoutLink;
    @FXML private ImageView logoImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO : set links to change fxml root when clicked

    }

    //TODO: add method to make link bold+underlined when clicked
}
