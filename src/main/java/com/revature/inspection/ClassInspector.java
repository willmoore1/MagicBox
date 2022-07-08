package com.revature.inspection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * This class will contain a bunch of methods which have the ability 
 * to inspect the methods & properties of another class.
 * 
 * This is a process called Introspection...this is only possible by calling
 * methods from the java.lang.reflect package.
 * 
 * Reflection is a feature in the Java programming language which allows the executing 
 * program to examine or "introspect" upon itself.
 *
 */

public class ClassInspector {
	
	
	// Let's create a method to pull the constructors from a class
	public static void listPublicConstructor(Class<?> clazz) {

		System.out.println("Printing the public constructors of class " + clazz.getName());
		// build an array of the constructors that belong to a class
		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> constructor : constructors) {

			// print out the name of the constructor and the parameter types
			System.out.println("\tConstructor name: " + constructor.getName());
			System.out.println("\tConstructor param types: " + Arrays.toString(constructor.getParameterTypes()) + "\n");
			System.out.println();			
		}
	}
	
	// List all non public field
	
	public static void listNonPublicField(Class<?> clazz) {
		
		System.out.println("Printing the non-public fields of class " + clazz.getName());
		Field[] fields = clazz.getDeclaredFields();
		
		//Check to see if there are any declared fields
		if (fields.length == 0) {
			System.out.println("There are no fields in " + clazz.getName());
		}
		
		for (Field field: fields) {
			
			// Let's check the modifier to only select those that arent public
			
			if(field.getModifiers() == (Modifier.PUBLIC)) {
				continue;
			}
			
			// as long as the field is NOT public we'll do the following
			System.out.println("\tField name: " + field.getName());
			System.out.println("\tField type: " + field.getType());
			System.out.println("\tIs the field primitive? " + field.getType().isPrimitive());
			System.out.println();
		}
	
		
	}
	
	
	public static void listPublicMethods(Class<?> clazz) {
		// We want to pull all the methods from a specific class
		
		// Let's exclude the methods from the object class because we should already know those
		
		System.out.println("Printing public methods of " + clazz.getName());
		Method[] methods = clazz.getMethods();
		
		for (Method method: methods) {
			
			// exclusion of object class methods
			
			if (method.getDeclaringClass() == Object.class) {
				continue;
			}
			
			// Let's also check to make sure the method is public
			
			if (!(method.getModifiers() == (Modifier.PUBLIC))) {
				continue;
			}
			
			System.out.println("\tMethod Name: " + method.getName());
			System.out.println("\tMethod Param Count: " + method.getParameterCount());
			System.out.println("\tMethod declared class: " + method.getDeclaringClass());
			// We can also pull any annotations by doing the following
			System.out.println("\tMethod Declared Annotations: " + Arrays.toString(method.getDeclaredAnnotations()));
			
			// Now let's print out info for each parameter for the method
			Parameter[] params = method.getParameters();
			
			for (Parameter param: params) {
				// List name and type
				System.out.println("\tParameter Name: " + param.getName());
				System.out.println("\tParameter Type: " + param.getType());
			}
		}
		
		
	}

}

