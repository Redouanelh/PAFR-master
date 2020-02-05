package nl.richrail.controller.command;

import nl.richrail.domain.singleton.Company;
import nl.richrail.domain.singleton.LoggerInterface;

public class HelpCommand implements CommandInterface {

    public HelpCommand() {};

    public void exec() {
        LoggerInterface l = Company.getInstance().getLogger();
        l.log("List of commands:");
        l.log("- getall");
        l.log("- get (id)");
        l.log("- new locomotive (id)");
        l.log("- new passenger (id) [seats]");
        l.log("- new freight (id) [weight]");
        l.log("- add (wagonid) to (locomotiveid)");
        l.log("- remove (wagonid) from (locomotiveid)");
        l.log("- delete locomotive (id)");
        l.log("- delete wagon (id)");
        l.log("- save (filename.json)");
        l.log("- load (filename.json)");
        l.log("() = user input");
        l.log("[] = optional user input" + "\n");
    };

}
