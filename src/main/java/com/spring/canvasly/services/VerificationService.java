package com.spring.canvasly.services;

import spark.Request;
import spark.Response;

import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class VerificationService {

    public static void verify(Request request, Response response) throws Exception {
        Map<String, String> environment = EnvironmentService.getEnvironmentMap();
        Map<String, Object> requestBodyMap = asMap( request.body() , "UTF-8");
        System.out.println(" request body " + request.body()  );
        System.out.println(" requestBodyMap " + requestBodyMap );
        String signedRequest = SecurityService.verifyAndDecodeAsJson( (String) requestBodyMap.get("signed_request"), environment.get("CANVAS_CONSUMER_SECRET") );
        request.params().put( "signedRequest", signedRequest );
    }


    @SuppressWarnings("unchecked")
    public static Map<String, Object> asMap(String urlencoded, String encoding) throws Exception {
        Map<String, Object> map = new LinkedHashMap<>();
        for (String keyValue : urlencoded.trim().split("&")) {
            String[] tokens = keyValue.trim().split("=");
            String key = tokens[0];
            String value = tokens.length == 1 ? null : URLDecoder.decode(tokens[1], encoding);
            String[] keys = key.split("\\.");
            Map<String, Object> pointer = map;
            for (int i = 0; i < keys.length - 1; i++) {
                String currentKey = keys[i];
                Map<String, Object> nested = (Map<String, Object>) pointer.get(keys[i]);
                if (nested == null) {
                    nested = new LinkedHashMap<>();
                }
                pointer.put(currentKey, nested);
                pointer = nested;
            }
            pointer.put(keys[keys.length - 1], value);
        }
        return map;
    }


}
