package display;

import java.awt.Color;

import javax.swing.JPanel;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JTextField;

import display.Frame.NamePanel;
import manager.Login;
import manager.UserInfo;
import manager.Workouts;
import objects.Workout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.awt.event.ActionEvent;


public abstract class AbstractPanel  extends JPanel  
{

	private static final long serialVersionUID = 1L;
	private JTextField textName;

	/**
	 * Create the panel.
	 */
	public AbstractPanel() {
		setBounds(0, 0, 650, 500);
		setBackground(new Color(255, 128, 128));
		setLayout(null);
		
		textName = new JTextField();
		textName.setText("GynInn");
		textName.setBounds(203, 11, 86, 20);
		add(textName);
		textName.setColumns(10);
		
		//TO-DO profile
		JButton btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(Login.currentUser.toString());
				System.out.println(Login.currentUser.getWorkout().getRefSets().get(0).toString());
			}
		});
		btnProfile.setBounds(366, 10, 89, 23);
		add(btnProfile);
		
		

	}
}
