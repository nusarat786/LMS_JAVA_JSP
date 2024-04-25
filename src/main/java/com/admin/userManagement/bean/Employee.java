package com.admin.userManagement.bean;

import java.io.InputStream;
import java.sql.Blob;

public class Employee {
	
	public int _id;
	public int _otp;
	private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String department;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String address;
    private String status;
    private double leaveBalance;
    private InputStream employeePhoto;
    public Blob employeePhotoo;
    private int isAdmin;
    
    
    public Employee() {
    	
    }
    public Employee(
    		String firstName, String lastName, String email, String position, String department,
			String dateOfBirth, String gender, String phoneNumber, String address, String status, double leaveBalance,
			InputStream employeePhoto) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.position = position;
		this.department = department;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
		this.leaveBalance = leaveBalance;
		this.employeePhoto = employeePhoto;
	}
    
    public Employee(
    		int id,
    		String firstName, String lastName, String email, String position, String department,
			String dateOfBirth, String gender, String phoneNumber, String address, String status, double leaveBalance,
			InputStream employeePhoto) {
		super();
		_id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.position = position;
		this.department = department;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
		this.leaveBalance = leaveBalance;
		this.employeePhoto = employeePhoto;
	}
    
    public Employee(
    		int id,
    		String firstName, String lastName, String email, String position, String department,
			String dateOfBirth, String gender, String phoneNumber, String address, String status, double leaveBalance,
			Blob employeePhotoo) {
		super();
		_id = id;
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.position = position;
		this.department = department;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
		this.leaveBalance = leaveBalance;
		this.employeePhotoo = employeePhotoo;
	}
    
    public Employee(
    		int id,int otp,
    		String firstName, String lastName, String email, String position, String department,
			String dateOfBirth, String gender, String phoneNumber, String address, String status, double leaveBalance,
			Blob employeePhotoo) {
		super();
		_id = id;
		_otp = otp;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.position = position;
		this.department = department;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
		this.leaveBalance = leaveBalance;
		this.employeePhotoo = employeePhotoo;
	}
    
    public Employee(
    		int id,int otp,
    		String firstName, String lastName, String email, String position, String department,
			String dateOfBirth, String gender, String phoneNumber, String address, String status, double leaveBalance,
			Blob employeePhotoo,int isAdmin) {
		super();
		_id = id;
		_otp = otp;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.position = position;
		this.department = department;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
		this.leaveBalance = leaveBalance;
		this.employeePhotoo = employeePhotoo;
		this.isAdmin = isAdmin;
	}
    

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getLeaveBalance() {
		return leaveBalance;
	}
	public void setLeaveBalance(double leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
	public InputStream getEmployeePhoto() {
		return employeePhoto;
	}
	public void setEmployeePhoto(InputStream employeePhoto) {
		this.employeePhoto = employeePhoto;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
    

}
