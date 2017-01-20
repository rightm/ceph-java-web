package com.web.util;

public class util {
	
	public static void nullCheck(Object r){
		if( null == r ){
			throw new NullPointerException("No rados defined") ;
		}
	}
	
}