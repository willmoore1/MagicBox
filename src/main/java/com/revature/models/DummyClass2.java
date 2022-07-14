package com.revature.models;

import java.util.Objects;

import com.revature.annotations.Column;
import com.revature.annotations.Id;
import com.revature.annotations.Table;

@Table(name = "test_table2")
public class DummyClass2 {
	
	@Id(columnName = "test_id")
	private int testId;
	
	@Column(name = "test_field_1")
	private String testField1;
	
	private int randomNumber;
	
	@Column(name = "test_field_2")
	private double randomNumber2;

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

	public DummyClass2(int testId, String testField1, int randomNumber) {
		super();
		this.testId = testId;
		this.testField1 = testField1;
		this.randomNumber = randomNumber;
	}

	public DummyClass2(String testField1, int randomNumber, double randomNumber2) {
		super();
		this.testField1 = testField1;
		this.randomNumber = randomNumber;
		this.randomNumber2 = randomNumber2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(randomNumber, randomNumber2, testField1, testId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DummyClass2 other = (DummyClass2) obj;
		return randomNumber == other.randomNumber
				&& Double.doubleToLongBits(randomNumber2) == Double.doubleToLongBits(other.randomNumber2)
				&& Objects.equals(testField1, other.testField1) && testId == other.testId;
	}

	public DummyClass2(int testId, String testField1, int randomNumber, double randomNumber2) {
		super();
		this.testId = testId;
		this.testField1 = testField1;
		this.randomNumber = randomNumber;
		this.randomNumber2 = randomNumber2;
	}

	public DummyClass2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DummyClass2 [testId=" + testId + ", testField1=" + testField1 + ", randomNumber=" + randomNumber
				+ ", randomNumber2=" + randomNumber2 + "]";
	}
	


}