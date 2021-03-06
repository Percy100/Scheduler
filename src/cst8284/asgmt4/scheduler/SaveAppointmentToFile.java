package cst8284.asgmt4.scheduler;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaveAppointmentToFile extends JFrame implements ActionListener{
	
	Scheduler sh;
	
	public SaveAppointmentToFile(Scheduler sh) {
		this.sh = sh;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		sh.saveAppointmentsToFile(sh.getAppointments(), "CurrentAppointments.apts");
		
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
	    
	   
//	    JTextField textfield1 = new JTextField("",50);
//	    JTextField textfield2 = new JTextField("",50);
//	    JTextField textfield3 = new JTextField("",50);
//	    JTextField textfield4 = new JTextField("",50);
//	    JTextField textfield5 = new JTextField("",50);
//	    
//	    JLabel j1 =new JLabel(" Enter Client Name (as FirstName LastName):");  
//	    p1.add(j1);
//	    p1.add(textfield1);
//	    
//	    JLabel j2 =new JLabel(" Phone Number (e.g. 613-555-1212):");  
//	    p2.add(j2);
//	    p2.add(textfield2);
//	    
//	    JLabel j3 =new JLabel(" Appointment Date (entered as DDMMYYYY):");  
//	    p3.add(j3);
//	    p3.add(textfield3);
//	    
//	    JLabel j4 =new JLabel(" Appointment Time:");  
//	    p4.add(j4);
//	    p4.add(textfield4);
//	    
//	    JLabel j5 =new JLabel(" Activity Description:");  
//	    p5.add(j5);
//	    p5.add(textfield5);
//	    
//
//	    f.getContentPane().add(p1);
//	    f.getContentPane().add(p2);
//	    f.getContentPane().add(p3);
//	    f.getContentPane().add(p4);
//	    f.getContentPane().add(p5);
//	    
//		JButton btnSave = new JButton("Save");
//		JButton btnExit= new JButton("Exit");
//		
//		
//		
//		btnSave.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				sh.saveAppointmentsToFile(sh.getAppointments(), "CurrentAppointments.apts");
//			}
//			
//		});
//		btnExit.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				f.dispose();
//				
//			}
//			
//		});
//		
//		 f.getContentPane().add(btnSave);
//		 f.getContentPane().add(btnExit);
//		 
//	    f.pack();
//	    f.setVisible(true);
//	  
	}


}
