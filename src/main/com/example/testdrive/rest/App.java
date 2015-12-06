package com.example.testdrive.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author arunkumar_balasubramanian
 * @version 1.0 12/5/15
 */
@Path("app")
public class App {
    @GET
    @Path("sayhi")
    @Produces(MediaType.APPLICATION_JSON)
    public Result sayHi(@QueryParam("input") String input){
        Result result = new Result("sayHi");
        result.setInput("Input: " + input);
        result.setOutput("Output: " + "test");
        return result;
    }

    static class Result{
        String input;
        String output;
        String action;

        public Result(){}

        public Result(String action) {
            this.action = action;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getOutput() {
            return output;
        }

        public void setOutput(String output) {
            this.output = output;
        }
    }
}