package nl.richrail.presentation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.richrail.presentation.parser.RunCommand;

import java.io.File;
import java.io.IOException;

public class MainGui implements UserInterface {

    @Override
    public void initializeUI() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/nl/richrail/presentation/ui/ui.fxml").toURI().toURL());
        Parent root = loader.load();
        stage.setTitle("©RichRail User interface");
        Scene scene = new Scene(root, 1000, 800);
        stage.setMaximized(true); // Set stage to full screen on startup.
        stage.setScene(scene);

        stage.show();

    }

    @Override
    public void initializeCLI() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/nl/richrail/presentation/cli/cli.fxml").toURI().toURL());
        Parent root = loader.load();
        stage.setTitle("©Richrail Command-line interface");
        Scene scene = new Scene(root, 850, 430);
        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void startApplication() throws IOException {
        this.initializeCLI();
        this.initializeUI();
    }

    public void restartCLI() throws IOException {

        this.initializeCLI();
    }
}
