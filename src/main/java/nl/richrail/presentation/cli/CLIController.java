package nl.richrail.presentation.cli;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import nl.richrail.domain.singleton.Company;
import nl.richrail.domain.singleton.LoggerInterface;
import nl.richrail.presentation.parser.RunCommand;

import java.net.URL;
import java.util.ResourceBundle;

public class CLIController implements Initializable {

    private LoggerInterface logger = Company.getInstance().getLogger();

    @FXML
    public TextArea textArea;

    @FXML
    public TextField inputField;

    @FXML
    public void onEnter(ActionEvent e)
    {
        if (!inputField.getText().equals("")) {
            logger.log("> " + inputField.getText());
            new RunCommand(inputField.getText()).exec();

            inputField.setText("");

        }
    }

    @FXML
    private void refresh() {
        if (textArea == null) { return; }
        textArea.setText("");
        for (String s : logger.getLog()) {
            textArea.appendText(s + '\n');
        }
    }

    public void runRefresh() {
        this.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.setCli(this);
    }

}
