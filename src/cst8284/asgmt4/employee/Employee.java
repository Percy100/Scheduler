
/* Course Name: CST8284 - Object Oriented Programming
   Student Name: Percy Obayuwana
   Class name: Employee
   Date: 27/11/2019
 */

package cst8284.asgmt4.employee;

import java.util.Scanner;

/**
 * an abstract class designed to hold employee information and provide required method to be implemented by it's subclass
 * @author Percy Obayuwana
 * @version 1.0
 * 
 */
public abstract class Employee {
	
	/**
	 * instance variable referencing full name of employee
	 */
	private String fullName;

	/**
	 * default constructor with no arg to initialize instance of Employee with default value(S)
	 */
	protected Employee() {this("unknown");}

	/**
	 * one arg constructor for Employee that takes a string as parameter
	 * @param fullName name of the instance of subclass of Employee
	 */
	protected Employee(String fullName) {setName(fullName);}

	protected static Scanner scan = new Scanner(System.in);

	/**
	 * sets fullName as required
	 * @param fullName name to be set
	 */
	public void setName(String fullName) {this.fullName = fullName;}

	/**
	 * returns current name
	 * @return string name
	 */
	public String getName() {return fullName;}

	public abstract String getActivityType();

	/**
	 * overridden superclass method that returns the string representation of the instance variable
	 */
	@Override
	public String toString() {return getName();}
}