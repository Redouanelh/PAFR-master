package nl.richrail.controller.command;

import nl.richrail.domain.singleton.Company;
import nl.richrail.domain.singleton.LoggerInterface;
import nl.richrail.persistance.CompanyDao;
import nl.richrail.persistance.CompanyFileDao;
import nl.richrail.persistance.CompanyJsonSerializer;
import nl.richrail.persistance.CompanySerializer;

public class LoadCommand implements CommandInterface {

    private String textfile;
    private LoggerInterface logger = Company.getInstance().getLogger();

    public LoadCommand(String textfile) {
        this.textfile = textfile;

    }

    @Override
    public void exec() {
        CompanySerializer jsonSerializer = new CompanyJsonSerializer();
        CompanyDao companyDao = new CompanyFileDao(this.textfile, jsonSerializer);

        if (Company.getInstance() != null) {
            companyDao.load();
        } else {
            logger.log(textfile + " failed to load");
        }

    }

}
