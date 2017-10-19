package com.spring.canvasly.services;

import org.apache.commons.lang.StringEscapeUtils;
import spark.Request;
import spark.Response;

import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class VerificationService {

    public static final String signedRequestPrefix = "signed_request=";

    public static void verify(Request request, Response response) throws Exception {
        Map<String, String> environment = EnvironmentService.getEnvironmentMap();
        System.out.println(" request.body() " + request.body() );
        if( request.body().startsWith(signedRequestPrefix) ) {
            String signedRequestInput = StringEscapeUtils.unescapeHtml ( request.body().substring( signedRequestPrefix.length() ) );
            String decryptedSignedRequest = SecurityService.verifyAndDecodeAsJson( signedRequestInput, environment.get("CANVAS_CONSUMER_SECRET") );
            request.params().put( "signedRequest", StringEscapeUtils.escapeHtml(decryptedSignedRequest) );
            System.out.println(" verification successful . signedRequest " + decryptedSignedRequest );
        } else {
            throw new Exception(" Signed Request not found ");
        }
    }
}
