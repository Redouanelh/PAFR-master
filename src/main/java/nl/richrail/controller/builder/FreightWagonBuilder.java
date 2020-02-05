package nl.richrail.controller.builder;

import nl.richrail.domain.Component;
import nl.richrail.domain.FreightWagon;

public class FreightWagonBuilder implements WagonBuilder{
    private Integer weight;
    private String id;

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public void setSeats(Integer seats) {} // Not needed for freight wagon

    @Override
    public Component build() {
        if (weight != null) {
            FreightWagon fwagon =  new FreightWagon(id, weight);

            return fwagon;
        } else {
            FreightWagon fwagon =  new FreightWagon(id, 5000);

            return fwagon;
        }
    }

}
