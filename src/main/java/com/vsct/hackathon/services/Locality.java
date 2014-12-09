package com.vsct.hackathon.services;

/**
 * Created by erwann_plouhinec on 09/12/2014.
 */
public class Locality {
    private String name;
    private int weight;

    Locality(final String name, final int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(final int weight) {
        this.weight = weight;
    }
}
