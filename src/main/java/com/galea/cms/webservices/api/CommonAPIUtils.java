package com.galea.cms.webservices.api;

public class CommonAPIUtils {
	
	public static Boolean checkToken(String latestToken, String token) {

		if(latestToken != null && !latestToken.isEmpty() && token != null && !token.isEmpty()) {
			return latestToken.equals(token);
		}
		else {
			return false;
		}
    }
}
