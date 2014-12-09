/*
 * Copyright (c) 2012, vsc-technologies - www.voyages-sncf.com
 * All rights reserved.
 * 
 * Les presents codes sources sont proteges par le droit d'auteur et 
 * sont la propriete exclusive de VSC Technologies.
 * Toute representation, reproduction, utilisation, exploitation, modification, 
 * adaptation de ces codes sources sont strictement interdits en dehors 
 * des autorisations formulees expressement par VSC Technologies, 
 * sous peine de poursuites penales. 
 * 
 * Usage of this software, in source or binary form, partly or in full, and of
 * any application developed with this software, is restricted to the
 * customer.s employees in accordance with the terms of the agreement signed
 * with VSC-technologies.
 */
package com.vsct.hackathon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 */
public class AutoCompleteServiceTest {

    public static final Pattern p = Pattern.compile("(.+)&gt;(.+)");

    @Test
    public void getOrigin() {
        final Matcher matcher = p.matcher("toto &gt; tata");
        System.out.println(matcher.matches());
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));

    }

}
