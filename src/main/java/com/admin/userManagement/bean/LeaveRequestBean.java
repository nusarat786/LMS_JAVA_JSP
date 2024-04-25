package com.admin.userManagement.bean;

public class LeaveRequestBean {
    private int leaveId;
    private int employeeId;
    private String leaveType;
    private String startDate;
    private String endDate;
    private String applyDate;
    private String reason;
    private String status;
    private String approvalBy;
    private String approvalDate;
    private int duration;

    // Constructor
    public LeaveRequestBean(int employeeId, String leaveType, String startDate, String endDate, String applyDate, String reason, String status, String approvalBy, String approvalDate) {
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applyDate = applyDate;
        this.reason = reason;
        this.status = status;
        this.approvalBy = approvalBy;
        this.approvalDate = approvalDate;
        this.duration = duration;
    }
    
    public LeaveRequestBean() {
        
    }
    
 // Constructor
    public LeaveRequestBean(int employeeId, String leaveType, String startDate, String endDate, String applyDate, String reason, String status) {
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applyDate = applyDate;
        this.reason = reason;
        this.status = status;

    }

    // Getters and setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
