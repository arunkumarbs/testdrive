package com.example.testdrive.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author arunkumar_balasubramanian
 * @version 1.0 12/5/15
 */
@Path("/user")
public class App {

    @GET
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name) {
        String output = "Hi from Jersey REST Service: " + name;
        return output;
    }
}