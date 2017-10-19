package com.spring.canvasly.spark;

import com.spring.canvasly.services.EnvironmentService;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

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
        });

        after((request, response) -> {
        });

        afterAfter((request, response) -> {
        });

        get("/", (req, res) -> {
            return "";
        });

        get("/opentext", (req, res) -> {
            return "opentext";
        });

        get("/radius", (req, res) -> {
            return "radius";
        });
    }

}
