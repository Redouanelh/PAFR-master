package nl.richrail.domain.singleton;

import nl.richrail.controller.Logger;
import nl.richrail.domain.Component;
import nl.richrail.domain.Locomotive;

import java.util.HashMap;
import java.util.Map;

public class Company {
    private static Company ourInstance;
    private LoggerInterface logger;
    private Map<String, Locomotive> locomotives;
    private Map<String, Component> components;

    public static Company getInstance() {
        if (ourInstance == null) {
            ourInstance = new Company();
        }
        return ourInstance;
    }

    private Company() {
        this.locomotives = new HashMap<>();
        this.components = new HashMap<>();
        this.logger = new Logger();
    }

    public Map<String, Locomotive> getLocomotives() {
        return locomotives;
    }

    public void setLocomotives(Map<String, Locomotive> locomotives) {
        this.locomotives = locomotives;
    }

    public Map<String, Component> getComponents() {
        return components;
    }

    public void setComponents(Map<String, Component> components) {
        this.components = components;
    }

    public void addLocomotive(Locomotive l) { locomotives.put(l.getId(), l); }

    public void addComponent(Component c) { components.put(c.getId(), c); }

    public void removeLocomotive(String id) {
        locomotives.remove(id);
    }

    public void removeComponent(String id) {
        components.remove(id);
    }

    public LoggerInterface getLogger() {
        return logger;
    }

}
