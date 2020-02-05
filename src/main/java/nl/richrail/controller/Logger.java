package nl.richrail.controller;

import nl.richrail.domain.singleton.LoggerInterface;
import nl.richrail.presentation.cli.CLIController;

import java.util.ArrayList;
import java.util.List;

public class Logger implements LoggerInterface {
    private List<String> log = new ArrayList<>();
    private CLIController cli;

    public List<String> getLog() {
        return log;
    }

    public void setCli(CLIController cli) {
        this.cli = cli;
    }

    public void log(String string) {
        log.add(string);
        if (cli != null) cli.runRefresh();

    }

}
