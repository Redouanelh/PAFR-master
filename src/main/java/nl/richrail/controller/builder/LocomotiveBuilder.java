package nl.richrail.controller.builder;

import nl.richrail.domain.Component;
import nl.richrail.domain.Driver;

public interface LocomotiveBuilder {
    public void setId(String id);
    public void setDriver(Driver driver);
    Component build();
}
