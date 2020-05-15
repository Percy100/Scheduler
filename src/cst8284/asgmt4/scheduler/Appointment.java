
/* Course Name: CST8284 - Object Oriented Programming
   Student Name: Percy Obayuwana
   Class name: Appointment
   Date: 27/11/2019
 */

package cst8284.asgmt4.scheduler;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Appointment class is to put together and hold the information required to make an appointment as entered by the user
 * @author Percy Obayuwana
 * @version 1.0
 *
 */
public class Appointment implements Serializable {
	
	/**
	 * instance variable referencing user's appointment date
	 */
	private Calendar aptDate;
	
	/**
	 * instance variable referencing user's first and last names respectively
	 */
	private String firstName, lastName;
	
	/**
	 * instance variable referencing user's phone number
	 */
	private TelephoneNumber phone;
	
	/**
	 * instance variable referencing activity being requested by user
	 */
	private Activity activity;
	//public static final long serialVersionUID = 1L;

	/**
	 * 4 args constructor chained to a 5 arg constructor. Constructs and initializes an instance of appointment 
	 * with specified calendar date, full name, phone number and activity as required
	 * @param cal calendar date for the appointment
	 * @param fullName full name of the person requesting for the appointment
	 * @param phone phone number of the person requesting for the appointment
	 * @param act activity being requested
	 */
	public Appointment(Calendar cal, String fullName, TelephoneNumber phone, Activity act) {
		this(cal, fullName.trim().split(" ")[0], fullName.trim().split(" ")[1], phone, act);
	}

	/**
	 * 5 args constructor. Constructs and initializes an instance of appointment with specified calendar date, first name, last name, phone number and activity as required
	 * @param cal calendar date for the appointment
	 * @param firstName first name of the person requesting for the appointment
	 * @param lastName last name of the person requesting for the appointment
	 * @param phone phone number of the person requesting for the appointment
	 * @param act act activity being requested
	 */
	public Appointment(Calendar cal, String firstName, String lastName, TelephoneNumber phone, Activity act) {
		setFirstName(firstName); 
		setLastName(lastName);
		setCalendar(cal); 
		setPhone(phone);
		setActivity(act);
	}

	/**
	 * returns the current requested appointment date
	 * @return aptDate appointment date
	 */
	public Calendar getCalendar() {return aptDate;}

	/**
	 * sets appointment date as requested
	 * @param aptDate appointment date to be set
	 */
	public void setCalendar(Calendar aptDate) {this.aptDate = aptDate;}


	/**
	 * returns current requester's first name
	 * @return first name as a string
	 */
	public String getFirstName() {return firstName; }

	/**
	 * sets the first name to the current requester's first name
	 * @param firstName first name of the requester to be set as requested
	 */
	public void setFirstName(String firstName) {this.firstName = firstName;}

	/**
	 * returns current requester's last name
	 * @return last name as a string
	 */
	public String getLastName() {return lastName;}

	/**
	 * sets last name to the current requester's last name
	 * @param lastName last name of the requester to be set as requested
	 */
	public void setLastName(String lastName) {this.lastName = lastName;}

	/**
	 * returns current requester's telephone number
	 * @return TelephoneNumber phone
	 */
	public TelephoneNumber getPhone() {return phone;}

	/**
	 * sets phone to the current requester's telephone number
	 * @param phone telephone number of the requester to be set as requested
	 */
	public void setPhone(TelephoneNumber phone) {this.phone = phone;}


	/**
	 * returns current activity requested
	 * @return activity of appointment
	 */
	public Activity getActivity() {return activity;}

	/**
	 * sets activity to the activity requested 
	 * @param activity of appointment to be set as requested
	 */
	public void setActivity(Activity activity) {this.activity = activity;}

	/**
	 * returns the string representation of the instance of appointments formated as required
	 */
	public String toString() {
		return getCalendar().getTime().toString() + "\n" +
				getFirstName() + " " + getLastName() + "\n" + 
				getPhone().toString() + "\n" +
				getActivity().toString();
	}

}
