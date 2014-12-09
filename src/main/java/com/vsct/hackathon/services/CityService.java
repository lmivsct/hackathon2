package com.vsct.hackathon.services;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import us.monoid.web.Resty;


@Component
public class CityService {

    private static final String URL_BASE = "http://integration1.geo.vsct.fr:8001/geoWeb/autocomplete";
    private static final String LANGUAGE = "fr";
    private static final String COUNTRY = "fr";
    private static final String CATEGORIES = "C-5"; // https://wiki.vsct.fr/pages/viewpage.action?pageId=91625049#GEO1.1-Serviceauto-complétion-Paramètres
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
        List<String> result = new ArrayList<>();
        try {
            result = (List<String>) resty.json(URL_BASE + "?searchTerm=" + URLEncoder.encode(search, "UTF-8") + "&language=" + LANGUAGE + "&country=" + COUNTRY + "&categories=" + CATEGORIES + "&onlyBookableTrain=" + ONLY_BOOKABLE_TRAIN).get("cities.localities.name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
