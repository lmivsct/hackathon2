package com.vsct.hackathon.services;

import java.util.Comparator;

/**
 * Created by erwann_plouhinec on 09/12/2014.
 */
public class LocalityComparator implements Comparator<Locality> {
    @Override
    public int compare(Locality o1, Locality o2) {
        return o2.getScore().compareTo(o1.getScore());
    }
}
