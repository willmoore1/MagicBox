package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.initialize.Configuration;
import com.revature.initialize.ConnectionUtil;
import com.revature.initialize.SessionFactory;
import com.revature.models.Student;
import com.revature.runtime.Session;

public class App {
	// Path to the cfg.xml file
	private static String filePath = "src/main/java/resource/magicbox.cfg.xml";
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		// Initialize config and add student as a class
		Configuration config = new Configuration();
		String studentClassName = Student.class.getName();
		config.addClass(studentClassName);
		
		// Create a SessionFactory using the specified file 
		SessionFactory sessionFactory = config.configure(filePath);
		
		// Create a new Student session using our factory
		Session<?> session = sessionFactory.createSession(filePath);
		
		int input = 0;
		
		while(input != 5) {
			System.out.println("Welcome to the student database!");
			System.out.println("Press 1 to add a new student.\nPress 2 to view a student's information.\nPress 3 to update a student's information.\nPress 4 to delete a student.\nPress 5 to quit the application.");
			input = scanner.nextInt();
			
			switch(input) {
				case 1:
					createStudent();
					break;
				case 2:
					readStudent();
					break;
				case 3:
					updateStudent();
					break;
				case 4:
					deleteStudent();
					break;
				case 5:
					System.out.println("Exiting application...");
					break;
			}
		}
	}
	
	private static void createStudent() {
		
	}
	
	private static void readStudent() {
		
	}
	
	private static void updateStudent() {
		
	}
	
	private static void deleteStudent() {
		
	}
	
}
