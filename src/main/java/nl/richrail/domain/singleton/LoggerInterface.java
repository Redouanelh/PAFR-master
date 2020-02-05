package nl.richrail.domain.singleton;

import nl.richrail.presentation.cli.CLIController;

import java.util.List;

public interface LoggerInterface {
    void log(String message);
    List<String> getLog();
    void setCli(CLIController cli);

}
