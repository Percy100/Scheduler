
/* Course Name: CST8284 - Object Oriented Programming
   Student Name: Percy Obayuwana
   Class name: BadAppointmentDataException
   Date: 27/11/2019
 */

package cst8284.asgmt4.scheduler;

/**
 * BadAppointmentDataException class is a class that has access to RuntimeException class' properties and methods
 * it's designed to catch any data input error from the user
 * @author Percy Obayuwana
 * @version 1.0
 *
 */
public class BadAppointmentDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * private String description instance variable referencing error description
	 */
	private String description;

	/**
	 * no arg constructor chained to 2 arg constructor, to initialize instance of BadAppointmentDataException
	 */
	public BadAppointmentDataException() {
		this("Please try again", "Bad data entered");
	}

	/**
	 * 2 arg constructor chained to super class constructor, to initialize instance of BadAppointmentDataException with 2 strings as passed
	 * @param s string message passed to the super constructor
	 * @param description description of error
	 */
	public BadAppointmentDataException(String s, String description) {
		super(s);
		setDescription(description);
	}

	/**
	 * returns description of current error
	 * @return description of error as a string
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * sets description according to the current error
	 * @param description description of current error to be set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



}
