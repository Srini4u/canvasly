package com.spring.canvasly.services;

import org.apache.commons.lang.StringEscapeUtils;
import spark.Request;
import spark.Response;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VerificationService {

    public static void verify(Request request, Response response) throws Exception {
        Map<String, String> environment = EnvironmentService.getEnvironmentMap();
        if( request.raw().getParameterMap().get("signed_request") != null ) {
            String decryptedSignedRequest = SecurityService.verifyAndDecodeAsJson( request.raw().getParameterMap().get("signed_request")[0] , environment.get("CANVAS_CONSUMER_SECRET") );
            System.out.println(" verification successful . signedRequest " + decryptedSignedRequest );
            request.raw().setAttribute( "signedRequest", decryptedSignedRequest );
        } else {
            throw new Exception(" Signed request is missing ");
        }
    }
}
