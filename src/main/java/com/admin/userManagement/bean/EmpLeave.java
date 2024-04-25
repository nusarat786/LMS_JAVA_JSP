package com.admin.userManagement.bean;

import java.io.InputStream;
import java.sql.Blob;

public class EmpLeave {
	
	private int leaveId;
    private int employeeId;
    private String leaveType;
    private String startDate;
    private String endDate;
    private String applyDate;
    private String reason;
    private String lstatus;
    private String approvalBy;
    private String approvalDate;
    private int duration;

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
    private String estatus;
    private double leaveBalance;
    private InputStream employeePhoto;
    public Blob employeePhotoo;
    private int isAdmin;
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getLstatus() {
		return lstatus;
	}
	public void setLstatus(String lstatus) {
		this.lstatus = lstatus;
	}
	public String getApprovalBy() {
		return approvalBy;
	}
	public void setApprovalBy(String approvalBy) {
		this.approvalBy = approvalBy;
	}
	public String getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int get_otp() {
		return _otp;
	}
	public void set_otp(int _otp) {
		this._otp = _otp;
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
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
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
	public Blob getEmployeePhotoo() {
		return employeePhotoo;
	}
	public void setEmployeePhotoo(Blob employeePhotoo) {
		this.employeePhotoo = employeePhotoo;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
}
