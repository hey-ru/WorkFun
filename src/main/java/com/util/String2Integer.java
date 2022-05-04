package com.util;
import java.util.Arrays;
public class String2Integer {
    String testString;

	 
		 public static void main(String[] args){
	        
	        String testString = "[1,2,356,678,3378]";
	        
	        String[] separatedStrings = testString.replaceAll("\\[", "")
	            .replaceAll("]", "").split(",");
	        
	        int[] intArray = new int[separatedStrings.length];
	        
	        for (int i = 0; i < separatedStrings.length; i++) {
	            
	            try {
	                intArray[i] = Integer.parseInt(separatedStrings[i]);
	            } catch (Exception e) {
	                System.out.println("Unable to parse string to int: " + e.getMessage());
	            }
	        }
	        
	        System.out.println(Arrays.toString(intArray));
	    }
	}

