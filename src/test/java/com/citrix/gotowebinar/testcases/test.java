package com.citrix.gotowebinar.testcases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		Date startDate = new Date();
		
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date enddate = fmt.parse("2016-12-06");
		
			    
	}

	
	 public static Date addDays(Date date, int days)
	    {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, days); //minus number would decrement the days
	        return cal.getTime();
	    }
	 

}
