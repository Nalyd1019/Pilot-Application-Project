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
	public Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
		launch(args);


	}

	@Override
	public void start(Stage stage) throws Exception {

		FlightBuddy flightBuddy = FlightBuddy.getInstance();
		//flightBuddy.initializeClubs();

		JSONSerializer js = new JSONSerializer();
		flightBuddy.setFlyingclubs(js.getFlyingClubs());
		if (flightBuddy.getFlyingclubs().size()==0)
			flightBuddy.initializeClubs();



		Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
		stage.setTitle("FlightBuddy");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setWidth(1200);
		stage.setHeight(800);
		stage.setResizable(false);
		stage.show();
	}

	@Override
	public void stop() throws IOException {
		FlightBuddy flightBuddy = FlightBuddy.getInstance();
		JSONSerializer js = new JSONSerializer();
		js.serializeToJson(flightBuddy.getFlyingclubs());
	}





}
