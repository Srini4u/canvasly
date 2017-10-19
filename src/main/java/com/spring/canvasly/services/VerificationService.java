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
        String[] stsrs = request.raw().getParameterMap().get("signed_request");
        for( int i=0; i<stsrs.length ; i++ ) {
            System.out.println( i + "   "  + stsrs[i] );
        }
        String decryptedSignedRequest = SecurityService.verifyAndDecodeAsJson( request.raw().getParameterMap().get("signed_request")[0] , environment.get("CANVAS_CONSUMER_SECRET") );
        request.params().put( "signedRequest", StringEscapeUtils.escapeHtml(decryptedSignedRequest) );
        System.out.println(" verification successful . signedRequest " + decryptedSignedRequest );
    }
}
