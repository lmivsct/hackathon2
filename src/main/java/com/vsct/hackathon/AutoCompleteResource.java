package com.vsct.hackathon;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.vsct.hackathon.services.AutoCompleteService;

@Path("/autocomplete")
public class AutoCompleteResource {

    @Autowired
    private AutoCompleteService autoCompleteService;

    @GET
    @Produces("application/json")
    public Response autocomplete(@QueryParam("query") String query) throws JSONException {
        final StringBuilder responseBuilder = new StringBuilder("[\"" + query + "\"");
        responseBuilder.append(",");
        responseBuilder.append(autoCompleteService.getSuggests(query));
        responseBuilder.append("]");

        return Response.status(200).entity(responseBuilder.toString()).build();
    }


}
