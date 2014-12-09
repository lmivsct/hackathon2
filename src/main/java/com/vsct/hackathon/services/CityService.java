package com.vsct.hackathon.services;

import java.util.ArrayList;
import java.util.List;

import us.monoid.web.Resty;


/**
 * Created by erwann_plouhinec on 09/12/2014.
 */
public class CityService {

    private static final String URL_BASE = "http://integration1.geo.vsct.fr:8001/geoWeb/autocomplete";
    private static final String LANGUAGE = "fr";
    private static final String COUNTRY = "fr";
    private static final String CATEGORIES = "C-5";
    private static final boolean ONLY_BOOKABLE_TRAIN = true;

    private Resty resty = new Resty();

    public List<String> getOrigine(String origineSaisie) {

        List<String> result = new ArrayList<>();
        try {
            result = (List<String>) resty.json(URL_BASE + "?searchTerm=" + origineSaisie +
                    "&language=" + LANGUAGE + "&country=" + COUNTRY + "&categories=" + CATEGORIES + "&onlyBookableTrain=" + ONLY_BOOKABLE_TRAIN).get
                    ("cities.localities.name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<String> getDestination(String destinationSaisie) {
        return new ArrayList<String>();
    }
}
