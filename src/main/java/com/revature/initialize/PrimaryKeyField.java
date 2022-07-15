package com.revature.initialize;

import java.lang.reflect.Field;

import com.revature.annotations.Column;
import com.revature.annotations.Id;

public class PrimaryKeyField {
	
private Field field;
	
	public PrimaryKeyField(Field field) {
		// Let's check the field to ensure it has the column annotation that we're expecting
		
		if(field.getAnnotation(Id.class) == null) {
			throw new IllegalStateException("Cannot create PrimaryKeyField object! Provided field: "
					+ getName() + "is not annotated with @Id");
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
		return field.getAnnotation(Id.class).columnName();
	}
	
	public Object getValue(Object o) {
		try {
			field.setAccessible(true);
			return field.get(o);
		} catch (IllegalArgumentException e) {
			//System.out.println("IllegalArgument");
			return null;
		} catch (IllegalAccessException e) {
			//System.out.println("IllegalAccess");
			return null;
		}
	}

}