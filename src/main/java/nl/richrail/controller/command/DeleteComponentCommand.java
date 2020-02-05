package nl.richrail.controller.command;

import nl.richrail.controller.FinderController;
import nl.richrail.domain.Component;
import nl.richrail.domain.Locomotive;
import nl.richrail.domain.singleton.Company;
import nl.richrail.domain.singleton.LoggerInterface;

public class DeleteComponentCommand implements CommandInterface {

    private String componentid;
    private LoggerInterface logger = Company.getInstance().getLogger();

    public DeleteComponentCommand(String componentid) {
        this.componentid = componentid;
    }

    @Override
    public void exec() {
        Component component = FinderController.findComponent(componentid);
        if (component != null) {
            Company.getInstance().removeComponent(componentid);
            Locomotive l = FinderController.findLocomotiveByWagon(componentid);
            if (l != null) {
                l.removeComponent(componentid);
            }

            logger.log("Component with id: " + componentid + " successfully deleted." + "\n");
        } else {
            logger.log("Component with id: " + componentid + " failed to delete; Component does not exist." + "\n");
        }

    }
}
