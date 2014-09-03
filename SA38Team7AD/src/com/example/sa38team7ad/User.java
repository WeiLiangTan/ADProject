package com.example.sa38team7ad;

public class User extends java.util.HashMap<String,String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4258999070376286774L;

	public User(){
		
	}
	
	public User(String userID, String name, String role, String password, String email, String phone, String deptId, String isTempHead){
		put("UserId",userID);
		put("Name", name);
		put("Role", role);
		put("Password", password);
		put("Email", email);
		put("Phone", phone);
		put("DeptId", deptId);
		put("IsTempHead", isTempHead);
	}
}
