package com.vsct.hackathon;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONException;

@Path("/autocomplete")
public class AutoCompleteResource {


    @GET
    @Produces("application/json")
    public Response autocomplete(@QueryParam("query") String query) throws JSONException {
        final StringBuilder responseBuilder = new StringBuilder("[\"" + query + "\"");
        responseBuilder.append(",");
        responseBuilder.append(proposals);
        responseBuilder.append("]");

        return Response.status(200).entity(responseBuilder.toString()).build();
    }


}
