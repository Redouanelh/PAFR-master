package nl.richrail.controller.factory;

import nl.richrail.controller.builder.*;
import nl.richrail.domain.Component;
import nl.richrail.domain.Driver;

public class TypeBasedComponentFactory implements ComponentFactory {
    private String type;

    public TypeBasedComponentFactory(String type) {
        this.type = type;
    }

    @Override
    public Component createComponent(String id, Integer seats, Integer weight) { // Wagon
        if (type.equals("passenger")) {
            WagonBuilder passengerbuilder = new PassengerWagonBuilder();
            passengerbuilder.setId(id);
            passengerbuilder.setSeats(seats);

            return passengerbuilder.build();
        } else {// If more wagon builders will be added, there need to be more if-statements with hardcoded the name of the wagon type
            WagonBuilder freightbuilder = new FreightWagonBuilder();
            freightbuilder.setId(id);
            freightbuilder.setWeight(weight);

            return freightbuilder.build();
        }
    }

    @Override
    public Component createLocomotive(String id, Driver driver) {
        LocomotiveBuilder locomotivebuilder = new StandardLocomotiveBuilder();
        locomotivebuilder.setId(id);
        locomotivebuilder.setDriver(driver);

        return locomotivebuilder.build();
    } // Add if-statement if there are multiple types of locomotives
}
