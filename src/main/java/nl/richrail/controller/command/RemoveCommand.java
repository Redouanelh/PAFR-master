package nl.richrail.controller.command;

import nl.richrail.controller.FinderController;
import nl.richrail.domain.Component;
import nl.richrail.domain.Locomotive;
import nl.richrail.domain.singleton.Company;

public class RemoveCommand implements CommandInterface {

    public String cid;
    public String lid;

    public RemoveCommand(String cid, String lid) {
        this.cid = cid;
        this.lid = lid;
    }

    public void exec() {
        Component c = FinderController.findComponent(cid);

        Locomotive l = FinderController.findLocomotive(lid);
        Locomotive l2 = FinderController.findLocomotiveByWagon(cid);

        if (c == null) { Company.getInstance().getLogger().log("Wagon with id " + cid + " does not exist" + "\n"); return; }
        if (l == null) { Company.getInstance().getLogger().log("Locomotive with id " + lid + " does not exist" + "\n"); return; }
        if (l2 != null && !l.equals(l2)) { Company.getInstance().getLogger().log("Wagon is attached to another locomotive with id " + l2.getId() + "\n"); return; }
        if (!l.getComponents().containsKey(cid)) { Company.getInstance().getLogger().log("Wagon is not attached to locomotive with id " + lid + "\n") ; return; }
        l.removeComponent(c.getId());
        Company.getInstance().getLogger().log("Removed " + cid + " from " + lid + "\n");
    }
}
