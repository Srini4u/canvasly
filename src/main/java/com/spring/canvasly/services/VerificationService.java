package com.spring.canvasly.services;

import spark.Request;
import spark.Response;

import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class VerificationService {

    public static final String signedRequestPrefix = "signed_request=";

    public static void verify(Request request, Response response) throws Exception {
        Map<String, String> environment = EnvironmentService.getEnvironmentMap();
        if( request.body().startsWith(signedRequestPrefix) ) {
            String signedRequestInput = request.body().substring( signedRequestPrefix.length() );
            String decryptedSignedRequest = SecurityService.verifyAndDecodeAsJson( signedRequestInput, environment.get("CANVAS_CONSUMER_SECRET") );
            request.params().put( "signedRequest", decryptedSignedRequest );
        } else {
            throw new Exception(" Signed Request not found ");
        }
    }
}
