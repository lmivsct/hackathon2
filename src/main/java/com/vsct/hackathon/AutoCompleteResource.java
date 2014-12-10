package com.vsct.hackathon;

import java.util.ArrayList;
import java.util.List;
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
        final List<String> suggestsAll = autoCompleteService.getSuggests(query);
        final ArrayList<String> suggests = new ArrayList<>();
        for (int i = 0; i < suggestsAll.size(); i++) {
            suggests.add(suggestsAll.get(i));
            if (i > 3) {
                break;
            }
        }
                      
        suggests.add("\"Aide\"");
        responseBuilder.append(suggests.toString());
        responseBuilder.append("]");

        return Response.status(200).entity(responseBuilder.toString()).build();
    }


}
