package nl.richrail.controller.factory;

import nl.richrail.domain.Component;
import nl.richrail.domain.Driver;

public interface ComponentFactory {
    Component createComponent(String id, Integer seats, Integer weight); // wagon
    Component createLocomotive(String id, Driver driver); // locomotive
}
