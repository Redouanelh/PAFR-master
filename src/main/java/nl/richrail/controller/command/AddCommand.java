package nl.richrail.controller.command;

import nl.richrail.controller.FinderController;
import nl.richrail.domain.Component;
import nl.richrail.domain.Locomotive;
import nl.richrail.domain.singleton.Company;
import nl.richrail.domain.singleton.LoggerInterface;

public class AddCommand implements CommandInterface {

    private String cid;
    private String lid;
    private LoggerInterface logger = Company.getInstance().getLogger();

    public AddCommand(String cid, String lid) {
        this.cid = cid;
        this.lid = lid;
    }

    public void exec() {
        Component c = FinderController.findComponent(cid);
        Locomotive l = FinderController.findLocomotive(lid);
        Locomotive l2 = FinderController.findLocomotiveByWagon(cid);
        if (c == null) { logger.log("Wagon with id " + cid + " does not exist" + "\n"); return; }
        if (l == null) { logger.log("Locomotive with id " + lid + " does not exist" + "\n"); return; }
        if (l.getComponents().containsKey(cid)) { logger.log("Wagon is already attached to locomotive with id " + lid + "\n"); return; }
        if (l2 != null) { logger.log("Wagon is already attached to locomotive with id " + l2.getId() + "\n"); return; }
        l.addComponent(c);
        logger.log("Added " + cid + " to " + lid + "\n");
    }
}
