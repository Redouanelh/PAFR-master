package nl.richrail.controller.command;

import nl.richrail.controller.factory.ComponentFactory;
import nl.richrail.domain.singleton.Company;
import nl.richrail.controller.FinderController;
import nl.richrail.domain.Component;
import nl.richrail.domain.singleton.LoggerInterface;

public class NewPassengerWagonCommand implements CommandInterface {

    private LoggerInterface logger = Company.getInstance().getLogger();
    private String id;
    private Integer seats;
    private ComponentFactory componentFactory;

    public NewPassengerWagonCommand(String id, Integer seats, ComponentFactory componentFactory) {
        this.id = id;
        this.seats = seats;
        this.componentFactory = componentFactory;
    }

    public void exec() {
        Component wagon = FinderController.findComponent(id);
        if (wagon == null) {
            if (seats != null) {
                Component c = componentFactory.createComponent(id, seats, null);

                Company.getInstance().addComponent(c);
                logger.log("Created passenger wagon with id: " + id + " and seats: " + seats + "\n");
            } else {
                Component c = componentFactory.createComponent(id, 20, null);

                Company.getInstance().addComponent(c);
                logger.log("new passenger wagon created: " + id + ". You did not give an amount of seats, so default will be: " + 20 + "\n");
            }
        } else {
            logger.log("Passenger wagon already exists with id: " + id + "\n");
        }

    }

}