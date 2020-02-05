package nl.richrail.presentation.ui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import nl.richrail.controller.FinderController;
import nl.richrail.domain.singleton.LoggerInterface;
import nl.richrail.presentation.ObserverMechanism;
import nl.richrail.controller.command.*;
import nl.richrail.controller.factory.TypeBasedComponentFactory;
import nl.richrail.domain.Component;
import nl.richrail.domain.Driver;
import nl.richrail.domain.Locomotive;
import nl.richrail.domain.singleton.Company;
import nl.richrail.presentation.MainGui;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UIController implements Initializable {

    private LoggerInterface logger = Company.getInstance().getLogger();

    @FXML
    public Button buttonUnattachWagon;
    @FXML
    public Button buttonRestartCLI;
    @FXML
    public Button buttonSave;
    @FXML
    public Button buttonLoad;
    @FXML
    public TextField filename;
    @FXML
    private ListView list_locomotives;
    @FXML
    private ListView list_wagons;
    @FXML
    private ListView list_unattached_wagons;
    @FXML
    private ObservableList<String> locomotives = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> wagons = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> unattachedwagons = FXCollections.observableArrayList();
    @FXML
    public TextField wagonName;
    @FXML
    public TextField wagonSeats;
    @FXML
    public TextField wagonWeight;
    @FXML
    public TextField wagonName1;
    @FXML
    public TextField wagonSeats1;
    @FXML
    public TextField wagonWeight1;
    @FXML
    public TextField addLocomotiveName;
    @FXML
    public ComboBox wagonTypeComboBox;
    @FXML
    public TextField addWagonName;
    @FXML
    public TextField addWagonSeats;
    @FXML
    public TextField addWagonWeight;
    @FXML
    public TextField addToLocomotive;
    @FXML
    public TextField attacherLocomotiveId;
    @FXML
    public TextField attacherWagonId;

    public void update() {
        updateLocomotives();
        updateUnattachedWagons();
        try {
            updateWagons(addToLocomotive.getText());
        } catch (NullPointerException n) {
            System.out.println("No locomotive selected");
        }
    }

    public void updateLocomotives() {
        locomotives.clear();
        for (Locomotive l : Company.getInstance().getLocomotives().values()) {
            locomotives.add(l.getId());
        }
        list_locomotives.setItems(locomotives);
    }

    public void updateWagons(String newValue) {
        try {
            wagons.clear();
            for (Component c : FinderController.findComponentByLocomotive(newValue)) {
                wagons.add(c.getId());
            }
            list_wagons.setItems(wagons);
        } catch (NullPointerException n) {
//            System.out.println("No locomotive selected");
        }
    }

    public void updateUnattachedWagons() {
        unattachedwagons.clear();

        ArrayList<String> s = new ArrayList<>();
        for (Component c : Company.getInstance().getComponents().values()) {
            if (FinderController.findLocomotiveByWagon(c.getId()) == null) {
                s.add(c.getId());
            }
        }
        unattachedwagons.clear();
        for (String c : s) {
            unattachedwagons.add(c);
        }
        list_unattached_wagons.setItems(unattachedwagons);
    }

    public void initialize(URL url, ResourceBundle rb) {
        //'Hardcoded' Drivers omdat deze functionaliteit niet in GUI of CLI geïmplenteerd wordt
        new Driver("d1", "Redouan", 20);
        new Driver("d2", "Maurits", 20);
        new Driver("d3", "Eric", 27);

        // Observer
        ObserverMechanism.getInstance().setUi(this);

        //Load locomotive names into Locomotive selector GUI
        updateLocomotives();
        updateUnattachedWagons();


        //Listener which updates the Wagon list when a train is selected
        list_locomotives.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    updateWagons(newValue);
                }
            }
        });

        //Listener which updates input fiels when a wagon is selected
        list_wagons.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    Component c = FinderController.findComponent(newValue);
                    wagonName.setText(c.getId());
                    try {
                        wagonWeight.setText(Integer.toString(c.getWeight()));
                    } catch (NullPointerException npe) {
                        wagonWeight.setText("");
                    }
                    try {
                        wagonSeats.setText(Integer.toString(c.getSeats()));
                    } catch (NullPointerException npe) {
                        wagonSeats.setText("");
                    }
                }
            }

        });

        list_unattached_wagons.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    Component c = FinderController.findComponent(newValue);
                    wagonName1.setText(c.getId());
                    try {
                        wagonWeight1.setText(Integer.toString(c.getWeight()));
                    } catch (NullPointerException npe) {
                        wagonWeight1.setText("");
                    }
                    try {
                        wagonSeats1.setText(Integer.toString(c.getSeats()));
                    } catch (NullPointerException npe) {
                        wagonSeats1.setText("");
                    }
                }
            }

        });

    }


    // Driver arg must be object, front end must be able to selected existing driver or null
    public void buttonAddTrain(ActionEvent actionEvent) {
        if (addLocomotiveName.getText().equals("")) {
            logger.log("Locomotive id required");
        } else {
            new NewLocomotiveCommand(addLocomotiveName.getText(), null, new TypeBasedComponentFactory("locomotive")).exec();
            updateLocomotives();
        }
    }

    public void buttonAddWagon(ActionEvent actionEvent) {
        try {
            if (addWagonName.getText().equals("")) {
                logger.log("Wagon id required");
                return;
            }
            if (wagonTypeComboBox.getValue().equals("passenger")) {
                new NewPassengerWagonCommand(addWagonName.getText(), Integer.parseInt(addWagonSeats.getText()), new TypeBasedComponentFactory("passenger")).exec();
                new AddCommand(addWagonName.getText(), addToLocomotive.getText()).exec();
            } else if (wagonTypeComboBox.getValue().equals("freight")) {

                new NewFreightWagonCommand(addWagonName.getText(), Integer.parseInt(addWagonWeight.getText()), new TypeBasedComponentFactory("freight")).exec();
                new AddCommand(addWagonName.getText(), addToLocomotive.getText()).exec();
            }
        } catch (Exception e) { }


        try {
            updateWagons(addToLocomotive.getText());
            updateUnattachedWagons();
        } catch (Exception e) {
            System.out.println("Wagon not added to locomotive");
        }

    }

    public void buttonRemoveTrain(ActionEvent actionEvent) {
        new DeleteLocomotiveCommand((String) list_locomotives.getSelectionModel().getSelectedItem()).exec();
        updateLocomotives();
        updateUnattachedWagons();
    }

    public void buttonRemoveWagon(ActionEvent actionEvent) {
        new DeleteComponentCommand((String) list_wagons.getSelectionModel().getSelectedItem()).exec();
        updateWagons((String) list_locomotives.getSelectionModel().getSelectedItem());
        updateUnattachedWagons();
    }

    public void buttonRemoveUnattachedWagon(ActionEvent actionEvent) {
        new DeleteComponentCommand((String) list_unattached_wagons.getSelectionModel().getSelectedItem()).exec();
        updateUnattachedWagons();
    }

    public void buttonWagonAttacher(ActionEvent actionEvent) {
        try {
            new AddCommand(attacherWagonId.getText(), attacherLocomotiveId.getText()).exec();
            updateUnattachedWagons();
            updateWagons(attacherLocomotiveId.getText());
        } catch (NullPointerException n) {
            System.out.println("No wagon and/or locomotive selected");
        }

    }

    public void buttonUnattachWagon(ActionEvent actionEvent) {
        try {
            new RemoveCommand((String) list_wagons.getSelectionModel().getSelectedItem(), (FinderController.findLocomotiveByWagon((String) list_wagons.getSelectionModel().getSelectedItem()).getId())).exec();
            updateUnattachedWagons();
            updateWagons((String) list_locomotives.getSelectionModel().getSelectedItem());
        } catch ( NullPointerException n) {
            System.out.println("No wagon selected");
        }

    }

    public void restartCLI(ActionEvent actionEvent) throws IOException {
        new MainGui().restartCLI();
    }

    public void saveFile(ActionEvent actionEvent) {
        new SaveCommand(filename.getText()).exec();
    }

    public void loadFile(ActionEvent actionEvent) {
        new LoadCommand(filename.getText()).exec();
        this.update();
    }

}

