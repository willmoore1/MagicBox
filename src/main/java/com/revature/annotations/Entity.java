package com.revature.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Annotations in Java are a type of marker interface
 * 
 * Annotations start with an @
 * Annotations do not change the ACTION of a compiled program
 * Annotations are used to associate metadata about the program elements(constructor, fields, methods)
 * Annotations change the way the program is treated by the compiler
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
	
	// Let's define a variable that will need to be instantiated
	String tableName();
	
}

/*
 * Additional things we should add
 * 
 * Target:
 * 	Tells where an annotation is valid to be used
 * 
 * Retention Policy:
 * There are 3
 *   - Source (annotation is accessible during compilation)
 *   - Class (annotation is accessible during class loading)
 *   - Runtime (annotation is accessible during runtime)
 *   	- this is the most used in an ORM
 * 
 */