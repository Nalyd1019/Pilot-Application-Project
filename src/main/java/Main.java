import controller.ViewNavigator;
import data.JSONSerializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FlightBuddy;
import model.FlyingClub;

import java.io.IOException;

public final class Main extends Application {

	Stage stage;

	public Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
		launch(args);


	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;

		initializeData();
		initializeStage();

		stage.show();
	}

	@Override
	public void stop() throws IOException {
		FlightBuddy flightBuddy = FlightBuddy.getInstance();
		JSONSerializer js = new JSONSerializer();
		js.serializeToJson(flightBuddy.getFlyingclubs());
	}

	private void initializeStage() throws Exception{
		ViewNavigator.setMainStage(stage);
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("loginPage.fxml"));
		stage.setTitle("FlightBuddy");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setWidth(1200);
		stage.setHeight(800);
		stage.setResizable(false);
	}
	private void initializeData() throws Exception{
		FlightBuddy flightBuddy = FlightBuddy.getInstance();
		JSONSerializer js = new JSONSerializer();
		flightBuddy.setFlyingclubs(js.getFlyingClubs());
	}





}
