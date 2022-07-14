package com.revature.models;

import com.revature.annotations.Column;
import com.revature.annotations.Id;

public class DummyClass2 {
	@Id(columnName = "test_id_2")
	private int testId;
	
	@Column(name = "test_field_2")
	private String testField1;

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
}
