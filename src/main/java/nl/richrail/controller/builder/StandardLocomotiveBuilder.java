package nl.richrail.controller.builder;

import nl.richrail.domain.Component;
import nl.richrail.domain.Driver;
import nl.richrail.domain.Locomotive;

public class StandardLocomotiveBuilder implements LocomotiveBuilder {
    private String id;
    private Driver driver;


    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public Component build() {
        if (driver != null) {
            Locomotive locomotive = new Locomotive(id, driver);

            return locomotive;
        } else {
            Locomotive locomotive = new Locomotive(id);

            return locomotive;
        }
    }
}
