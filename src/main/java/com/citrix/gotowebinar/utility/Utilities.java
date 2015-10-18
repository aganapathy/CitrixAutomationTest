package com.citrix.gotowebinar.utility;

import java.io.File;
import java.io.IOException;
 import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

public class Utilities {

	public static void deleteAllFiles(File file) throws IOException{
	 
	    	if(file.isDirectory()){
	 
	    		//directory is empty, then delete it
	    		if(file.list().length==0){
	 
	    		   file.delete();
	 
	    		}else{
	 
	    		   //list all the directory contents
	        	   String files[] = file.list();
	 
	        	   for (String temp : files) {
	        	      //construct the file structure
	        	      File fileDelete = new File(file, temp);
	 
	        	      //recursive delete
	        	      deleteAllFiles(fileDelete);
	        	   }
	 
	        	   //check the directory again, if empty then delete it
	        	   if(file.list().length==0){
	           	     file.delete();
	        	   }
	    		}
	 
	    	}else{
	    		//if file, then delete it
	    		file.delete();
	    	}
	    }
	
	public static String generateRandomString()
	{
		return RandomStringUtils.randomAlphanumeric(5);		
	}
	
	 public Date addDays(Date date, int days)
	    {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, days); //minus number would decrement the days
	        return cal.getTime();
	    }
	 
	 public int getYear(Date date)
	 {
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 return cal.get(Calendar.YEAR);

	 }
	
	 public int getMonth(Date date)
	 {
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 return cal.get(Calendar.MONTH);

	 }
	 
	 public int getDate(Date date)
	 {
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 return cal.get(Calendar.DATE);

	 }
	 
		public int getDateDiffInMonths(Date date1, Date date2) {
			
		    int m1 = getYear(date1) * 12 + getMonth(date1);
		    int m2 = getYear(date2) * 12 + getMonth(date2);
		    return m2 - m1;
		}
	 
}
