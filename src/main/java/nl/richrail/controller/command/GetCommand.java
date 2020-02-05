package nl.richrail.controller.command;

import nl.richrail.controller.FinderController;
import nl.richrail.domain.Component;
import nl.richrail.domain.singleton.Company;

public class GetCommand implements CommandInterface {

    private String id;

    public GetCommand(String id) {
        this.id = id;
    }

    public void exec() {
        Component c = FinderController.findComponent(id);
        if (c == null) { Company.getInstance().getLogger().log("Wagon is not found with id " + id + "\n"); return; }
        if (c.getSeats() != null) {
            Company.getInstance().getLogger().log("Passenger wagon " + id + " with " + c.getSeats() + " seats." + "\n");
        } else {
            Company.getInstance().getLogger().log("Freight wagon " + id + " with " + c.getWeight() + " weight." + "\n");
        }
    }
}
