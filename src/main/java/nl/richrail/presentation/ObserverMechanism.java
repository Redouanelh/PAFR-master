package nl.richrail.presentation;

import nl.richrail.presentation.ui.UIController;

import java.util.Observable;
import java.util.Observer;

public class ObserverMechanism implements Observer {

    private static ObserverMechanism ourInstance;
    private UIController ui;

    public static ObserverMechanism getInstance() {
        if (ourInstance == null) {
            ourInstance = new ObserverMechanism();
        }
        return ourInstance;
    }

    public void setUi(UIController ui) {
        this.ui = ui;
    }

    @Override
    public void update(Observable observable, Object o) {
        ui.update();
    }
}
