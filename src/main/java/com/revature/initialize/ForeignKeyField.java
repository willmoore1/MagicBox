package com.revature.initialize;

import java.lang.reflect.Field;

import com.revature.annotations.Column;
import com.revature.annotations.JoinColumn;

public class ForeignKeyField {
	
private Field field;
	
	public ForeignKeyField(Field field) {
		// Let's check the field to ensure it has the column annotation that we're expection
		
		if(field.getAnnotation(JoinColumn.class) == null) {
			throw new IllegalStateException("Cannot create ForeignKeyField object! Provided field: "
					+ getName() + "is not annotated with @JoinColumn");
		}
		
		this.field = field;
		
	}
	
	public String getName() {
		return field.getName();
	}
	
	// Return the type of the field that annotated
	public Class<?> getType(){
		return field.getType();
	}
	
	// Let's add one final method to extract the column so we can utilize if we're building sql tables
	public String getColumnName() {
		return field.getAnnotation(JoinColumn.class).columnName();
	}

}