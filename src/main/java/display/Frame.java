package display;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {

	 
	
	private List<JPanel> panels = null;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	
	/**
	 * To facilitate navigation between panel, will go adding the pannels in here, 
	 * please remember to respecto order in the arrayList, same as in the enum
	 */
	public enum NamePanel {
	    LOGIN,
	    REGISTER,
	    WORKOUT,
	  
	}
	
	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			Frame frame = new Frame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	

	/**
	 * Create the frame.
	 */
	public Frame() {
		
		
		panels = new ArrayList<JPanel>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
//0  
		LoginPanel login = new LoginPanel(panels);
		
		//login.setBounds(0, 0,650, 500);
		login.setVisible(false);
		contentPanel.add(login);
		panels.add(login);
		
		
//1
		RegisterPanel register = new RegisterPanel(panels);
		//register.setBounds(0, 0,650, 500);
		register.setVisible(false);
		contentPanel.add(register);
		panels.add(register);
		
		//2
		WorkoutPanel workout = new WorkoutPanel(panels);
		//workout.setBounds(0, 0,650, 500);
		workout.setVisible(false);
		contentPanel.add(workout);
		panels.add(workout);
		
		panelsvisibility(NamePanel.LOGIN, panels);
		
	}
	
	/**
	 * changes vicibility, and will work while adding other pannels, much cleaner than changing visibility in awhole blcok
	 * @param login.ordinal()n
	 */
	public void panelsvisibility(NamePanel panelName, List<JPanel> panels2) {
		for (int i = 0; i < panels2.size(); i++)
		{
		if (i == panelName.ordinal())
			panels2.get(i).setVisible(true);
		else
			panels2.get(i).setVisible(false);
		}
	}

}
