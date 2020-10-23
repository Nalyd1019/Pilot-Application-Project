package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * @Author Samuel Dahlberg
 * Controller class for the header fxml file.
 */
public class HeaderController implements Initializable {

    @FXML private Hyperlink startLink;
    @FXML private Hyperlink clubLink;
    @FXML private Hyperlink accountLink;
    @FXML private Hyperlink logbookLink;
    @FXML private Hyperlink bookingLink;
    @FXML private Hyperlink logoutLink;
    @FXML private ImageView logoImage;

    /**
     * The initialize method that runs when loading a page containing the header.
     * The method sets the logoimage and makes all links lead to the correct page.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLinkBold(ViewNavigator.getCurrentPage());

        logoImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream("GliderSilhouette.png")));
        startLink.setOnMouseClicked(mouseEvent -> ViewNavigator.LoadView(ViewNavigator.START));
        clubLink.setOnMouseClicked(mouseEvent -> ViewNavigator.LoadView(ViewNavigator.CLUB));
        accountLink.setOnMouseClicked(mouseEvent -> ViewNavigator.LoadView(ViewNavigator.ACCOUNT));
        logbookLink.setOnMouseClicked(mouseEvent -> ViewNavigator.LoadView(ViewNavigator.LOG));
        bookingLink.setOnMouseClicked(mouseEvent -> ViewNavigator.LoadView(ViewNavigator.BOOKING));
        logoutLink.setOnMouseClicked(mouseEvent -> logout());

        startLink.setBorder(Border.EMPTY);
        clubLink.setBorder(Border.EMPTY);
        accountLink.setBorder(Border.EMPTY);
        logbookLink.setBorder(Border.EMPTY);
        bookingLink.setBorder(Border.EMPTY);
        logoutLink.setBorder(Border.EMPTY);


    }

    private void logout(){
        ViewNavigator.LoadView(ViewNavigator.LOGIN);
    }

    /**
     * Method styling the current page's link to be bold
     */
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
