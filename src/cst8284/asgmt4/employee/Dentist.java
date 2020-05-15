
/* Course Name: CST8284 - Object Oriented Programming
   Student Name: Percy Obayuwana
   Class name: Dentist
   Date: 27/11/2019
 */

package cst8284.asgmt4.employee;
/**
 * Dentist class is a concrete class that extends Employee Class. it overrides and implements Employee's methods(s)
 * @author Percy Obayuwana
 * @version 1.0
 *
 */

public class Dentist extends Employee {
	
	/**
	 * {@value instance variable referencing an array of work descriptions}
	 */
	private static String[] workDescription = {"Assessment", "Filling", "Crown", "Cosmetic Repair"};

	/**
	 * Constructs and initializes an instance of a dentist with a specified full name as passed
	 * @param fullName name of the dentist instance to be constructed
	 */

	public Dentist(String fullName) {
		super (fullName);
	}

	/**
	 * overridden employee method that returns the current activity requested of the dentist
	 * @return this dentist's work description
	 */

	public String getActivityType() {
		System.out.println("Enter a selection from the following menu:");
		int i = 1;
		for (String description: workDescription)
			System.out.println(i++ + "." + description);
		int ch = scan.nextInt();
		scan.nextLine(); // 'eat' the next line in the buffer
		System.out.println();  // add a space
		return workDescription[ch-1];
	}
}
