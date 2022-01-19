package com.restassured.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class RandomUtils {

	private RandomUtils() {

	}

	private static final SecureRandom random= new SecureRandom();

	public static String generateRandomString(int length) {

		String text ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(length);
		for( int i = 0; i < length; i++ ) 
			sb.append( text.charAt( random.nextInt(text.length()) ) );
		return sb.toString();

	}


	public static String todaysDate() {    
		//2022-01-19T11:02:07.426Z
		Date date = new Date();
		SimpleDateFormat formatObj = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
		formatObj.setTimeZone(TimeZone.getTimeZone("UTC"));
		return formatObj.format(date);
	}    

	public static String generateRandomNumericString(int length) {
		String textnumber ="0123456789";
		StringBuilder sb = new StringBuilder(length);
		for( int i = 0; i < length; i++ ) 
			sb.append( textnumber.charAt( random.nextInt(textnumber.length()) ) );
		return sb.toString();

	}

	public static String generateRandomMasterBillNumber() {
		return "MAMU"+generateRandomString(1)+generateRandomNumericString(2);
	}

	public static String generateRandomMasterFileNumber() {
		return generateRandomString(6)+generateRandomNumericString(3);
	}



	public static String generateRandomHBnumber() {

		return generateRandomString(3)+generateRandomNumericString(3);
	}




}
