package com.vsct.hackathon.services;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
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
    private static final String CATEGORIES = "R-5,C-5"; // C = cities, R = railway, 5 = 5 items max
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

            cities = parseLocalities(json.get("cities.localities"));
            List<Locality> railwayStations = parseLocalities(json.get("railwayStations.localities"));
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

    private List<Locality> parseLocalities(Object localities) {
        JSONArray arrayLocalities = new JSONArray();
        if (localities != null && !"null".equalsIgnoreCase(localities.toString())) {
            arrayLocalities = (JSONArray) localities;
        }
        List<Locality> cities = new ArrayList<>();
        try {
            for (int i = 0; i < arrayLocalities.length(); i++) {
                JSONObject locality = arrayLocalities.getJSONObject(i);
                String score = locality.getString("score");
                Number number = NumberFormat.getNumberInstance(Locale.ENGLISH).parse(score);
                BigDecimal bgScore = new BigDecimal(number.toString());
                String name = locality.getString("name");
                cities.add(new Locality(name, bgScore));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
