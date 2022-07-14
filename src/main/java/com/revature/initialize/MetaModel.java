package com.revature.initialize;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import com.revature.annotations.Id;
import com.revature.annotations.JoinColumn;
import com.revature.annotations.Table;

/*
 * This class's job is to gather as much information as possible about the class that we want
 * to transpose into a DB Entity
 * 
 * This class's job is to model data about another class
 */

public class MetaModel<T> { // We're inferring that the MetaModel Class can only be a metamodel of another class
	
	
	private Class<?> clazz;
	private PrimaryKeyField primaryKeyField;
	private List<ColumnField> columnFields;
	private List<ForeignKeyField> foreignKeyFields;
	
	
	// Let's create a constructor that we'll only use when we call another method
	
	public MetaModel(Class<?> clazz) {
		this.clazz  = clazz;
		this.columnFields = new LinkedList<>();
		this.foreignKeyFields = new LinkedList<>();
	}
	
	// Let's create a method to check and then transpose a normal java class to a MetaModel Class
	//  This means we need to check for the @Entity annotation
	
	public static MetaModel<Class<?>> of(Class<?> clazz){
		
		// Let's check for the @entity notation
		
		if (clazz.getAnnotation(Table.class) == null) {
			throw new IllegalStateException("Cannot create MetaModel Object! Provided Class: " + clazz.getName() +
											" is not annotated with @Entity");
		}
		
		return new MetaModel<Class<?>>(clazz);
	}
	
	// We should create method to gather more data about our class
	// Let's find the column, primary key, and any foreign keys
	
	public List<ColumnField> getColumns(){
		
		// This method return all the properties of the class that are marked with @Column
		
		Field[] fields = clazz.getDeclaredFields();
		
		//for each field within the above field[] check to see if it has a column annotation and if it does,
		// add it to the metamodel's linked list
		
		for (Field field: fields) {
			columnFields.add(new ColumnField(field));
		}
		
		// Let's just add some extra logic in the case that the entity doesn't have any column fields
		
		if(columnFields.isEmpty()) {
			throw new RuntimeException("No columns found in: " + clazz.getName());
		}
		
		return columnFields;
	}
	
	// Let's do the foreign key field first since this should match almost exactly to our getColumnFields
	
	public List<ForeignKeyField> getForeignKeys(){
		
		// This method return all the properties of the class that are marked with @Column
		
				Field[] fields = clazz.getDeclaredFields();
				
				//for each field within the above field[] check to see if it has a column annotation and if it does,
				// add it to the metamodel's linked list
				
				for (Field field: fields) {
					
					// Create a column object, this will not be null if the field is annotated with @Column
					JoinColumn foreignKey = field.getAnnotation(JoinColumn.class);
					
					if (foreignKey != null) {
						// This means it's marked with @column and we can add it to our list
						foreignKeyFields.add(new ForeignKeyField(field));
					}
				}
				
				// Let's just add some extra logic in the case that the entity doesn't have any column fields
				
				if(foreignKeyFields.isEmpty()) {
					throw new RuntimeException("No foreign keys found in: " + clazz.getName());
				}
				
				return foreignKeyFields;
		
	}
	
	// Let's construct a method to extract out the primary key of a metamodel object
	
	public PrimaryKeyField getPrimaryKey() {
		
		// Capture an array of the fields
		Field[] fields = clazz.getDeclaredFields();
		
		// Check for primary key
		for (Field field: fields) {
			Id primaryKey = field.getAnnotation(Id.class);
			
			
			// If the primary key is not null then generate a primary key field object from that field
			if (primaryKey != null) {
				// This will capture the first and (hopefully) only primary key that exists
				return new PrimaryKeyField(field);
			}
		}
		
		throw new RuntimeException("Did not find a field annotated with @Id in class: " + clazz.getName());
	}
	
	// Just to make things simpler if we call this model
	
	public String getSimpleClassName() {
		return clazz.getSimpleName();
	}
	
	public String getClassName() {
		return clazz.getName();
	}
	
	public String getTableName() {
		String name = clazz.getAnnotation(Table.class).name();
		return name;
	}
	public T getObject(Object[] fieldVals) {
		Constructor<?>[] allConstructors = clazz.getDeclaredConstructors();
		for(Constructor<?> ctor : allConstructors) {
			Class<?>[] pType = ctor.getParameterTypes();
			if(pType.length == clazz.getFields().length) {
				try {
					T ret = (T) ctor.newInstance(fieldVals);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}