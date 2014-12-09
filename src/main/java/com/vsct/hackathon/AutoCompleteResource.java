package com.vsct.hackathon;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/autocomplete")
public class AutoCompleteResource {
    
    @GET
    @Produces("application/json")
    public String autocomplete(String input){
           return "test";
    }
    
}
