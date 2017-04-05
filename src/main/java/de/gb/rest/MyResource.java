package de.gb.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        GotIt response = new GotIt("Got it!");
        return gson.toJson(response);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response readJson(String payload) {
        GotIt gotIt = gson.fromJson(payload, GotIt.class);

        return Response.ok(gotIt.getMessage()).build();
    }

    private class GotIt {

        final private String message;

        public GotIt(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
