package nl.richrail.persistance;

import com.google.gson.*;
import nl.richrail.controller.command.AddCommand;
import nl.richrail.controller.command.NewFreightWagonCommand;
import nl.richrail.controller.command.NewLocomotiveCommand;
import nl.richrail.controller.command.NewPassengerWagonCommand;
import nl.richrail.controller.factory.TypeBasedComponentFactory;
import nl.richrail.domain.Component;
import nl.richrail.domain.Locomotive;
import nl.richrail.domain.singleton.Company;
import nl.richrail.domain.singleton.LoggerInterface;

import java.util.HashMap;
import java.util.Map;

public class CompanyJsonSerializer implements CompanySerializer {

    private LoggerInterface logger = Company.getInstance().getLogger();

    @Override
    public String serialize(Company company) {

        String locomotivesJson;
        String componentsJson;

        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyLocomotives = prettyGson.toJson(Company.getInstance().getLocomotives());
        String prettyComponents = prettyGson.toJson(Company.getInstance().getComponents());

        locomotivesJson = prettyLocomotives;
        componentsJson = prettyComponents;

        return locomotivesJson + "-" + componentsJson;
    }

    @Override
    public Company deserialize(String data) {

        String locomotivesJson = data.split("-")[0];

        String componentsJson = data.split("-")[1];

        Map<String, Locomotive> locomotivemap = new HashMap<>();
        Map<String, Component> componentmap = new HashMap<>();

        JsonObject jsonObjectComponents = new JsonParser().parse(componentsJson).getAsJsonObject();
        for(Map.Entry<String, JsonElement> entry : jsonObjectComponents.entrySet()) {
            JsonObject jsonObjectComponent = new JsonParser().parse(entry.getValue().toString()).getAsJsonObject();
            String id = entry.getKey();
            if (jsonObjectComponent.has("seats")) {
                String seats = jsonObjectComponent.get("seats").toString();
                new NewPassengerWagonCommand(id, Integer.parseInt(seats), new TypeBasedComponentFactory("passenger")).exec();
            } else if (jsonObjectComponent.has("weight")) {
                String weight = jsonObjectComponent.get("weight").toString();
                new NewFreightWagonCommand(id, Integer.parseInt(weight), new TypeBasedComponentFactory("freight")).exec();
            }
        }

        JsonObject jsonObjectLocomotives = new JsonParser().parse(locomotivesJson).getAsJsonObject();
        for(Map.Entry<String, JsonElement> entry : jsonObjectLocomotives.entrySet()) {
            JsonObject jsonObjectLocomotive = new JsonParser().parse(entry.getValue().toString()).getAsJsonObject();
            String id = entry.getKey();
            new NewLocomotiveCommand(id, null, new TypeBasedComponentFactory("locomotive")).exec();
            JsonObject jsonObjectAttached = new JsonParser().parse(jsonObjectLocomotive.get("components").toString()).getAsJsonObject();
            for(Map.Entry<String, JsonElement> entryAttached : jsonObjectAttached.entrySet()) {
                new AddCommand(entryAttached.getKey(), entry.getKey()).exec();
            }
        }

        return null;
    }
}
