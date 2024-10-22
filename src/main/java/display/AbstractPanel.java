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
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.awt.event.ActionEvent;
import display.Frame;


public abstract class AbstractPanel  extends JPanel  
{

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AbstractPanel() {
		setBounds(0, 0, 650, 500);
		setBackground(new Color(255, 128, 128));
		setLayout(null);
		
		

	}
}
