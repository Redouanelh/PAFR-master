package nl.richrail.controller.command;

import nl.richrail.controller.FinderController;
import nl.richrail.controller.factory.ComponentFactory;
import nl.richrail.domain.Component;
import nl.richrail.domain.singleton.Company;
import nl.richrail.domain.singleton.LoggerInterface;

public class NewFreightWagonCommand implements CommandInterface {

    private LoggerInterface logger = Company.getInstance().getLogger();
    private String id;
    private Integer weight;
    private ComponentFactory componentFactory;

    public NewFreightWagonCommand(String id, Integer weight, ComponentFactory componentFactory) {
        this.id = id;
        this.weight = weight;
        this.componentFactory = componentFactory;
    }

    public void exec() {
        Component wagon = FinderController.findComponent(id);
        if (wagon == null) {
            if (weight != null) {
                Component c = componentFactory.createComponent(id, null, weight);

                Company.getInstance().addComponent(c);
                logger.log("Created freight wagon with id: " + id + " and weight: " + weight + "\n");
            } else {
                Component c = componentFactory.createComponent(id, null, 5000);

                Company.getInstance().addComponent(c);
                logger.log("New freight wagon created: " + id + ". You did not give a max weight, so default will be: " + 5000 + "\n");
            }
        } else {
            logger.log("Freight wagon already exists with id: " + id + "\n");
        }

    }

}