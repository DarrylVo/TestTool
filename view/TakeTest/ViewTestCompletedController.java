package view.TakeTest;

import javafx.fxml.FXML;
import model.Main;
import model.TestTaking.Viewing;

public class ViewTestCompletedController {

	@FXML
	Main main;
	@FXML
	private static Viewing viewTest;

	public ViewTestCompletedController() {
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public static void setViewing(Viewing viewing) {
		viewTest = viewing;
	}
}
