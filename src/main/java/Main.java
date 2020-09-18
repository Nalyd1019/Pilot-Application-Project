import data.JSONSerializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FlightBuddy;
import model.FlyingClub;

public final class Main extends Application {
	public Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
		launch(args);

		/* SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				final Project project = new Project();
				final ProjectView projectView = new ProjectView(project);

				ProjectController.create(project, projectView);
				projectView.setVisible(true);

			}
		}); */
	}

	@Override
	public void start(Stage stage) throws Exception {
		FlightBuddy flightBuddy = new FlightBuddy();
		//flightBuddy.initializeClubs();



		JSONSerializer js = new JSONSerializer();
		flightBuddy.setFlyingclubs(js.getFlyingClubs());
		js.serializeToJson(flightBuddy.getFlyingclubs());

		FlyingClub fc = flightBuddy.getFlyingclubs().get(0);





		Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
		stage.setTitle("FlightBuddy");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setWidth(1200);
		stage.setHeight(800);
		stage.setResizable(false);
		stage.show();
	}





}
