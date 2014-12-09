package com.vsct.hackathon.services;

import java.math.BigDecimal;

/**
 * Created by erwann_plouhinec on 09/12/2014.
 */
public class Locality {
    private String name;
    private BigDecimal score;

    Locality(final String name, final BigDecimal score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getScore() {
        return score;
    }
}
