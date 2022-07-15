package com.revature;

import java.util.List;
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
		Session<Student> session = (Session<Student>) sessionFactory.createSession(studentClassName);
		
		
		int input = 0;
		
		while(input != 5) {
			System.out.println("Welcome to the student database!");
			System.out.println("Press 1 to add a new student.\nPress 2 to view a student's information.\nPress 3 to update a student's information.\nPress 4 to delete a student.\nPress 5 to quit the application.");
			input = scanner.nextInt();
			scanner.nextLine();
			
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
	
	private static void createStudent(Session<Student> studentSession) {
		// Get student info 
		System.out.println("Enter the student's first name:");
		String firstName = scanner.nextLine();
		
		System.out.println("Enter the student's last name:");
		String lastName = scanner.nextLine();
		
		System.out.println("Enter the student's email:");
		String email = scanner.nextLine();
		
		Student student = new Student(firstName, lastName, email);
		
		// Save a new Student object, then commit it to db
		int id = studentSession.savePK(student);
		System.out.println("Added a new student with ID " + id);
	}
	
	private static void readStudent(Session<Student> studentSession) {
		// Get student id
		System.out.println("Enter the student's id:");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Found the following student info:");
		Student s = studentSession.get("id", id).get(0);
		System.out.println(s);
		
	}
	
	private static void updateStudent(Session<Student> studentSession) {
		// Get student id and info 
		System.out.println("Enter the student's ID:");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Enter the student's first name:");
		String firstName = scanner.nextLine();
		
		System.out.println("Enter the student's last name:");
		String lastName = scanner.nextLine();
		
		System.out.println("Enter the student's email:");
		String email = scanner.nextLine();
		
		Student s = new Student(firstName, lastName, email);
		s.setId(id);
		
		studentSession.update(s);
		System.out.println("Successfully updated the following student:");
		System.out.println(s);
	}
	
	private static void deleteStudent(Session<Student> studentSession) {
		// Get student id
		System.out.println("Enter the student's id:");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		Student s = studentSession.get("id", id).get(0);
		
		studentSession.delete(s);
		System.out.println("Successfully deleted student");
	}
	
}
