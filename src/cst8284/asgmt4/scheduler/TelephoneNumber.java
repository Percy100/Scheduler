
/* Course Name: CST8284 - Object Oriented Programming
   Student Name: Percy Obayuwana
   Class name: TelephoneNumber
   Date: 27/11/2019
 */

package cst8284.asgmt4.scheduler;

import java.io.Serializable;

/**
 * The telephoneNumber is mean to hold phone number of the user and parse the phone number into it's various components (areacode, prefix and line number)
 * @author Percy Obayuwnana
 * @version 1.0
 *
 */
public class TelephoneNumber implements Serializable {
	
	/**
	 * areaCode the first set of 3 digits of phone number
	 * prefix the second set of 3 digits of phone number
	 * lineNumber the last set of 4 digits of phone number
	 */
	private int areaCode, lineNumber, prefix;

	/**
	 * one arg that take a string and initializes an instance of telephoneNumber with a specified phone number passed as a string
	 * @param phoneNumber user's phone number passed as a string
	 */
	public TelephoneNumber(String phoneNumber) {
		/**
		 * validating user's phone number before parsing into it's component parts
		 */
		try {
			if (isBadCharacter(phoneNumber))
				throw new BadAppointmentDataException("Telephone numbers can only contain numbers or the character ‘-‘", "Bad character(s) in input string");

			if (isIncorrectFormat(phoneNumber))
				throw new BadAppointmentDataException("Missing digit(s); correct format is AAA-PPP-NNNN, "
						+ "{where AAA is the area code and PPP-NNNN is the local number", "Incorrect format");

			if (isInvalidNumber(phoneNumber))
				throw new BadAppointmentDataException("Area code can’t start with a ‘0’ or a ‘1’", "Invalid number");

			int areaCode = Integer.parseInt(phoneNumber.split("-")[0].trim());
			int prefix = Integer.parseInt(phoneNumber.split("-")[1].trim());
			int lineNumber = Integer.parseInt(phoneNumber.split("-")[2].trim());
			setAreaCode(areaCode); setPrefix(prefix); setLineNumber(lineNumber);
		}catch (BadAppointmentDataException ex) {
			System.out.println(ex.getMessage() +", " + ex.getDescription()+ "\n");
		} catch (Exception ex) {
			;
		}
		return;
	}	

	/**
	 * returns the areacode of the current user's phone number
	 * @return integer value representation of areacode of the user's phone number
	 */
	public int getAreaCode() {return areaCode;}

	/**
	 * sets areacode to the areacode from the user's phone number
	 * @param areaCode areacode of the user's phone number
	 */
	public void setAreaCode(int areaCode) {this.areaCode = areaCode;}

	/**
	 * returns the prefix of the current user's phone number
	 * @return integer value representation of prefix of the user's phone number
	 */
	public int getPrefix() { return prefix;}

	/**
	 * sets prefix to the prefix from the user's phone number
	 * @param prefix prefix of the user's phone number
	 */
	public void setPrefix(int prefix) {this.prefix = prefix;}

	/**
	 * returns the line number of the current user's phone number
	 * @return integer value representation of line number of the user's phone number
	 */
	public int getLineNumber() {return lineNumber;}

	/**
	 * sets line number to the prefix from the user's phone number
	 * @param lineNumber line number of the user's phone number
	 */
	public void setLineNumber(int lineNumber) {this.lineNumber = lineNumber;}

	/**
	 * a method that takes phone number as a string and checks to see if it's in the correct format
	 * @param phoneNumber user's phone number passed as a string to the method
	 * @return boolean value, true if it's in the correct format, else false
	 */
	private boolean isIncorrectFormat(String phoneNumber){

		return (phoneNumber.length() !=12) && (phoneNumber.matches("[0-9-]+"));
	}

	/**
	 * a method that takes phone number as a string and checks to see if it's a valid phone number
	 * @param phoneNumber user's phone number passed as a string to the method
	 * @return boolean value, true if it's a valid number, else false
	 */
	private boolean isInvalidNumber(String phoneNumber){
		return (Integer.parseInt(phoneNumber.split("-")[0].trim()) < 200);
	}

	/**
	 * a method that takes phone number as a string and checks to see if phone number contains bad character
	 * @param phoneNumber user's phone number passed as a string to the method
	 * @return boolean value, true if phone number contains bad character, else false
	 */
	private boolean isBadCharacter(String phoneNumber){

		return phoneNumber.contains("#") || phoneNumber.contains("(");		
	}

	/**
	 * a method that takes no parameter, it returns the string representation of the instance of telephonenUmber
	 */
	public String toString() {return "(" + getAreaCode() +") "+ getPrefix() + "-" + getLineNumber();}
}
