package com.example.demo;

import com.example.demo.game.App;
import com.example.demo.game.perso.Perso;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Path("/hello")
public class HelloResource {
 private static final ObjectMapper mapper = new ObjectMapper();
    public HelloResource(){};

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        Perso player = App.getInstance().getPersonnage();


        ObjectNode json = mapper.createObjectNode();
        json.put("result", "Jersey JSON example using Jackson 2.x");
        json.put("val" , "blabla");
        json.put("val2" , "654654");
      return Response.status(Response.Status.OK).entity(json).build();
    }

//    public Response test() {
//        ObjectNode json = mapper.createObjectNode();
//
//    }
}