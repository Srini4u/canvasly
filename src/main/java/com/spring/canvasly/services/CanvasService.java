package com.spring.canvasly.services;

import org.apache.commons.lang.StringEscapeUtils;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class CanvasService {

    public static Map<String, Object> landingPage(Request request, Response response) {
        Map<String, Object> attributeMap = new HashMap<String, Object>();
        attributeMap.put( "signedRequest", StringEscapeUtils.escapeHtml( (String) request.raw().getAttribute("signedRequest" ) ));
        return attributeMap;
    }

}
