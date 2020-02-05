package nl.richrail.controller.builder;

import nl.richrail.domain.Component;
import nl.richrail.domain.PassengerWagon;

public class PassengerWagonBuilder implements WagonBuilder {
    private Integer seats;
    private String id;

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Override
    public void setWeight(Integer weight) {} // Not needed for passenger wagon

    @Override
    public Component build() {
        if (seats != null) {
            PassengerWagon pwagon =  new PassengerWagon(id, seats);

            return pwagon;
        } else {
            PassengerWagon pwagon =  new PassengerWagon(id, 20);

            return pwagon;
        }
    }

}
