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
                      
        suggests.add("\"Page d'aide\"");
        responseBuilder.append(suggests.toString());
        responseBuilder.append(",");
        final ArrayList<String> descriptions = new ArrayList<>();
        for (int i = 0; i < suggests.size(); i++) {
            descriptions.add("\"test\"");
            if (i > 3) {
                break;
            }
        }
        descriptions.add("\"AIDE\"");
        responseBuilder.append(descriptions.toString());
        responseBuilder.append(",");
        final ArrayList<String> urls = new ArrayList<>();
        for (int i = 0; i < suggests.size(); i++) {
            urls.add("\"http://localhost/index.html\"");
            if (i > 3) {
                break;
            }
        }
        urls.add("\"http://localhost/index.html\"");
        responseBuilder.append(urls.toString());
        responseBuilder.append("]");

        return Response.status(200).entity(responseBuilder.toString()).build();
    }


}
