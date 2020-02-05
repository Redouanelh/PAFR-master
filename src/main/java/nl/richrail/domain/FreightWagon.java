package nl.richrail.domain;

public class FreightWagon implements Component, Wagon {
    private String id;
    private int weight;

    public FreightWagon(String id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    public boolean hasId(String id) {
        return this.id.equals(id);
    }

    public String getId() {
        return this.id;
    }

    public Integer getSeats() {
        return null;
    } // not needed for this wagon type

    public Integer getWeight() {
        return weight;
    }

    public String toString() {
        return "Freight wagon: (id: " + this.id + ", max weight: " + weight + ")";
    }

}
