package com.vsct.hackathon.services.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AutoCompleteUtils {

    public static List<String> convertListForSuggestion(List<String> items) {
        final ArrayList<String> newList = new ArrayList<>();
        for (String item : items) {
            newList.add("\""+item+"\"");
        }
        return newList;
    }
}
