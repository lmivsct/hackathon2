package com.vsct.hackathon.services.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    
    public static Date getTomorrow(){
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
}
