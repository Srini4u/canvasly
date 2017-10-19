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

    public static final String signedRequestPrefix = "signed_request=";

    public static void verify(Request request, Response response) throws Exception {
        Map<String, String> environment = EnvironmentService.getEnvironmentMap();
        System.out.println(" request.body() " + request.body() );
        if( request.body().startsWith(signedRequestPrefix) ) {
            String signedRequestInput = ( request.body().substring( signedRequestPrefix.length() ) );


            byte b[] = signedRequestInput.getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            String[] objs = ( String[]) si.readObject();

            String decryptedSignedRequest = SecurityService.verifyAndDecodeAsJson( objs[0] , environment.get("CANVAS_CONSUMER_SECRET") );
            request.params().put( "signedRequest", StringEscapeUtils.escapeHtml(decryptedSignedRequest) );
            System.out.println(" verification successful . signedRequest " + decryptedSignedRequest );
        } else {
            throw new Exception(" Signed Request not found ");
        }
    }
}
