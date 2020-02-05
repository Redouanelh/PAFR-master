package nl.richrail.controller.command;

import nl.richrail.controller.FinderController;
import nl.richrail.domain.Locomotive;
import nl.richrail.domain.singleton.Company;
import nl.richrail.domain.singleton.LoggerInterface;

public class DeleteLocomotiveCommand implements CommandInterface {

    private String id;
    private LoggerInterface logger = Company.getInstance().getLogger();

    public DeleteLocomotiveCommand(String id) {
        this.id = id;
    }

    @Override
    public void exec() {
        Locomotive locomotive = FinderController.findLocomotive(id);
        if (locomotive != null) {
            Company.getInstance().removeLocomotive(id);

            logger.log("Train/locomotive with id: " + id + " successfully deleted" + "\n");
        } else {
            logger.log("Train/locomotive with id: " + id + " failed to delete; train/locomotive does not exist" + "\n");
        }
    }
}
