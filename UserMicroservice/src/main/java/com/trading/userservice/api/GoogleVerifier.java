package com.trading.userservice.api;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class GoogleVerifier {
	
	private static GoogleVerifier instance = null;

    private GoogleVerifier() {
    }


    private static GoogleVerifier getInstance() {
        if (instance == null) {
            instance = new GoogleVerifier();
        }
        return instance;
    }



    public static String verifyToken(String token) {
    	getInstance();
        GoogleIdTokenVerifier verifier;
		try {
			verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), 
					new JacksonFactory()).build();
		} catch (GeneralSecurityException e1) {
			System.out.println(e1.getMessage());
			return null;
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
			return null;
		}

        try {
            GoogleIdToken idToken = verifier.verify(token);

            if (idToken != null) {
                return idToken.getPayload().getEmail();
                   
            }else {
            	System.out.println("Invalid");
            	return null;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

	
}
