
/* Course Name: CST8284 - Object Oriented Programming
   Student Name: Percy Obayuwana
   Class name: Activity
   Date: 27/11/2019
 */

package cst8284.asgmt4.scheduler;

import java.io.Serializable;

/**
 * activity class holds the activity information for appointments
 * @author Percy Obayuwana
 * @version 1.0
 *
 */
public class Activity implements Serializable {
	
	/**
	 * instance variable referencing description of activity being requested
	 */
	private String descriptionOfWork;
	
	/**
	 * instance variable referencing category of activity being requested
	 */
	private String category;

	/**
	 * Constructs and initializes an instance of activity with specified description and category
	 * @param description description of appointment activity
	 * @param category category of appointment activity
	 */
	public Activity(String description, String category) {
		setDescription(description);
		setCategory(category);
	}

	/**
	 * returns the current description of activity
	 * @return description of work/activity
	 */
	public String getDescription() {return descriptionOfWork;}

	/**
	 * sets the description of activity as required
	 * @param description description of activity
	 * 
	 */
	public void setDescription(String description) {this.descriptionOfWork = description;}

	/**
	 * returns the current category of activity
	 * @return category of activity
	 */
	public String getCategory() {return category;}

	/**
	 * sets the category of activity as required
	 * @param category category of activity
	 */
	public void setCategory(String category) {this.category = category;}

	/**
	 * returns the string representation of the instance of activity
	 */
	public String toString() {return getCategory() + "\n" + getDescription();}
}
