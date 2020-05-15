
/* Course Name: CST8284 - Object Oriented Programming
   Student Name: Percy Obayuwana
   Class name: SchedulerLauncher
   Date: 27/11/2019
 */

package cst8284.asgmt4.scheduler;

import cst8284.asgmt4.employee.Dentist;

/**
 * is the main point of entry for appointment scheduling application, hence it contains the main() method. 
 * It is designed to launch the application by first instantiating a new Scheduler object, and then calling its launch() method
 * @author Percy Obayuwana
 * @version 1.0
 */
public class SchedulerLauncher {

	/**
	 * main method where program execution starts
	 * @param args an array of string type
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Scheduler(new Dentist("Dr. Andrews")).launch();
				
			}
	});
		
	}
}
