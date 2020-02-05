package nl.richrail.controller;

import nl.richrail.domain.Component;
import nl.richrail.domain.Locomotive;
import nl.richrail.domain.singleton.Company;

import java.util.Collection;

public class FinderController {

    public static Locomotive findLocomotive(String id) {
        if (Company.getInstance().getLocomotives().containsKey(id)) {
             return Company.getInstance().getLocomotives().get(id);
        }
        return null;
    }

    public static Component findComponent(String id) {
        if (Company.getInstance().getComponents().containsKey(id)) {
            return Company.getInstance().getComponents().get(id);
        }
        return null;
    }

    public static Locomotive findLocomotiveByWagon(String id) {
        for (Locomotive l : Company.getInstance().getLocomotives().values()) {
            if (l.getComponents().containsKey(id)) {
                return l;
            }
        }
        return null;
    }

    public static Collection<Component> findComponentByLocomotive(String id) {
        for (Locomotive l : Company.getInstance().getLocomotives().values()) {
            if (l.hasId(id)) {
                return l.getComponents().values();
            }
        }
        return null;
    }


}
