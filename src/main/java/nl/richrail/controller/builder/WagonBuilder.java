package nl.richrail.controller.builder;

import nl.richrail.domain.Component;

public interface WagonBuilder {
    public void setWeight(Integer weight);
    public void setSeats(Integer seats);
    public void setId(String id);
    Component build();

}
