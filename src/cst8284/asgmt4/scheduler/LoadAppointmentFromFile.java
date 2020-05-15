package cst8284.asgmt4.scheduler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class LoadAppointmentFromFile extends JFrame implements ActionListener{
	
	Scheduler sh;
	
	public LoadAppointmentFromFile(Scheduler sh) {
		this.sh = sh;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		sh.loadAppointmentsFromFile("CurrentAppointments.apts", sh.getAppointments());
	}

}
