package com.customer.validation;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {

	public static boolean checkOccupation(String str) {
		String target = str;
		String lowerCase = target.toLowerCase();
		switch (lowerCase) {
		case "plumber":
			return true;
		case " chef":
			return true;
		case "developer":
			return true;
		case "carpenter":
			return true;
		case "other":
			return true;
		default:
			return false;
		}

	}

	public static boolean checkCustomerGroup(String str) {

		String target = str;
		String lowerCase = target.toLowerCase();

		switch (lowerCase) {
		case "hikeon":
			return true;
		case " chef":
			return true;
		case "developer":
			return true;
		case "na":
			return true;
		default:
			return false;
		}

	}
	public static boolean checkDobFormat(String dateToCheck) {
		if (isValidDateFormat(dateToCheck, "yyyy-MM-dd")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidDateFormat(String dateStr, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static boolean calaculateAge(String dob) {
		int age = 0;
		try {
			Timestamp dateOfBirth = getTimestampFromString(dob);
			Timestamp currentDate = new Timestamp(System.currentTimeMillis());

			age = calculateAge(dateOfBirth, currentDate);

		} catch (ParseException e) {
			System.err.println("Error parsing date: " + e.getMessage());
		}
		if(age>=18) {
			return true;
		}
		return false;

	}

	
	public static Timestamp getTimestampFromString(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = dateFormat.parse(dateString);
		return new Timestamp(parsedDate.getTime());
	}

	public static int calculateAge(Timestamp dateOfBirth, Timestamp currentDate) {
		long millisecondsPerYear = 1000L * 60 * 60 * 24 * 365;
		long ageInMillis = currentDate.getTime() - dateOfBirth.getTime();
		return (int) (ageInMillis / millisecondsPerYear);
	}

	public static String formatDate(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date(timestamp.getTime()));
	}
}
