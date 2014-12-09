package com.vsct.hackathon;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vsct.hackathon.model.SearchParameters;

public class AutoCompleteService {

    public static final Pattern p = Pattern.compile("(.+)&gt;(.+)");

    public SearchParameters getSearchParameters(String input) {
        final SearchParameters searchParameters = new SearchParameters();
        final Matcher matcher = p.matcher(input);
        if (matcher.matches()) {
            searchParameters.setOrigin(matcher.group(0));
            searchParameters.setDestination(matcher.group(1));
        }
        return searchParameters;
    }
    
    public List<String> getSuggests(SearchParameters searchParameters){
        List<String> proposals = new ArrayList<String>();
        return null;
    }

}
