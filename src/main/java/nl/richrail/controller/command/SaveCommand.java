package nl.richrail.controller.command;

import nl.richrail.domain.singleton.Company;
import nl.richrail.domain.singleton.LoggerInterface;
import nl.richrail.persistance.CompanyDao;
import nl.richrail.persistance.CompanyFileDao;
import nl.richrail.persistance.CompanyJsonSerializer;
import nl.richrail.persistance.CompanySerializer;

public class SaveCommand implements CommandInterface {

    private String textfile;
    private LoggerInterface logger = Company.getInstance().getLogger();

    public SaveCommand(String textfile) {
        this.textfile = textfile;
    }

    @Override
    public void exec() {
        CompanySerializer jsonSerializer = new CompanyJsonSerializer();

        if (!this.textfile.contains(".json")) {
            this.textfile += ".json";
        }

        CompanyDao companyDao = new CompanyFileDao(this.textfile, jsonSerializer);

        if (Company.getInstance() != null) {
            companyDao.save(Company.getInstance());
        } else {
            logger.log("Company failed to save");
        }
    }
}
