package com.springboot.app.model;

public class EmployeeRequest {

	private Integer personId;

	private String employeeNum;

	private String employedDate;

	private String terminatedDate;

	public EmployeeRequest(Integer personId, String employeeNum, String employedDate, String terminatedDate) {
		super();
		this.personId = personId;
		this.employeeNum = employeeNum;
		this.employedDate = employedDate;
		this.terminatedDate = terminatedDate;
	}

	public EmployeeRequest() {
		super();
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}

	public String getEmployedDate() {
		return employedDate;
	}

	public void setEmployedDate(String employedDate) {
		this.employedDate = employedDate;
	}

	public String getTerminatedDate() {
		return terminatedDate;
	}

	public void setTerminatedDate(String terminatedDate) {
		this.terminatedDate = terminatedDate;
	}
}
