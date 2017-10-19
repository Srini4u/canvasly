package com.spring.canvasly.services;

import spark.Request;
import spark.Response;

import java.util.Map;

public class VerificationService {
    public static void verify(Request request, Response response) throws Exception {
        Map<String, String> environment = EnvironmentService.getEnvironmentMap();
        String signedRequest = SecurityService.verifyAndDecodeAsJson(request.params().get("signed_request"), environment.get("CANVAS_CONSUMER_SECRET") );
        request.params().put( "signedRequest", signedRequest );

    }
}
