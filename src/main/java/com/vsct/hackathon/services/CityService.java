package com.vsct.hackathon.services;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

@Component
public class CityService {

    private static final String URL_BASE = "http://integration1.geo.vsct.fr:8001/geoWeb/autocomplete";
    private static final String LANGUAGE = "fr";
    private static final String COUNTRY = "fr";
    private static final String CATEGORIES = "R-5,C-5"; // https://wiki.vsct.fr/pages/viewpage.action?pageId=91625049#GEO1
    // .1-Serviceauto-complétion-Paramètres
    // C = cities, 5 = 5 items max
    private static final boolean ONLY_BOOKABLE_TRAIN = true;

    private Resty resty = new Resty();

    public List<String> getOrigine(String origineSaisie) {
        return appelREST(origineSaisie);
    }

    public List<String> getDestination(String destinationSaisie) {
        return appelREST(destinationSaisie);
    }

    private List<String> appelREST(String search) {
        List<Locality> cities = new ArrayList<>();
        try {
            JSONResource json = resty.json(URL_BASE + "?searchTerm=" + URLEncoder.encode(search, "UTF-8") +
                    "&language=" + LANGUAGE + "&country=" + COUNTRY + "&categories=" + CATEGORIES + "&onlyBookableTrain=" + ONLY_BOOKABLE_TRAIN);
            cities = parseLocalities((JSONArray) json.get("cities.localities"));
            List<Locality> railwayStations = parseLocalities((JSONArray) json.get("railwayStations.localities"));
            cities.addAll(railwayStations);
            Collections.sort(cities, new LocalityComparator());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<String> names = new LinkedHashSet<>();
        for (Locality city : cities) {
            names.add(city.getName());
        }

        return new ArrayList<>(names);
    }

    private List<Locality> parseLocalities(JSONArray localities) {
        List<Locality> cities = new ArrayList<>();
        try {
            for (int i = 0; i < localities.length(); i++) {
                JSONObject locality = localities.getJSONObject(i);
                int weight = locality.getInt("weight");
                String name = locality.getString("name");
                cities.add(new Locality(name, weight));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
