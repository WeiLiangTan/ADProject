package com.example.sa38team7ad;

public class CollectionPoint extends java.util.HashMap<String,String> {

	public CollectionPoint(){
		
	}
	
	public CollectionPoint(String cpID, String cpName){
		put("CpID", cpID);
		put("CpName",cpName);
	}
}