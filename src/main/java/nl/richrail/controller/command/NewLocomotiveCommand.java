package nl.richrail.controller.command;

import nl.richrail.controller.factory.ComponentFactory;
import nl.richrail.domain.Component;
import nl.richrail.domain.Driver;
import nl.richrail.domain.Locomotive;
import nl.richrail.domain.singleton.Company;
import nl.richrail.controller.FinderController;
import nl.richrail.domain.singleton.LoggerInterface;

public class NewLocomotiveCommand implements CommandInterface {

    private LoggerInterface logger = Company.getInstance().getLogger();
    private String id;
    private Driver driver;
    private ComponentFactory componentFactory;

    public NewLocomotiveCommand(String id, Driver driver, ComponentFactory componentFactory) {
        this.id = id;
        this.driver = driver;
        this.componentFactory = componentFactory;
    }

    public void exec() {
        Locomotive locomotive = FinderController.findLocomotive(id);
        if (locomotive == null) {
            if (driver != null) {
                Component l = componentFactory.createLocomotive(id, driver);

                Company.getInstance().addLocomotive((Locomotive) l);
                logger.log("Created train/locomotive with id: " + id + "\n");
            } else {
                Component l = componentFactory.createLocomotive(id, null);

                Company.getInstance().addLocomotive((Locomotive) l);
                logger.log("Created train/locomotive with id: " + id + ". No driver was given, this can be set later." + "\n");
            }
        } else {
            logger.log("Train/locomotive already exists with id: " + id + "\n") ;
        }
    }

}
