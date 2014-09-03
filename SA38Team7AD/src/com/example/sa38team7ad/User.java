package com.example.sa38team7ad;

public class User extends java.util.HashMap<String,String> {

	public User(){
		
	}
	public User(String name, String role, String email, String phone, String deptId, String isTempHead){
		put("Name", name);
		put("Role", role);
		put("Email", email);
		put("Phone", phone);
		put("DeptId", deptId);
		put("IsTempHead", isTempHead);
	}
	
	public User(String name, String role, String password, String email, String phone, String deptId, String isTempHead){
		this(name, role, email,phone,deptId, isTempHead);
		put("Password", password);
	}
}
