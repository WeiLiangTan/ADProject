package com.example.sa38team7ad;

public class CollectionPoint extends java.util.HashMap<String,String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5217340160396981417L;

	public CollectionPoint(){
		
	}
	
	public CollectionPoint(String cpID, String cpName){
		put("CpID", cpID);
		put("CpName",cpName);
	}
}