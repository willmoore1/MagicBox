package com.revature.models;

import com.revature.annotations.Column;
import com.revature.annotations.Id;
import com.revature.annotations.Table;

@Table(name = "test_table")
public class DummyClass {
	
	@Id(columnName = "test_id")
	private int testId;
	
	@Column(name = "test_field_1")
	private String testField1;
	
	private int randomNumber;

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getTestField1() {
		return testField1;
	}

	public void setTestField1(String testField1) {
		this.testField1 = testField1;
	}

	public DummyClass(int testId, String testField1, int randomNumber) {
		super();
		this.testId = testId;
		this.testField1 = testField1;
		this.randomNumber = randomNumber;
	}

	public DummyClass() {
		// TODO Auto-generated constructor stub
	}
	


}