package nl.richrail;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

import nl.richrail.presentation.MainGui;

public class Main extends Application {

    public void start(Stage stage) throws IOException {

        /** Facade pattern */
        new MainGui().startApplication();

    }

}