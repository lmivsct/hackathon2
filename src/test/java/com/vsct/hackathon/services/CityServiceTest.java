package com.vsct.hackathon.services;

import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by erwann_plouhinec on 09/12/2014.
 */
public class CityServiceTest {

    private CityService cityService = new CityService();

    @Test
    public void getOrigine() {
        List<String> result = cityService.getOrigine("Nantes");
        assertEquals("Nantes", result.get(0));
        assertEquals("Nantes-En-Ratier", result.get(1));
    }
}
