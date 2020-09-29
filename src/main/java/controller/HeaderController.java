package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
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
        logoImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream("GliderSilhouette.png")));
        startLink.setOnMouseClicked(mouseEvent -> ViewNavigator.LoadView(ViewNavigator.START));
        clubLink.setOnMouseClicked(mouseEvent -> ViewNavigator.LoadView(ViewNavigator.CLUB));
        accountLink.setOnMouseClicked(mouseEvent -> ViewNavigator.LoadView(ViewNavigator.ACCOUNT));
        logbookLink.setOnMouseClicked(mouseEvent -> ViewNavigator.LoadView(ViewNavigator.LOG));
        bookingLink.setOnMouseClicked(mouseEvent -> ViewNavigator.LoadView(ViewNavigator.BOOKING));
        logoutLink.setOnMouseClicked(mouseEvent -> logout());

        //setLinkBold(ViewNavigator.getCurrentPage());

    }

    private void logout(){
        //currentuser = null
        //loadview(loginpage)?
    }

    //TODO: add method to make link bold+underlined when clicked
    private void setLinkBold(String currentPage) {
        switch (currentPage) {
            case ViewNavigator.START:
                startLink.setStyle("-fx-font-weight: bold; -fx-underline: true;");
                break;
            case ViewNavigator.CLUB:
                clubLink.setStyle("-fx-font-weight: bold; -fx-underline: true;");
                break;
            case ViewNavigator.ACCOUNT:
                accountLink.setStyle("-fx-font-weight: bold; -fx-underline: true;");
                break;
            case ViewNavigator.LOG:
                logbookLink.setStyle("-fx-font-weight: bold; -fx-underline: true;");
                break;
            case ViewNavigator.BOOKING:
                bookingLink.setStyle("-fx-font-weight: bold; -fx-underline: true;");
                break;
        }
    }

}
