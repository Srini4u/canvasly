package com.spring.canvasly.spark;

import com.spring.canvasly.services.CanvasService;
import com.spring.canvasly.services.EnvironmentService;
import com.spring.canvasly.services.SecurityService;
import com.spring.canvasly.services.VerificationService;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.afterAfter;
import static spark.Spark.get;

public class SparkMain {
    public static void main(String args[]) throws Exception {

        Map<String, String> environment = EnvironmentService.getEnvironmentMap();

        String port = environment.get("PORT") != null ? environment.get("PORT") : "8000";
        port( Integer.parseInt( port ) );
        staticFiles.location("/static");

        before((request, response) -> {
            //VerificationService.verify(request, response);
        });

        exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
            response.status(400);
            response.type("text/html");
            response.body( exception.getMessage() );
        });

        after((request, response) -> {
        });

        afterAfter((request, response) -> {
        });

        get("/", (req, res) -> {
            return "Home Page";
        });

        post("/index", (request, response) -> {
            VerificationService.verify(request, response);
            response.type("text/html");
            return new ModelAndView(CanvasService.landingPage(request, response), "canvas.ftl");
        }, new FreeMarkerEngine());

        post("/opentext", (request, response) -> {
            VerificationService.verify(request, response);
            response.type("text/html");
            return new ModelAndView(CanvasService.landingPage(request, response), "canvas.ftl");
        }, new FreeMarkerEngine());

        post("/radius", (request, response) -> {
            VerificationService.verify(request, response);
            response.type("text/html");
            return new ModelAndView(CanvasService.landingPage(request, response), "canvas.ftl");
        }, new FreeMarkerEngine());

    }

}
