package nl.richrail.controller.command;

import nl.richrail.controller.FinderController;
import nl.richrail.domain.Component;
import nl.richrail.domain.Locomotive;
import nl.richrail.domain.singleton.Company;

import java.util.ArrayList;

public class GetAllCommand implements CommandInterface {

    @Override
    public void exec() {
        if (Company.getInstance().getLocomotives().isEmpty()) {
            Company.getInstance().getLogger().log("No locomotives found");
        } else {
            Company.getInstance().getLogger().log("Locomotives:");
            for (Locomotive l : Company.getInstance().getLocomotives().values()) {
                StringBuilder s = new StringBuilder("(" + l.getId() + ")");
                if (!l.getComponents().isEmpty()) {
                    for (Component c : l.getComponents().values()) {
                        s.append("-[").append(c.getId()).append("]");
                    }
                }
                Company.getInstance().getLogger().log("  " + s.toString());
            }

        }

        if (Company.getInstance().getComponents().isEmpty()) {
            Company.getInstance().getLogger().log("No wagons found\n");
        } else {
            Company.getInstance().getLogger().log("Unattached wagons:");
            ArrayList<String> s = new ArrayList<>();
            for (Component c : Company.getInstance().getComponents().values()) {
                if (FinderController.findLocomotiveByWagon(c.getId()) == null) {
                    s.add("["+c.getId()+"]");
                }
            }
            Company.getInstance().getLogger().log("  " + String.join(", ", s) + "\n");
        }
    }
}
