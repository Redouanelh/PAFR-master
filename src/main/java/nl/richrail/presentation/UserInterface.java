package nl.richrail.presentation;

import java.io.IOException;

public interface UserInterface {

    public void initializeUI() throws IOException;
    public void initializeCLI() throws IOException;
    public void startApplication() throws IOException;
}
