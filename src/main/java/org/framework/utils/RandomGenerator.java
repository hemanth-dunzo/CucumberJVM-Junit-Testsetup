package org.framework.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.base.CharMatcher;

public class RandomGenerator {
	
	  public  String random(Integer length, PermittedCharacters permittedCharacters) {
	        String randomString = null;
	        if (PermittedCharacters.ALPHABETS.equals(permittedCharacters)) {
	            randomString = randomString(length);
	        } else if (PermittedCharacters.NUMERIC.equals(permittedCharacters)) {
	            randomString = randomInteger(length);
	        } else if (PermittedCharacters.ALPHANUMERIC.equals(permittedCharacters)) {
	            randomString = randomAlphanumeric(length);
	        } else if (PermittedCharacters.ANY_CHARACTERS.equals(permittedCharacters)) {
	            randomString = randomAsciiCharacters(length);
	        } else if (PermittedCharacters.ANY_CHARACTERS_SUPPORTS_MULTILINGUAL.equals(permittedCharacters)) {
	            randomString = randomAsciiCharacters(length);
	        }
	        return randomString;
	    }

	   
	    private  String randomInteger(Integer length) {
	        return RandomStringUtils.randomNumeric(length);
	    }

	    
	    private  String randomString(Integer length) {
	        return RandomStringUtils.random(length, true, false);
	    }

	  
	    private  String randomAlphanumeric(Integer length) {
	        return RandomStringUtils.randomAlphanumeric(length);
	    }

	   
	    public  String randomAlphabetic(Integer length) {
	        return RandomStringUtils.randomAlphabetic(length);
	    }

	  
	    public  String randomEmailAddress(Integer length) {
	        String email = randomAlphanumeric(length) + "@unilogcorp.com";
	        return email.toLowerCase();
	    }

	    public  String randomPlusOrMinus() {
	        List<String> item = new LinkedList<>();
	        item.add("-");
	        item.add("+");

	        Random rand = new Random();
	        int choice = rand.nextInt(item.size());
	        return item.get(choice);
	    }


	    public  DateTime randomDOB() {
	        DateTime dateTime = new DateTime();
	        dateTime = dateTime.minusYears((int) (18 + (Math.random() * ((50 - 18) + 1))));
	        return dateTime.minusYears((int) (18 + (Math.random() * ((50 - 18) + 1))));
	    }

	    public  String formatDate(DateTime dateTime, String dateformat) {
	        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(dateformat);
	        return dateTime.toString(dateTimeFormatter);
	    }

	    public  DateTimeFormatter dateFormatterWithLocale(Locale locale) {
	        return DateTimeFormat.mediumDate().withLocale(locale);
	    }

	    public  String dateWithNoLeadingZero(String dateWithLeadingZero) {
	        String dateWithNoLeadingZero;
	        dateWithNoLeadingZero = CharMatcher.is('0').trimLeadingFrom(dateWithLeadingZero);
	        return dateWithNoLeadingZero;
	    }

	    public  String randomFutureFormattedDate(String dateformat) {
	        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(dateformat);
	        DateTime dateTime = new DateTime();
	        final DateTime plusYears = dateTime.plusYears((int) (1 + Math.random() * (10 - 1)));
	        return plusYears.toString(dateTimeFormatter);
	    }

	    private  String randomAsciiCharacters(Integer characterAmount) {
	        return RandomStringUtils.random(characterAmount, 32, 127, false, false);
	    }
}
