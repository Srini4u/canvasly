package com.spring.canvasly.services;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class CanvasService {

    public static Map<String, Object> landingPage(Request request, Response response) {
        Map<String, Object> attributeMap = new HashMap<String, Object>();
        attributeMap.put( "signedRequest",  request.params().get( "signedRequest" ) );
        return attributeMap;
    }

}
