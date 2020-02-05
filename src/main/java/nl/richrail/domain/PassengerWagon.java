package nl.richrail.domain;

public class PassengerWagon implements Component, Wagon {

    private String id;
    private int seats;

    public PassengerWagon(String id) {
        this(id, 20);
    }

    public PassengerWagon(String id, int seats) {
        this.id = id;
        this.seats = seats;
    }

    public boolean hasId(String id) {
        return this.id.equals(id);
    }

    public String getId() {
        return this.id;
    }

    public Integer getSeats() {
        return seats;
    }

    public Integer getWeight() { return null; } // not needed for this wagon type

    public String toString() {
        return "Passenger wagon: (id: " + this.id + ", seats: " + this.seats + ")";
    }

}
