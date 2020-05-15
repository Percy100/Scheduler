
/* Course Name: CST8284 - Object Oriented Programming
   Student Name: Percy Obayuwana
   Class name: Scheduler
   Date: 27/11/2019
 */

package cst8284.asgmt4.scheduler;

import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cst8284.asgmt4.employee.Employee;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * Scheduler class implements the appointment scheduler, it handles all the input and output information 
 * as well as storing instantiated Appointments objects to an array, allowing users to save and retrieve 
 * appointment information from the appointment scheduler
 * 
 * @author Percy Obayuwana
 * @version 1.0
 *
 */
public class Scheduler {
	private static final Toolkit tk = Toolkit.getDefaultToolkit();
	private static final Dimension screenSize = tk.getScreenSize();
	private static final JTextArea scrollText = new JTextArea();
	
	private JFrame frame;
	/**
	 * To read in user's input
	 */
	private static Scanner scan = new Scanner(System.in);
	
	/**
	 * appointments an array instances of the Appointments of type Appointment
	 */
	private ArrayList<Appointment> appointments = new ArrayList<>();
	
	/**
	 * employee instance of Employee
	 */
	private Employee employee;
	
	/**
	 * {@value sabc instance of SortAppointmentByCalendar}
	 */
	private final SortAppointmentByCalendar sabc = new SortAppointmentByCalendar();


	private static final int 
	SAVE_APPOINTMENT =            1,     DELETE_APPOINTMENT =            2,
	CHANGE_APPOINTMENT =          3,     DISPLAY_APPOINTMENT =           4,
	DISPLAY_SCHEDULE =            5,     SAVE_APPOINTMENTS_TO_FILE =     6,
	LOAD_APPOINTMENTS_FROM_FILE = 7,     EXIT =                          0;

	/**
	 * one arg constructor that constructs and initializes an instance of scheduler with instance Employee subtype
	 * @param emp Employee subtype
	 */
	public Scheduler(Employee emp) {
	//	System.out.println("Scheduling appointments for " + emp);
		setEmployee(emp);
	}

	/**
	 * sets Employee subtype to current Employee subtype
	 * @param emp instance of Employee subtype
	 */
	private void setEmployee(Employee emp) {this.employee = emp;}

	/**
	 * returns current instance of Employee subtype
	 * @return employee current instance Employee subtype
	 */
	private Employee getEmployee() {return employee;}

	/**
	 * takes no parameter, it displays menu of selections to user and executes the results of the user's selection
	 */
	public void launch() {
		displayMenu();
//		int choice = 0;
//		do {
//			choice = displayMenu();
//			executeMenuItem(choice);
//		} while (choice != EXIT);		
	}

	/**
	 * displays and prompts the user to make a selection of task to be performed from a selection/task menu
	 * @return an integer value corresponding to the task selected by the user
	 */
//	private int displayMenu() {
//		System.out.println("Enter a selection from the following menu:");
//		System.out.println(
//				SAVE_APPOINTMENT + ". Save appointment\n" +
//						DELETE_APPOINTMENT + ". Remove appointment\n" +
//						CHANGE_APPOINTMENT + ". Change appointment\n" +
//						DISPLAY_APPOINTMENT  + ". Get appointment\n" +
//						DISPLAY_SCHEDULE + ". Display schedule\n" +
//						SAVE_APPOINTMENTS_TO_FILE + ". Backup appointments\n" +
//						LOAD_APPOINTMENTS_FROM_FILE + ". Load appointments\n" +
//						EXIT + ". Exit program");
//		int ch = scan.nextInt();
//		scan.nextLine();  // 'eat' the next line in the buffer
//		System.out.println();
//		return ch;
//	}
	
	private void displayMenu() {
		frame = new JFrame();
		frame.setTitle("Scheduling appointments for "+getEmployee().getName());
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int screenX = (int) screenSize.getWidth() / 2;
		int screenY = (int) (7 * screenSize.getHeight() / 8);

		if ((!getAppointments().isEmpty() ) && (getAppointments() != null)){
			reloadJTextArea();
		}
			frame.add(getWestPanel(), BorderLayout.WEST);
			frame.add(getCenterPanel(scrollText, screenY), BorderLayout.CENTER);
			frame.setPreferredSize(new Dimension(screenX, screenY));

			frame.pack();
			frame.setVisible(true);
	}
			
	public void reloadJTextArea() {
		scrollText.setText(toStringFromArrayList(getAppointments()));
	}
	
	private static String toStringFromArrayList(ArrayList<Appointment> appointments) {
		StringBuilder stBd = new StringBuilder();
		for(Appointment ap :appointments) {
			stBd.append(ap.toString());
		}
		return stBd.toString();
	}
		
	public JPanel getCenterPanel(JTextArea jta, int height) {
		JScrollPane centerPane = new JScrollPane(jta);
		centerPane.setPreferredSize(new Dimension(400, 7 * height / 8));
		JPanel jp = new JPanel();
		jp.add(centerPane);
		return jp;
	}

	public JPanel getWestPanel() {
		JPanel controlPanel = new JPanel(new GridLayout(12, 1));
		JPanel westPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weighty = 1;

		
		controlPanel.add(setWestPanelBtns(" Save Appointment ", new SaveAppointment(this)));
		
		 controlPanel.add(setWestPanelBtns("     Display Appointment    ", new DisplayAppointment(this)));
		 controlPanel.add(setWestPanelBtns("      Dispaly Schedule      ", new DisplaySchedule(this)));
		 controlPanel.add(setWestPanelBtns("  Save Appointments to File  ", new SaveAppointmentToFile(this)));
		 controlPanel.add(setWestPanelBtns(" Load Appointments From File ", new LoadAppointmentFromFile(this)));
		// controlPanel.add(setWestPanelBtns("            Exit             ", new Exit(this)));
		 
		 controlPanel.add(setWestPanelBtns("    Exit   ", new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					
				}
				 
			 }));
			
			westPanel.add(controlPanel, gbc);
       		return westPanel;
		}	
	
	private JButton setWestPanelBtns(String btnLabel, ActionListener act) {
		final Font font = new Font("SansSerif", Font.PLAIN, 20);
		JButton btn = new JButton(btnLabel);
		btn.setFont(font);
		btn.addActionListener(act);
		return btn;
	}
	
	private void loadAppointmentDialog() {
		
	    JFrame f = new JFrame("Get, set, change or delete an appointment ");
	    f.getContentPane().setLayout(new GridLayout(0,1));
	    
	    JPanel p1= new JPanel(new GridBagLayout());
	    p1.setLayout(new GridLayout(0,1));
	    
	    JPanel p2= new JPanel();
	    p2.setLayout(new GridLayout(0,1));
	    
	    JPanel p3= new JPanel();
	    p3.setLayout(new GridLayout(0,1));
	    
	    JPanel p4= new JPanel();
	    p4.setLayout(new GridLayout(0,1));
	    
	    JPanel p5= new JPanel();
	    p5.setLayout(new GridLayout(0,1));
	    
	    JTextField textfield1 = new JTextField("",50);
	    JTextField textfield2 = new JTextField("",50);
	    JTextField textfield3 = new JTextField("",50);
	    JTextField textfield4 = new JTextField("",50);
	    JTextField textfield5 = new JTextField("",50);
	    JTextField textfield6 = new JTextField("",50);
	    
	    JLabel j =new JLabel(" Enter Client Name (as FirstName LastName):");  
	    p1.add(j);
	    p1.add(textfield2);
	    
	    JLabel j2 =new JLabel(" Phone Number (e.g. 613-555-1212):");  
	    p2.add(j2);
	    p2.add(textfield3);
	    
	    JLabel j3 =new JLabel(" Appointment Date (entered as DDMMYYYY):");  
	    p3.add(j3);
	    p3.add(textfield4);
	    
	    JLabel j4 =new JLabel(" Appointment Time:");  
	    p4.add(j4);
	    p4.add(textfield5);
	    
	    JLabel j5 =new JLabel(" Activity Description:");  
	    p5.add(j5);
	    p5.add(textfield6);
	    
//	    f.getContentPane().add(textfield1);
//	    f.getContentPane().add(textfield2);
//	    f.getContentPane().add(textfield3);
	    f.getContentPane().add(p1);
	    f.getContentPane().add(p2);
	    f.getContentPane().add(p3);
	    f.getContentPane().add(p4);
	    f.getContentPane().add(p5);
	    
		JButton btn1 = new JButton("Find");
		JButton btn2 = new JButton("Change");
		JButton btn3 = new JButton("Delete");
		JButton btn4 = new JButton("Exit");
		
//		GridBagConstraints c = new GridBagConstraints();
		
//		frame.add(btn1,BorderLayout.WEST);
//		frame.add(btn2, BorderLayout.EAST);
//		frame.add(btn3, BorderLayout.SOUTH);
//		frame.add(btn4, BorderLayout.SOUTH);
//		
//		c.gridx = 0;
//		c.gridy = 1;
//		p1.add(btn1);
//		
//		c.gridx = 0;
//		c.gridy = 1;
//		p1.add(btn2);
//		
//		c.gridx = 0;
//		c.gridy = 3;
//		p1.add(btn3);
//		
//		c.gridx = 0;
//		c.gridy = 4;
//		p1.add(btn4);
//		
//		f.add(p1);
		
		
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print("ASdasdasdasda"+textfield2.getText());
				//saveAppointment();
				
			}
			
		});
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// do something
				
			}
			
		});
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// do something
				
			}
			
		});
		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// do something
				
			}
			
		});
		 f.getContentPane().add(btn1);
		 f.getContentPane().add(btn2);
		 f.getContentPane().add(btn3);
		 f.getContentPane().add(btn4);
	    f.pack();
	    f.setVisible(true);
	  
	}


	/**
	 * executes the task corresponding to the user's selection or choice
	 * @param choice integer value corresponding user's selection or choice of task
	 */
	

	/**
	 * a method that prints out the string entered as a parameter, scans it in and returns the user's response as another string
	 * @param s string passed to the method
	 * @return user's response as a string
	 */
	private static String getResponseTo(String s) {
		System.out.print(s);
		String input = scan.nextLine();
		try {
			while (input.isEmpty() || input == null)
				throw new BadAppointmentDataException("Must enter a value", "Empty or null value entered");

		}catch (BadAppointmentDataException ex) {
			System.out.println(ex.getMessage() +", " + ex.getDescription()+ "\n");
		} catch (Exception ex) {
			System.out.println("General exception thrown; source unknown");
		}
		return input;

	}

	/**
	 * prompts user for all the information to make an appointment(full name, phone number, date and time of appointment and activity, 
	 * and then makes an appointment object with the information 
	 * @return a new Appointment object
	 */
	public Appointment makeAppointmentFromUserInput(String fullName, String phoneNumber, String date, String time, String activity) {

		try { 
			if (!fullName.isEmpty())
				if (!fullName.matches("[ a-zA-Z'.' '-]+"))
					throw new BadAppointmentDataException ("Name cannot include characters other than alphabetic characters, "
							+ "the dash (-), the period (.), and the apostrophe (‘)", "Illegal characters in name");

			if (fullName.split(" ")[0].length() > 30 || fullName.split(" ")[1].length() > 30 )
				throw new BadAppointmentDataException ("Name cannot exceed 30 characters", "Name exceeds maximum length");
		}catch (BadAppointmentDataException ex) {
			System.out.println(ex.getMessage() +", " + ex.getDescription()+ "\n");
		} catch (Exception ex) {

		}

		

		TelephoneNumber phone = new TelephoneNumber(phoneNumber);
		Calendar cal = makeCalendarFromUserInput(date, time);
		Activity act = new Activity(activity, "" /*TODO getEmployee().getActivityType()*/);
		return (new Appointment(cal, fullName, phone, act));
	}
	/**
	 * takes a boolean value as parameter, prompts the user for the date and time of appointment by calling getResponseTo method twice, 
	 * once for the date and a second time for the hour, processes the two strings into year, month, day and hour values, 
	 * and then instantiates a new Calendar object using one of the overloaded Calendar's set() methods
	 * @param suppressHour boolean value that is set to true when time is required and false when time is required for a calendar
	 * @return a new Calendar object
	 */
	public static Calendar makeCalendarFromUserInput(String date) {
		return makeCalendarFromUserInput(date, "0");
		
	}
		
		public static Calendar makeCalendarFromUserInput(String date, String time) {
			Calendar cal = Calendar.getInstance();
			int hour = 0;
			int day= 0; int month = 0; int year = 0;

		cal.clear();

		if (date.contains("-") || date.contains("/")) 
			try {
				throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY", "Bad calendar format");
			}catch (BadAppointmentDataException ex) {
				System.out.println(ex.getMessage() +", " + ex.getDescription()+ "\n");
			} catch (Exception ex) {
				System.out.println("General exception thrown; source unknown");
			}
		else {

			day = Integer.parseInt(date.substring(0,2));
			month = Integer.parseInt(date.substring(2,4)) - 1;  // offset by one to account for zero-based month in Calendar
			year = Integer.parseInt(date.substring(4,8));

			try {	
				switch (month) {
				case 1:
					if ((year%4==0) && (year%100!=0) && (day > 29))
						throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY", "Bad calendar format");
					else if (((year%4!=0) || (year%100==0)) && (day > 28))
						throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY", "Bad calendar format");
					break;
				case 3:
				case 5:
				case 8:
				case 10:
					if (day > 30) 
						throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY", "Bad calendar format");
					break;
				default:
					if (month < 0 || month > 11 || day < 1) 
						throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY", "Bad calendar format");
					else
						if (day > 31) 
							throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY", "Bad calendar format");

				}
			}catch (BadAppointmentDataException ex) {
				System.out.println(ex.getMessage() +", " + ex.getDescription()+ "\n");
			} catch (Exception ex) {
				System.out.println("General exception thrown; source unknown");
			}}

					
			hour = processTimeString(time);
		

		cal.set(year, month, day, hour, 0);
		return (cal);
	}

	/**
	 * a method that takes time as a String (read in any of the stated formats), and returns an 
	 * integer corresponding to the correct 24-hour format value.
	 * @param t times passed as a string
	 * @return integer value corresponding 24-hour time format 
	 */
	private static int processTimeString(String t) {
		int hour = 0;
		t = t.trim();
		if (t.contains(":")) hour = Integer.parseInt(t.split(":")[0]);
		else if (t.contains (" ")) hour = Integer.parseInt(t.split(" ")[0]);
		else hour = Integer.parseInt(t);
		return ((hour < 8 && hour > 0) ? hour+12 : hour);
	}

	/**
	 * this method takes an calendar value as parameter, searches the appointment arraylist by 
	 * calling Collections' sort and binarySearch methods. If an appointment with same calendar 
	 * value as that passed is found, it returns the appointment and exit else it returns null 
	 * if no appointment with same calendar value passed is found
	 * @param cal calendar value passed as parameter to the method
	 * @return an appointment if found or null if not found
	 */
	private Appointment findAppointment(Calendar cal) {
		Collections.sort(getAppointments(), new SortAppointmentByCalendar());
		int index = Collections.binarySearch(getAppointments(), new Appointment(cal, null, null, null,null), sabc);
		if (index >= 0) {
			getAppointments().get(index);
		}	
		return null;
	}

	/**
	 * method that takes an appointment as a parameter, check to see if the appointment already exists by calling 
	 * findAppointment() to see if any matching Calendar objects exist in the appointments arraylist. If findAppointment() returns null, 
	 * then that time is available, and the new Appointment is safely booked or added to the array list and confirmation message is printed. 
	 * If not,then the attempt is aborted, and a message is printed informing the user that the attempt failed.
	 * @param apt an Appointment object passed as a parameter to the method
	 * @return boolean value, true if appointment was successfully saved, else false
	 */
	public boolean saveAppointment(Appointment apt) {	
		Calendar cal = apt.getCalendar(); 
		if (findAppointment(cal)==null) { 
			getAppointments().add(apt);
			Collections.sort(getAppointments(), sabc); // added
		//	System.out.println("Appointment saved.");  
			JOptionPane.showMessageDialog(frame,
				    "Appointment saved");
			return true;
		}  // else time slot taken, need to make another choice
	//System.out.println("Cannot save; an appointment at that time already exists");
		JOptionPane.showMessageDialog(frame,
			    "Cannot save; an appointment at that time already exists");
		return false;
	}
	/**
	 * a method that takes a calendar object as a parameter, searches and display details of the appointment, 
	 * prompts the user to confirm if its okay to delete the appointment. If the user's input is yes, it goes 
	 * ahead to remove the appointment object from the array list and returns true if successful or false if not
	 * @param cal calendar object passed as a parameter to the method
	 * @return boolean value, true if successful or false if not
	 */
	private boolean deleteAppointment(Calendar cal) {
		if (displayAppointment(cal)) {  							// display existing appointment on this date/time
			String okToChange = getResponseTo("\nEnter 'Yes' to delete this appointment");
			if (okToChange.trim().equals("Yes")) {  						// okay to proceed with change/deletion?
				getAppointments().remove(findAppointment(cal)); 
				System.out.println("Appointment deleted"); 
				return true;
			} else System.out.println("Request cancelled"); 
		} return false;  // Appointment didn't exist at the date/time specified
	}

	/**
	 * the method is used to change the date and time of an existing appointments, takes calendar object as parameter. 
	 * It prompts the user for the calendar value of the appointment to be changed, displays details of the appointment 
	 * by calling the displayAppointment method, prompt the user to confirm if its okay to change the appointment. 
	 * If the user's input is yes, it then prompts the user to enter the new date and time, makes a new calendar from the 
	 * user input, checks to check that date and time is free by calling findAppointment method. if free, the appointment
	 * is updated with the new values and returns true if successful or false if not
	 * @param cal calendar object passed as a parameter
	 * @param date 
	 * @param time 
	 * @return boolean value, true if successful or false if not
	 */
	private boolean changeAppointment(Calendar cal, String date, String time) {
		if (displayAppointment(cal)) {  							// display existing appointment on this date/time
			String okToChange = getResponseTo("\nEnter 'Yes' to change the date and time of this appointment ");
			if (okToChange.trim().equals("Yes")) { 
				System.out.println("Enter new date and time");
				Calendar newCal = makeCalendarFromUserInput(date, time); // get new date/time
				if (findAppointment(newCal)==null) {				// appointment time not already taken
					findAppointment(cal).setCalendar(newCal);		// set new date/time in appointment
					System.out.println("Appointment re-booked\n");
					return true;									// new appointment time set
				} else System.out.println("That time is already booked for an appointment\n");
			} else System.out.println("Request cancelled"); 
		} return false;  // Appointment does not exist, was unavailable, or cancelled
	}

	/**
	 * this method is used to display a particular appointment details, it takes calendar object as a parameter, 
	 * prompts the user for a calendar value for the appointment, checks to see if the appointment with the passed 
	 * calendar value exist by calling the findAppointment method. If it does, it calls the displayAppointment 
	 * method to output details of the appointment, if not, it outputs a message telling the user no appointment
	 * exist for the said date and time
	 * @param cal calendar object passed as a parameter
	 * @return boolean value, true if found or false if not
	 */
	public boolean displayAppointment(Calendar cal) {
		Appointment apt = findAppointment(cal);
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		scrollText.setText((apt!=null) ?
				"\n\n"+ apt.toString()+"\n": // Output the appointment as a string to the console, otherwise...
					"No appointment scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n"
				);
		return (apt!=null);
	}
	/**
	 * this method displays all appointments for a particular day, it takes calendar object as parameter. 
	 * Prompts the user for a calendar date (without time) for the appointment and displays details of
	 * all the appointments for that day 
	 * @param cal calendar object passed as a parameter
	 */
	public void displayDaySchedule(Calendar cal) {
		for (int hrCtr = 8; hrCtr < 17; hrCtr++) {
			cal.set(Calendar.HOUR_OF_DAY, hrCtr);
			displayAppointment(cal);		
		}
	}

	/**
	 * a method that takes an arraylist of Appointment type and a storage file (as a string) as parameter and load or write each 
	 * Appointment object in the ArrayList into the storage file passed to it
	 * @param apts arraylist of Appointment type passed to the method
	 * @param saveFile storage file passed as a string to the method to be written or loaded to
	 * @return boolean value, true if loading or writing was successful or false if not
	 */
	public boolean saveAppointmentsToFile(ArrayList<Appointment> apts, String saveFile) {
		try (FileOutputStream fos = new FileOutputStream(saveFile);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			for (Appointment apt: apts) 
				oos.writeObject(apt);
		//	scrollText.setText("Appointment data saved to " + saveFile);
			JOptionPane.showMessageDialog(frame,
				    "Appointment data saved to File");
			return true;
		} catch (IOException e) {
			scrollText.setText("Failed to load appointments from " + saveFile);
			JOptionPane.showMessageDialog(frame,
				    "Failed to load appointments from File");
			return false;
		}
	}

	/**
	 * a method that takes a storage file (as a string) and an arraylist of Appointment type as parameters and reads each 
	 * Appointment object in the arraylist from the storage file passed to it
	 * @param sourceFile storage file passed as a string to the method to be read from
	 * @param apts arraylist of Appointment type passed to the method
	 * @return boolean value, true if reading was successful or false if not
	 */
	public boolean loadAppointmentsFromFile(String sourceFile, ArrayList<Appointment> apts) {
		apts.clear();  // remove all existing appointments from the ArrayList before loading from file
		try (FileInputStream fis = new FileInputStream(sourceFile);
				ObjectInputStream ois = new ObjectInputStream(fis);){
			while(true) apts.add((Appointment)ois.readObject());
		} 
		catch (EOFException ex) {
		//	System.out.println("Appointments successfully loaded from " + sourceFile);
			JOptionPane.showMessageDialog(frame,
				    "Appointments successfully loaded from File");
			return true;}
		catch (IOException | ClassNotFoundException e) {return false;} 	
	}

	public ArrayList<Appointment> getAppointments() {return appointments;}

}
