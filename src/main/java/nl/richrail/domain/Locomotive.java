package nl.richrail.domain;

import java.util.HashMap;

public class Locomotive implements Component, Cloneable {

    private String id;
    private HashMap<String, Component> components = new HashMap<>();
    private Driver driver;

    public Locomotive(String id) {
        this.id = id;
    }

    public Locomotive(String id, Driver driver) {
        this.id = id;
        this.driver = driver;
    }

    public boolean hasId(String id) {
        return this.id.equals(id);
    }

    public String getId() {
        return this.id;
    }

    public Integer getSeats() {
        return null;
    }

    public Integer getWeight() {
        return null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, Component> getComponents() {
        return components;
    }

    public void addComponent(Component c) {
        components.put(c.getId(), c);
    }

    public void removeComponent(String id) {
        components.remove(id);
    }

    public Driver getDriver() {
        return driver;
    }
    
    public Object clone(String id) throws CloneNotSupportedException {
        Locomotive clonedLocomotive = (Locomotive) super.clone();
        clonedLocomotive.setId(id);
        return clonedLocomotive;
    }

    public String toString() {
        return "Locomotive: (id: " + this.id + (driver == null ? "" : ", " + driver.getName()) + ")";
    }
}
