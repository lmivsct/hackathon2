package com.vsct.hackathon.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vsct.hackathon.model.SearchParameters;
import com.vsct.hackathon.services.utils.AutoCompleteUtils;

@Component
public class AutoCompleteService {

    private static final Pattern p = Pattern.compile("(.+)>(.+)");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private CityService cityService;


    private SearchParameters getSearchParameters(String input) {
        final SearchParameters searchParameters = new SearchParameters();
        final Matcher matcher = p.matcher(input);
        if (matcher.matches()) {
            searchParameters.setOrigin(matcher.group(1).trim());
            searchParameters.setDestination(matcher.group(2).trim());
        } else {
            searchParameters.setOrigin(input);
        }
        return searchParameters;
    }

    public List<String> getSuggests(String input) {
        final SearchParameters searchParameters = getSearchParameters(input);
        List<String> proposals = new ArrayList<String>();
        if (searchParameters.getOrigin() != null && searchParameters.getOrigin().length() > 2) {
            final List<String> origins = cityService.getOrigine(searchParameters.getOrigin());
            if (origins != null) {
                if (searchParameters.getDestination() != null && searchParameters.getDestination().length() > 2) {
                    final List<String> destinations = cityService.getDestination(searchParameters.getDestination());
                    if (destinations != null) {
                        for (String destination : destinations) {
                            final StringBuilder suggestEntry = new StringBuilder();
                            suggestEntry.append("\"" + searchParameters.getOrigin() + " > " + destination + "\"");
                            proposals.add(suggestEntry.toString());
                        }
                    } else {
                        if (!searchParameters.getOrigin().equalsIgnoreCase("paris")) {
                            proposals.add("\"" + searchParameters.getOrigin() + " > Paris le " + dateFormat.format(new Date()) + "\"");
                        }
                        if (!searchParameters.getOrigin().equalsIgnoreCase("lyon")) {
                            proposals.add("\"" + searchParameters.getOrigin() + " > Lyon le " + dateFormat.format(new Date()) + "\"");
                        }
                        if (!searchParameters.getOrigin().equalsIgnoreCase("nantes")) {
                            proposals.add("\"" + searchParameters.getOrigin() + " > Nantes le " + dateFormat.format(new Date()) + "\"");
                        }
                    }
                } else {
                    final ArrayList<String> originDests = new ArrayList<>();
                    for (String origin : origins) {
                        if (!origin.equalsIgnoreCase("paris")) {
                            originDests.add(origin + " > Paris");
                        } else {
                            originDests.add(origin + " > Lyon");
                        }
                    }
                    proposals.addAll(AutoCompleteUtils.convertListForSuggestion(originDests));
                }

            } else {
                proposals.add("\"Paris > Lyon le " + dateFormat.format(new Date()) + "\"");
                proposals.add("\"Paris > Lille le " + dateFormat.format(new Date()) + "\"");
                proposals.add("\"Paris <> Nantes le " + dateFormat.format(new Date()) + "\"");
                proposals.add("\"Paris <> Rennes le " + dateFormat.format(new Date()) + "\"");
            }
        }
        return proposals;
    }

}
