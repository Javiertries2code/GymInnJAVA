package display;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {

	private List<JPanel> panels = null;

	public List<JPanel> getPanels() {
		return panels;
	}

	public void setPanels(List<JPanel> panels) {
		this.panels = panels;
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;

	/**
	 * To facilitate navigation between panel, will go adding the pannels in here,
	 * please remember to respecto order in the arrayList, same as in the enum
	 */


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

		// login.setBounds(0, 0,650, 500);
		login.setVisible(true);
		contentPanel.add(login);
		panels.add(login);

//1
		RegisterPanel register = new RegisterPanel(panels);
		// register.setBounds(0, 0,650, 500);
		register.setVisible(false);
		contentPanel.add(register);
		panels.add(register);

		// 2
		WorkoutPanel workout = new WorkoutPanel(panels);
		// workout.setBounds(0, 0,650, 500);
		workout.setVisible(false);
		contentPanel.add(workout);
		panels.add(workout);

		// 3
		RecordPanel records = new RecordPanel(panels);
		records.setVisible(false);
		contentPanel.add(records);
		panels.add(records);

		// 4
		ProfilePanel profile = new ProfilePanel(panels);
		profile.setVisible(false);
		contentPanel.add(profile);
		panels.add(profile);

		// 5
		TrainingPanel training = new TrainingPanel(panels);
		training.setVisible(false);
		contentPanel.add(training);
		panels.add(training);

		//AbstractPanel.panelsvisibility(AbstractPanel.NamePanel.LOGIN, panels);

	

	}

	/**
	 * changes vicibility, and will work while adding other pannels, much cleaner
	 * than changing visibility in awhole blcok
	 * 
	 * @param login.ordinal()n
	 */
	
	}


