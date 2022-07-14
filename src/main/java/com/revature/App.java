package com.revature;

import java.util.Scanner;

import com.revature.initialize.Configuration;
import com.revature.initialize.SessionFactory;
import com.revature.models.Student;
import com.revature.runtime.Session;

public class App {
	// Path to the cfg.xml file
	private static String filePath = "src/main/java/resource/magicbox.cfg.xml";
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
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
					createStudent(session);
					break;
				case 2:
					readStudent(session);
					break;
				case 3:
					updateStudent(session);
					break;
				case 4:
					deleteStudent(session);
					break;
				case 5:
					System.out.println("Exiting application...");
					break;
			}
		}
	}
	
	private static void createStudent(Session<?> studentSession) {
		// Get student info 
		System.out.println("Enter the student's first name:");
		String firstName = scanner.nextLine();
		
		System.out.println("Enter the student's last name:");
		String lastName = scanner.nextLine();
		
		System.out.println("Enter the student's email:");
		String email = scanner.nextLine();
		
		// Save a new Student object, then commit it to db
		//studentSession.save(new Student(firstName, lastName, email));
		//studentSession.commit();
	}
	
	private static void readStudent(Session<?> studentSession) {
		
	}
	
	private static void updateStudent(Session<?> studentSession) {
		
	}
	
	private static void deleteStudent(Session<?> studentSession) {
		
	}
	
}
