package com.revature.initialize;

import java.lang.reflect.Field;

import com.revature.annotations.Column;

/*
 * The purpose of this class is to extract fields of a class marked with @Column
 * Which is an annotation that we've defined in our annotations package
 * 
 * I'll use this class to extract the data type of those fields so I can get a better idea of what
 * SQL Type constraints would be represent that column in a database
 */
public class ColumnField {
	
	// This class models the column we're setting up from a class' fields
	
	private Field field;
	
	public ColumnField(Field field) {
		this.field = field;
	}
	
	public String getName() {
		return field.getName();
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
	
	// Return the type of the field that annotated
	public Class<?> getType(){
		return field.getType();
	}
	
	// Let's add one final method to extract the column so we can utilize if we're building sql tables
	public String getColumnName() {
		if(field.isAnnotationPresent(Column.class)) return field.getAnnotation(Column.class).name();
		return(field.getName());
	}

}
