
/* Course Name: CST8284 - Object Oriented Programming
   Student Name: Percy Obayuwana
   Class name: SortAppointmentByCalendar
   Date: 27/11/2019
 */

package cst8284.asgmt4.scheduler;

import java.util.Comparator;

/**
 * SortAppointmentByCalendar class implements Comparator and it's purpose is to 
 * to organize the Appointments by date using the overridden compare method
 * @author Percy Obayuwana
 * @version 1.0
 *
 */
public class SortAppointmentByCalendar implements Comparator<Appointment> {

	/**
	 * a method that compare two Appointment objects returns an integer value representing the difference 
	 * between the Calendar’s of the two Appointments' input as parameters to the method
	 * @param apt1 one of the appointment objects being compared
	 * @param apt2 the second appointment object being compared
	 * @return return an integer value
	 */
	public int compare(Appointment apt1, Appointment apt2) {
		return apt1.getCalendar().compareTo(apt2.getCalendar());
	}


}
